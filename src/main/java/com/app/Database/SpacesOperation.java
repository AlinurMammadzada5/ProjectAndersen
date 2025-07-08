package com.app.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class SpacesOperation {
    private static SpacesOperation instance;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("coworkingPersistence");


    private SpacesOperation(){

    }
    public static SpacesOperation getInstance() {
        if (instance == null) {
            instance = new SpacesOperation();
        }
        return instance;
    }


    public void addSpace() {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();

            Spaces space = new Spaces();
            space.setReserved(false);
            space.setUsername("-");

            em.persist(space);
            em.getTransaction().commit();
        }

  }

    public void deleteSpace(int id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();

            Spaces space = em.find(Spaces.class, id);
            if (space != null) {
                em.remove(space);
            }
            em.getTransaction().commit();
        }


    }

    public void getAllSpaces() {
try(EntityManager em = emf.createEntityManager()){
    em.createQuery("SELECT s FROM Spaces s", Spaces.class)
            .getResultList()
            .forEach(System.out::println);
}

    }


    public void getAvailableSpaces() {
try(EntityManager em = emf.createEntityManager()){
    em.createQuery("SELECT s.id FROM Spaces s where s.reserved=false", Integer.class)
            .getResultList()
            .forEach(id -> System.out.println("Table "+id+" Available"));
}

    }

    public void reserveSpace(int id, String username) {

        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();

            int userReserveCount=userSpaceCount(username);

            if(userReserveCount<2) {
                Spaces space = em.find(Spaces.class, id);
                if (space != null && !space.isReserved()) {
                    space.setReserved(true);
                    space.setUsername(username);
                }
            } else {
                System.out.println("Maximum number of spaces have been reserved. First Try to Cancel Reservation");
            }
            em.getTransaction().commit();
        }


    }

    public void cancelReservation(int id, String username) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            int userSpaceCount=userSpaceCount(username);
            if(userSpaceCount>0) {
                Spaces space = em.find(Spaces.class, id);
                if (space != null && space.isReserved() && username.equals(space.getUsername())) {
                    space.setReserved(false);
                    space.setUsername("-");
                }

                em.getTransaction().commit();
            } else {
                System.out.println("You don't have any spaces to cancel");
            }
        }


    }
    public int userSpaceCount(String username) {
       try(EntityManager em = emf.createEntityManager()){
           Long spaceCount = em.createQuery(
                           "SELECT COUNT(s) FROM Spaces s WHERE s.reserved = true AND s.username = :username",
                           Long.class
                   ).setParameter("username", username)
                   .getSingleResult();
           return spaceCount.intValue();
       }



    }

    public void myReservedSpaces(String username) {
 try(EntityManager em = emf.createEntityManager()){


     em.createQuery(
                     "SELECT s.id FROM Spaces s WHERE s.reserved = true AND s.username = :username",
                     Integer.class
             )
             .setParameter("username", username)
             .getResultList()
             .forEach(id -> System.out.println("Table " + id + " Reserved"));
 }

    }
}
