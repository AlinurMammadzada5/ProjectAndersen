package com.app.ConsoleUI;

import com.app.Exceptions.EmptyNameException;
import com.app.Exceptions.NotExistedTableException;
import com.app.Scanner.ScannerGet;
import com.app.Database.SpacesOperation;

public class UserLog {
    String username;
    Boolean userLogged;
private final SpacesOperation so= SpacesOperation.getInstance();


    void userLogin() throws EmptyNameException, NotExistedTableException {
            userLogged = false;
        while (!userLogged) {
            try {
                System.out.print("Enter Username: ");
                username = ScannerGet.scanner.nextLine();

                if (username.isEmpty()) {
                    throw new EmptyNameException("Empty Username is not Accepted");

                }
                System.out.println("User Login Successful...");
                userLogged = true;

            } catch (EmptyNameException e) {
                System.out.println("Enter Username to Proceed ");
            }

}



            int choice=0;
            while (choice!=5) {
            System.out.println("Options : 1 - Browse The Spaces  /  2 - Make a Reservation  /  3 - View My Reservation  /   4 - Cancel Reservation  /  5 - Exit");
            System.out.print("Your Choice :  ");
            choice = ScannerGet.scanner.nextInt();
            getAnswer(choice);
        }
    }

    public void getAnswer (int choice) {

        switch (choice) {
            case 1:{
                so.getAvailableSpaces();
                break;
            }
            case 2:{
                System.out.print("Enter the Table ID : ");
                int reserveChoice = ScannerGet.scanner.nextInt();
                so.reserveSpace(reserveChoice,username);

                break;
            }
            case 3:{
                so.myReservedSpaces(username);
                break;
            }
            case 4:{
                System.out.println("Enter the Canceling Table ID : ");
                int cancelChoice = ScannerGet.scanner.nextInt();
                so.cancelReservation(cancelChoice,username);
                break;
            }
            case 5: break;
            default:{
                System.out.println("Invalid Option");
                break;
            }
        }
        }
    }

