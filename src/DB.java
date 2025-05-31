import java.util.ArrayList;
import java.util.List;

public class DB {
    private static DB instance;


      private final List<String> spaces ;
      private final List<Boolean> books ;
      private final List<String> bookUsernames ;
      private int spaceCount;

     private DB() {
         this.spaces= new ArrayList<>();
         this.books = new ArrayList<>();
         this.bookUsernames = new ArrayList<>();
         this.spaceCount=0;
     }
     public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
         return instance;
     }

      void addSpace() {
        int currSpace = spaceCount + 1;
        spaces.add("Table " + spaceCount);
        System.out.println("New space added : Table " + currSpace);
        books.add(false);
        bookUsernames.add("-");
        spaceCount++;

    }

      void deleteSpace() {
        System.out.print("Enter space number: ");
        int choice = ScannerGet.scanner.nextInt();
        if(choice>=0 && choice<=spaces.size()) {
            spaces.remove(choice - 1);
            books.remove(choice - 1);
            bookUsernames.remove(choice - 1);
            organizeTable();
            spaceCount--;
        } else{
            System.out.println("Invalid space number");
        }
    }

      void organizeTable() {
        for (int i = 0; i < spaces.size(); i++) {
            spaces.set(i, "Table " + i);
        }

    }

      void printTable() {
        for (int i = 0; i < spaces.size(); i++) {
            if (books.get(i)==false) {
                System.out.println("Table " + (i + 1) + " : booked :  " + books.get(i));
            }else{
                System.out.println("Table " + (i + 1) + " : booked :  " + books.get(i) + " Username : " + bookUsernames.get(i));
            }
        }
    }

       void printAvailableTables() {
        for (int i = 0; i < spaces.size(); i++) {
            if (books.get(i)==false) {
                System.out.println("Table " + (i+1) + " is  available");
            }
        }
    }

      void reserveSpace(int choice, String username) {

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

      void myReservedSpace(String username) {
        for (int i = 0; i < spaces.size(); i++) {
            if(bookUsernames.get(i).equals(username)) {
                System.out.println("Table " + (i+1) + " is  reserved for yourself");
            }
        }
    }

      void cancelReservedSpace(String username, int choice) {
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
