import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DB {
    private static DB instance;


      private Map<String,Integer> userReservationCount = new HashMap<>();
//      private final List<String> spaces ;
//      private final List<Boolean> books ;
//      private final List<String> bookUsernames ;
      private final List<Reservation<String>> reservations ;

      private int spaceCount;
      private final String FILE_NAME = "spaces.txt";


      private DB() {
        reservations = new ArrayList<>();
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
              for (int i = 0; i < reservations.size(); i++) {
                  String currSpace = "Table " + (i + 1) + "," + reservations.get(i).getBooked() + "," + reservations.get(i).getUsername();
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


                    if(currBook.equals("false")){

                        reservations.add(new Reservation<>(currSpace,false,currBookUsername));
                    } else if(currBook.equals("true")){
                        reservations.add(new Reservation<>(currSpace,true,currBookUsername));
                    }
                    spaceCount++;
                }

          } catch (IOException e){
              System.out.println("Error to read file");
          }
     }


      void addSpace() {

          reservations.add(new Reservation<>("Table " + spaceCount,false, "-"));
          System.out.println("New space added : Table " + spaceCount);
          spaceCount++;

    }

      void deleteSpace() {
        System.out.print("Enter space number: ");
        int choice = ScannerGet.scanner.nextInt();

          try{
              reservations.remove(choice - 1);
              organizeTable();
              spaceCount--;
          } catch (IndexOutOfBoundsException e){
              System.out.println("Space Number out of bounds");

        }
    }

void organizeTable() {

          for (int i = 0; i < reservations.size(); i++) {
        reservations.get(i).setSpaceName("Table"+i);
    }

}



    void printTable() {
        for (int i = 0; i < reservations.size(); i++) {
            if (!reservations.get(i).getBooked()) {
                System.out.println("Table " + (i + 1) + " : booked :  " + reservations.get(i).getBooked());
            }else{
                System.out.println("Table " + (i + 1) + " : booked :  " + reservations.get(i).getBooked() + " Username : " + reservations.get(i).getUsername());
            }
        }
    }



       void printAvailableTables() {
        for (int i = 0; i < reservations.size(); i++) {
            if (!reservations.get(i).getBooked()) {
                System.out.println("Table " + (i+1) + " is  available");
            }
        }
    }

      void reserveSpace(int choice, String username) throws NotExistedTableException {
          int currReservationCount = userReservationCount.getOrDefault(username,0);
          if(choice<=0 || choice>=reservations.size()) {
              throw new NotExistedTableException("There is no such space");
          } else{
              if(!reservations.get(choice - 1).getBooked()) {
                 if(currReservationCount<2) {
                     System.out.println("Table " + (choice) + " is  reserved");

                     userReservationCount.put(username,currReservationCount+1);

                     reservations.set(choice - 1, new Reservation<>(reservations.get(choice - 1).getSpaceName(),true, username ));
                 } else {
                     System.out.println("You already have reserved 2 space. You need to delete a reserved space first");
                 }

            } else {
                System.out.println("This book is already reserved by someone else");
            }
          }
    }

      void myReservedSpace(String username) {
          for (int i = 0; i < reservations.size(); i++) {
              Reservation<String> r = reservations.get(i);
              if (r.getUsername().equals(username)) {
                  System.out.println(r.getSpaceName() + " is reserved for " + username);
              }
          }
    }


      void cancelReservedSpace(String username, int choice) {
          int currReservationCount = userReservationCount.getOrDefault(username, 0);

          if (currReservationCount > 0) {
              if (choice <= 0 || choice > reservations.size()) {
                  System.out.println("Invalid table number.");
                  return;
              }

              Reservation<String> r = reservations.get(choice - 1);
              if (r.getUsername().equals(username)) {
                  reservations.set(choice - 1, new Reservation<>(r.getSpaceName(),false,"-" ));

                  int curr = userReservationCount.getOrDefault(username, 0);
                  userReservationCount.put(username, curr - 1);
                  System.out.println("Reservation for " + r.getSpaceName() + " cancelled.");
              } else {
                  System.out.println("You don’t own this reservation.");
              }
          } else {
              System.out.println("You don't have reserved space");
          }
      }
}
