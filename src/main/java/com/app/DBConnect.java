package com.app;

import java.sql.*;

public class DBConnect {



    private static final String URL = "jdbc:postgresql://localhost:5432/CoworkingSpaceDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Onlyeagle2.";
    private Connection conn;
    private int spaceCount;

    private static DBConnect instance;

    private DBConnect() {
        connect();
        spaceCount = 0;
    }

    public int getSpaceCount() {
        String countQuery = "SELECT COUNT(*) FROM space";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(countQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            spaceCount = resultSet.getInt(1);
            System.out.println(resultSet);
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
              return spaceCount;

    }

    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    public void connect() {
        try {
             conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e.getMessage());
        }
    }


    void printTable() {
  String printSpaces = "select * from Spaces";
  try {
      PreparedStatement preparedStatement = conn.prepareStatement(printSpaces);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
          String tableName  ="Table "+ rs.getString("TableID");
          boolean reservationCondition = Boolean.parseBoolean(rs.getString("Tablebool"));
          String username =  rs.getString("Username");

          System.out.println(tableName + " : " + reservationCondition + " : " + username);
      }

  } catch (SQLException sqlException) {
      System.out.println("Printing table failed!");
  }
    }


    void addSpace() {
        String addTable = "insert into Spaces (Tablebool,username) values(?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(addTable);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2,"-");
            preparedStatement.executeUpdate();
            System.out.println("New space added");
            //spaceCount++;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    void deleteSpace() {
        System.out.print("Enter space number: ");
        int choice = ScannerGet.scanner.nextInt();
        String deleteTable = "DELETE FROM Spaces WHERE TableID = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(deleteTable);
            preparedStatement.setInt(1, choice);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Space " + choice + " deleted");
                //spaceCount--;
            } else {
                System.out.println("This space does not exist.");
            }

        } catch (SQLException s) {
            System.out.println("SQL Error: " + s.getMessage());
        }
    }

    void printAvailableTables() {

    String printAvailableSpaces = "select * from Spaces where Tablebool = ?";
    try {
       PreparedStatement preparedStatement = conn.prepareStatement(printAvailableSpaces);
       preparedStatement.setBoolean(1, false);
       ResultSet rs = preparedStatement.executeQuery();
       while (rs.next()) {
           System.out.println("Table "+rs.getString("TableID") + " : Available" );
       }
    } catch (SQLException ex){
        System.out.println(ex.getMessage());
    }

    }

    void reserveSpace(int choice,String username) throws NotExistedTableException {
        int currReserveCount = countReservations(username);
        String checkTable = "select Tablebool from Spaces where TableID = ?";
        String reserveTable = "update Spaces set Tablebool = ? , Username = ? where TableID = ?";
        try {

              PreparedStatement preparedStatement = conn.prepareStatement(checkTable);    // this is for checking availability
              PreparedStatement preparedStatement2 = conn.prepareStatement(reserveTable); // This if for reserving
              preparedStatement.setInt(1, choice);
              preparedStatement2.setBoolean(1, true);
              preparedStatement2.setString(2, username);
              preparedStatement2.setInt(3, choice);
              ResultSet rs = preparedStatement.executeQuery();
              if(rs.next()) {
                  boolean reservationCondition = Boolean.parseBoolean(rs.getString("Tablebool"));
                  if(reservationCondition) {
                  System.out.println("Reservation "+choice + " is already reserved by someone else");
              } else{
                if(currReserveCount<2){
                preparedStatement2.executeUpdate();
                System.out.println("Table "+choice +" is reserved for "+username);
                } else {
                  System.out.println("You Already Reserved two Spaces, You can reserve 2 spaces maximum.");
                 }
                    }
              } else {
                  System.out.println("This space does not exist.");
              }

        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


    int countReservations(String username) {
        String countReservations = "SELECT COUNT(*) FROM Spaces WHERE Username = ?";
        int total=0;
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(countReservations);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
               total = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
             return total;
         }

    void myReservedSpace(String username) {
       String myTables = "select TableID from Spaces where Username = ?";
       try {
           PreparedStatement preparedStatement = conn.prepareStatement(myTables);
           preparedStatement.setString(1, username);
           ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()) {
               System.out.println("Table " +rs.getString("TableID"));
           }
       } catch (SQLException ex){
           System.out.println(ex.getMessage());
       }
    }

    void cancelReservation(int choice, String username) {
        String checkTable = "select Tablebool from Spaces where TableID = ? and Username = ?" ;
        String cancelTable = "update Spaces set Tablebool = ?,Username=? where TableID = ?";

        try {
            PreparedStatement checkTablePreparedStatement = conn.prepareStatement(checkTable);
            checkTablePreparedStatement.setInt(1,choice);
            checkTablePreparedStatement.setString(2, username);
           ResultSet rs = checkTablePreparedStatement.executeQuery();
             if(rs.next()) {
                 boolean checkTableResult = rs.getBoolean("Tablebool");
                 if(checkTableResult) {

                     PreparedStatement preparedStatement = conn.prepareStatement(cancelTable);
                     preparedStatement.setBoolean(1,false);
                     preparedStatement.setString(2,"-");
                     preparedStatement.setInt(3,choice);
                     preparedStatement.executeUpdate();
                     System.out.println("Table "+choice +" cancelled");

                 } else {
                     System.out.println("This space does not exist or does not belong to the user.");
                 }

             }
        }
            catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    }







