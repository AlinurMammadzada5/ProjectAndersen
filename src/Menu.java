

public class Menu {

    void showMenu(){
        int choice=0;
        while(choice!=4) {
            System.out.println("Welcome To Coworking Space Reservation Application" +
                    "\n" + "Options: 1 - Admin Login  /  2 - User Login  /  3 - Exit");
            System.out.print("Your Choice :  ");
            choice = ScannerGet.scanner.nextInt();
            menuAnswer(choice);
        }
    }
    void menuAnswer(int choice){

        switch (choice) {
            case 1: {
                AdminLog admin = new AdminLog();
                admin.adminLogin();
                break;
            }
            case 2: {
                UserLog user = new UserLog();
                user.userLogin();
                break;
            }
            case 3: {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Invalid Choice");
                break;
            }
        }
    }
}
