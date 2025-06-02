import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DB {
    private static DB instance;



      private final List<String> spaces ;
      private final List<Boolean> books ;
      private final List<String> bookUsernames ;
      private int spaceCount;
      private    FileWriter spacesText;
private final String FILE_NAME = "spaces.txt";


      private DB() {
         this.spaces= new ArrayList<>();
         this.books = new ArrayList<>();
         this.bookUsernames = new ArrayList<>();
         this.spaceCount=1;
         try {
             readFile();
         } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("You can add spaces first");
         }

     }



     public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
         return instance;
     }




     void spaceSaver(){
          try(FileWriter spacesText = new FileWriter(FILE_NAME)) {
              for (int i = 0; i < spaces.size(); i++) {
                  String currSpace = "Table " + (i + 1) + "," + books.get(i) + "," + bookUsernames.get(i);
                  spacesText.write(currSpace+"\n");

              }
          }catch (IOException e) {
              System.out.println("Error to save Table files");
          }
     }

     void readFile(){
          try(BufferedReader spaceReader = new BufferedReader(new FileReader("spaces.txt"))){
                String currLine;
                while ((currLine = spaceReader.readLine()) != null) {
                    String currSpace = currLine.split(",")[0];
                    String currBook = currLine.split(",")[1];
                    String currBookUsername = currLine.split(",")[2];
                    spaces.add(currSpace);
                    if(currBook.equals("false")){
                        books.add(false);
                    } else if(currBook.equals("true")){
                        books.add(true);
                    }
                    bookUsernames.add(currBookUsername);
                    spaceCount++;
                }

          } catch (IOException e){
              System.out.println("Error to read file");
          }
     }


      void addSpace() {

          spaces.add("Table " + spaceCount);
          books.add(false);
          bookUsernames.add("-");
          System.out.println("New space added : Table " + spaceCount);
          spaceSaver();
           spaceCount++;

    }

      void deleteSpace() {
        System.out.print("Enter space number: ");
        int choice = ScannerGet.scanner.nextInt();

          try{
              spaces.remove(choice - 1);
              books.remove(choice - 1);
              bookUsernames.remove(choice - 1);
              organizeTable();
              spaceSaver();
              spaceCount--;
          } catch (IndexOutOfBoundsException e){
              System.out.println("Space Number out of bounds");

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

      void reserveSpace(int choice, String username) throws NotExistedTableException {

          if(choice<=0 || choice>=spaces.size()) {
              throw new NotExistedTableException("There is no such space");
          } else{
              if(books.get(choice-1)==false) {
                System.out.println("Table " + (choice) + " is  reserved");
                books.set(choice-1, true);
                bookUsernames.set(choice-1, username);
                spaceSaver();


            } else {
                System.out.println("This book is already reserved by someone else");
            }
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

          try {
              final int numberInList =  choice-1;
              if(books.get(numberInList)==true) {
                  if (bookUsernames.get(numberInList).equals(username)) {
                      System.out.println("Table " + choice + " is  cancelled for yourself");
                      books.set(numberInList, false);
                      bookUsernames.set(numberInList, "-");
                      spaceSaver();
                  }
          }else {
                  System.out.println("This book is not for you. Can't cancel this book");
              }

        } catch (IndexOutOfBoundsException e) {
              System.out.println("Index you entered is invalid. Try again");
          }
    }
}
