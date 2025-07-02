package com.app;



public class AdminLog {


//DBConnect dbc = DBConnect.getInstance();
private final SpacesOperation so= SpacesOperation.getInstance();
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
            case 1:so.addSpace();
            break;
            case 2: System.out.print("Enter space number to delete: ");
                int id = ScannerGet.scanner.nextInt();
                so.deleteSpace(id);
                System.out.println("Space deleted if it existed.");
                break;

            case 3:
               so.getAllSpaces();
                break;
            case 4: so.em.close(); break;
            default:System.out.println("Invalid Choice");
            break;
        }
        }
    }

