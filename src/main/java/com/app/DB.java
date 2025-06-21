package com.app;

import java.io.*;
import java.util.*;



public class DB {
    private static DB instance;


      private final Map<String,Integer> userReservationCount = new HashMap<>();
      private final List<Reservation<String>> reservations ;
      private int spaceCount;
      private final String FILE_NAME = "spaces.txt";


      public int getSpaceCount(){
          return spaceCount;
      }


      public List<Reservation<String>> getReservations(){
          return new ArrayList<>(reservations);
      }

      private DB() {
        reservations = new ArrayList<>();
         this.spaceCount=1;
         try {
             readFile();
         } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("You can add spaces first");
         }

     }

     public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
         return instance;
     }


     void spaceSaver(){
          try(FileWriter spacesText = new FileWriter(FILE_NAME)) {
              for (int i = 0; i < reservations.size(); i++) {
                  String currSpace = "Table " + (i + 1) + "," + reservations.get(i).getBooked() + "," + reservations.get(i).getUsername();
                  spacesText.write(currSpace+"\n");

              }
          }catch (IOException e) {
              System.out.println("Error to save Table files");
          }
     }

     void readFile(){
         try(BufferedReader spaceReader = new BufferedReader(new FileReader("spaces.txt"))){
             String currLine;
             while ((currLine = spaceReader.readLine()) != null) {
                 String currSpace = currLine.split(",")[0];
                 String currBook = currLine.split(",")[1];
                 String currBookUsername = currLine.split(",")[2];


                 if(currBook.equals("false")){

                     reservations.add(new Reservation<>(currSpace,false,currBookUsername));
                 } else if(currBook.equals("true")){
                     reservations.add(new Reservation<>(currSpace,true,currBookUsername));
                 }
                 spaceCount++;
             }

         } catch (IOException e){
             System.out.println("Error to read file");
         }
     }

     void addSpace() {

          reservations.add(new Reservation<>("Table " + spaceCount,false, "-"));
          System.out.println("New space added : Table " + spaceCount);
          spaceCount++;
 }

      void deleteSpace() {
        System.out.print("Enter space number: ");
        int choice = ScannerGet.scanner.nextInt();

          try{
              reservations.remove(choice - 1);
              organizeTable();
              spaceCount--;
          } catch (IndexOutOfBoundsException e){
              System.out.println("Space Number out of bounds");

        }
    }

void organizeTable() {

          for (int i = 0; i < reservations.size(); i++) {
        reservations.get(i).setSpaceName("Table"+i);
    }
}



    void printTable() {
         reservations.stream().map(r ->r.getSpaceName()+" "+r.getBooked()+"  "+r.getUsername()).forEach(System.out::println);


      }



       void printAvailableTables() {
           reservations.stream().map(r -> !r.getBooked() ? r.getSpaceName()+" : available " : r.getSpaceName()+" : not available").forEach(System.out::println);
    }

      void reserveSpace(int choice, String username) throws NotExistedTableException {
try {


    if (!reservations.get(choice - 1).getBooked()) {
        countReservations(username);
        int currReservationCount = userReservationCount.getOrDefault(username, 0);
        if (currReservationCount < 2) {
            System.out.println("Table " + (choice) + " is  reserved");
            userReservationCount.put(username, currReservationCount + 1);
            reservations.set(choice - 1, new Reservation<>(reservations.get(choice - 1).getSpaceName(), true, username));
        } else {
            System.out.println("You already have reserved 2 space. You need to delete a reserved space first");
        }

    } else {
        System.out.println("This book is already reserved by someone else");
    }
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Not That Amount of Space is Available in Our Space App");
}
    }
    void countReservations(String username) {
          int reserveCounter=0;
        for (Reservation<String> reservation : reservations) {
            if (reservation.getUsername().equals(username)) {
                reserveCounter++;
            }
            userReservationCount.put(username,reserveCounter);
        }
    }

      void myReservedSpace(String username) {
       reservations.forEach(reservation -> {
           if(reservation.getUsername().equals(username)) {
               System.out.println(reservation.getSpaceName() + " is reserved by you");
           }
       });
    }


      void cancelReservedSpace(String username, int choice) {
           int currReservationCount = Optional.ofNullable(userReservationCount.get(username)).orElse(0);
          if (currReservationCount > 0) {
              if (choice <= 0 || choice > reservations.size()) {
                  System.out.println("Invalid table number.");
                  return;
              }

              Reservation<String> r = reservations.get(choice - 1);
              if (r.getUsername().equals(username)) {
                  reservations.set(choice - 1, new Reservation<>(r.getSpaceName(),false,"" ));

                  int curr = userReservationCount.getOrDefault(username, 0);
                  userReservationCount.put(username, curr - 1);
                  System.out.println("Reservation for " + r.getSpaceName() + " cancelled.");
              } else {
                  System.out.println("You don’t own this reservation.");
              }
          } else {
              System.out.println("You don't have reserved space");
          }
      }
}
