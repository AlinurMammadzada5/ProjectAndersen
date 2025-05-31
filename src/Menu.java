

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

        if(choice == 1){
            AdminLog admin = new AdminLog();
            admin.adminLogin();
        }
        else if(choice == 2){
            UserLog user = new UserLog();
            user.userLogin();
        }
        else if(choice == 3){
            System.exit(0);
        } else {
            System.out.println("Invalid Choice");
        }
    }
}
