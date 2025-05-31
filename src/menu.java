

public class menu {

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
