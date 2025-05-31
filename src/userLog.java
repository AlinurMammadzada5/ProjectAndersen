public class userLog {
    String username;
    DB db =DB.getInstance();



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
           db.printAvailableTables();
        }
        else if (choice == 2) {
            System.out.print("Enter the Table ID : ");
            int reserveChoice = scannerGet.scanner.nextInt();
            db.reserveSpace(reserveChoice,username);
        }
        else if (choice == 3) {
            db.myReservedSpace(username);
        }
        else if (choice == 4) {
            System.out.println("Enter the Canceling Table ID : ");
            int cancelChoice = scannerGet.scanner.nextInt();
            db.cancelReservedSpace(username,cancelChoice);
        }
        else {
            System.out.println("Invalid Choice");
        }
        }
    }

