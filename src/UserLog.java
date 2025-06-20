

public class UserLog {
    String username;
    DB db =DB.getInstance();



    void userLogin() throws EmptyNameException, NotExistedTableException {

            System.out.print("Enter Username: ");
            ScannerGet.scanner.nextLine();
            username = ScannerGet.scanner.nextLine();
            if(username.isEmpty()){
                throw new EmptyNameException("Empty Username is not Accepted");
            } else {
                System.out.println("User Login Successful...");
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
                db.printAvailableTables();
                break;
            }
            case 2:{
                System.out.print("Enter the Table ID : ");
                int reserveChoice = ScannerGet.scanner.nextInt();
                  try {
                      db.reserveSpace(reserveChoice,username);
                  } catch (NotExistedTableException e) {
                      System.out.println(e.getMessage());
                  }

                break;
            }
            case 3:{
                db.myReservedSpace(username);
                break;
            }
            case 4:{
                System.out.println("Enter the Canceling Table ID : ");
                int cancelChoice = ScannerGet.scanner.nextInt();
                db.cancelReservedSpace(username,cancelChoice);
                break;
            }
            case 5:break;
            default:{
                System.out.println("Invalid Option");
                break;
            }
        }
        }
    }

