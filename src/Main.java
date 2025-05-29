

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class scannerGet{
    static Scanner scanner = new Scanner(System.in);
}

/* Our List Database and methods*/
class DB {
    static List<String> spaces = new ArrayList<>();
    static List<Boolean> books = new ArrayList<>();
    static List<String> bookUsernames = new ArrayList<>();
    static int spaceCount = 0;

    static void addSpace() {
        int currSpace = spaceCount + 1;
        spaces.add("Table " + spaceCount);
        System.out.println("New space added : Table " + currSpace);
        books.add(false);
        bookUsernames.add("-");
        spaceCount++;

    }

    static void deleteSpace() {
        System.out.print("Enter space number: ");
        int choice = scannerGet.scanner.nextInt();
        if(choice>=0 && choice<=spaces.size()) {
        spaces.remove(choice - 1);
        organizeTable();
        spaceCount--;
        } else{
            System.out.println("Invalid space number");
        }
    }

    static void organizeTable() {
        for (int i = 0; i < spaces.size(); i++) {
            spaces.set(i, "Table " + i);
        }
    }

    static void printTable() {
        for (int i = 0; i < spaces.size(); i++) {
            if (books.get(i)==false) {
                System.out.println("Table " + (i + 1) + " : booked :  " + books.get(i));
            }else{
                System.out.println("Table " + (i + 1) + " : booked :  " + books.get(i) + " Username : " + bookUsernames.get(i));
            }
        }
    }

    static void printAvailableTables() {
        for (int i = 0; i < spaces.size(); i++) {
            if (books.get(i)==false) {
                System.out.println("Table " + (i+1) + " is  available");
            }
        }
    }

    static void reserveSpace(int choice,String username) {

        if(choice>0 && choice<=spaces.size()) {
        if(books.get(choice-1)==false) {
            System.out.println("Table " + (choice) + " is  reserved");
            books.set(choice-1, true);
            spaces.set(choice-1, "Table "+choice);
            bookUsernames.set(choice-1, username);
        } else {
            System.out.println("This book is already reserved by someone else");
        }
        } else{
            System.out.println("This book is not available");
        }
    }

    static void myReservedSpace(String username) {
        for (int i = 0; i < spaces.size(); i++) {
            if(bookUsernames.get(i).equals(username)) {
                System.out.println("Table " + (i+1) + " is  reserved for yourself");
            }
        }
    }

    static void cancelReservedSpace(String username, int choice) {
        if(choice>0 && choice<=spaces.size()) {
        final int numberInList =  choice-1;
        if(books.get(numberInList)==true) {
            if (bookUsernames.get(numberInList).equals(username)) {
                System.out.println("Table " + choice + " is  cancelled for yourself");
                books.set(numberInList, false);
                bookUsernames.set(numberInList, "-");
            }
        } else {
            System.out.println("This book is not reserved by someone else");
        }
        }
    }

}


class menu{

    void showMenu(){
        int choice=0;
        while(choice!=4) {
            System.out.println("Welcome To Coworking Space Reservation Application" +
                    "\n" + "Options: 1 - Admin Login  /  2 - User Login  /  3 - Exit");
            System.out.print("Your Choice :  ");
            choice = scannerGet.scanner.nextInt();
            menuAnswer(choice);
        }
    }
    void menuAnswer(int choice){

        if(choice == 1){
            adminLog admin = new adminLog();
            admin.adminLogin();
        }
        else if(choice == 2){
            userLog user = new userLog();
            user.userLogin();
        }
        else if(choice == 3){
            System.exit(0);
        } else {
            System.out.println("Invalid Choice");
        }
    }
}
class adminLog{
    void adminLogin(){
        System.out.println("Admin Login Successful...");
        int choice=0;
        while (choice!=5) {
            System.out.println("Options : 1 - Add a new Coworking Space  /  2 - Remove a Coworking Space  /  3 - View Spaces  /   4 - Exit");
            System.out.print("Your Choice :  ");
            choice = scannerGet.scanner.nextInt();
            getAnswer(choice);
        }



    }
    public void getAnswer(int choice) {
        if (choice == 1) {
            DB.addSpace();
        } else if (choice == 2) {
            DB.deleteSpace();
        } else if (choice == 3) {
            DB.printTable();
        } else if (choice == 4) {
            menu mainMenu = new menu();
            mainMenu.showMenu();
        } else {
            System.out.println("Invalid Choice");

        }
    }
}

class userLog{
    String username;
    void userLogin(){
        System.out.print("Enter Username: ");
        scannerGet.scanner.nextLine();
        username = scannerGet.scanner.nextLine();
        System.out.println("User Login Successful...");
        int choice=0;

        while (choice!=5) {



            System.out.println("Options : 1 - Browse The Spaces  /  2 - Make a Reservation  /  3 - View My Reservation  /   4 - Cancel Reservation  /  5 - Exit");
            System.out.print("Your Choice :  ");
            choice = scannerGet.scanner.nextInt();
            getAnswer(choice);
        }
    }

    public void getAnswer (int choice) {
        if (choice == 1) {
            DB.printAvailableTables();
        }
        else if (choice == 2) {
            System.out.print("Enter the Table ID : ");
            int reserveChoice = scannerGet.scanner.nextInt();
            DB.reserveSpace(reserveChoice,username);
        }
        else if (choice == 3) {
            DB.myReservedSpace(username);
        }
        else if (choice == 4) {
            System.out.println("Enter the Canceling Table ID : ");
            int cancelChoice = scannerGet.scanner.nextInt();
            DB.cancelReservedSpace(username,cancelChoice);
        }
        else if (choice == 5) {
            menu mainMenu = new menu();
            mainMenu.showMenu();
        }
        else {
            System.out.println("Invalid Choice");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        menu appMenu = new menu();
        appMenu.showMenu();

    }
}