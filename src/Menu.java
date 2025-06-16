import java.util.InputMismatchException;
//interface MenuShow{
//    void showMenu();
//}
public class Menu {

     public void showMenu() {
        int choice=0;
        while(choice!=4) {
            System.out.println("Welcome To Coworking Space Reservation Application" +
                    "\n" + "Options: 1 - Admin Login  /  2 - User Login  /  3 - Exit");
            System.out.print("Your Choice :  ");
            try {
                choice = ScannerGet.scanner.nextInt();
                menuAnswer(choice);
            } catch (InputMismatchException inputMiss){
                System.out.println("Please enter a valid option");
            }

        }
    }

    void menuAnswer(int choice)  {

        switch (choice) {
            case 1: {
                AdminLog admin = new AdminLog();
                admin.adminLogin();
                break;
            }
            case 2: {
                UserLog user = new UserLog();
                try {
                    user.userLogin();
                } catch (EmptyNameException | NotExistedTableException e) {
                    System.out.println(e.getMessage());
                }
                break;

            }
            case 3: {
                DB.getInstance().spaceSaver();
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
