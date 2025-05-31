

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

        switch (choice) {
            case 1:db.addSpace();
            break;
            case 2:db.deleteSpace();
            break;
            case 3:db.printTable();
            break;
            case 4:break;
            default:System.out.println("Invalid Choice");
            break;
        }
        }
    }

