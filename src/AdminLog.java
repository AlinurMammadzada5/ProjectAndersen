

public class AdminLog {

DB db =DB.getInstance();
    void adminLogin(){
        System.out.println("Admin Login Successful...");
        int choice=0;
        while (choice!=4) {
            System.out.println("Options : 1 - Add a new Coworking Space  /  2 - Remove a Coworking Space  /  3 - View Spaces  /   4 - Exit");
            System.out.print("Your Choice :  ");
            choice = ScannerGet.scanner.nextInt();
            getAnswer(choice);
        }



    }
    public void getAnswer(int choice) {

        if (choice == 1) {
            db.addSpace();
        } else if (choice == 2) {
            db.deleteSpace();
        } else if (choice == 3) {
            db.printTable();
        } else {
            System.out.println("Invalid Choice");

        }
        }
    }

