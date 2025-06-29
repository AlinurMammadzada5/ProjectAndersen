package com.app;

import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args)  {


        DBConnect db= DBConnect.getInstance();
        String checkTable = "select Tablebool from Spaces where TableID = ?";

        Menu appMenu = new Menu();
        appMenu.showMenu();




    }
}