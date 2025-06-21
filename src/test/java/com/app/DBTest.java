package com.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBTest {

    private DB db;

    @BeforeEach
    void setUp() {
        db = DB.getInstance();
    }

    @Test
    void testAddSpace() {
        int before = db.getSpaceCount();
        db.addSpace();
        int after = db.getSpaceCount();
        assertEquals(before + 1, after);
    }


 @ParameterizedTest
 @ValueSource(ints={1})
 void testRemoveSpace(int space) {
        int before = db.getSpaceCount();
      ScannerGet.scanner = new Scanner(String.valueOf(space));
        db.deleteSpace();
       int after = db.getSpaceCount();
       assertEquals(before - 1, after);
    }

    private Reservation<String> getReservation(int index) {
        return db.getReservations().get(index);
    }
    @Test
    void testReserveSpace_SuccessfulReservation() throws NotExistedTableException {
        String username = "user1";

        db.reserveSpace(1, username);

        Reservation<String> res = getReservation(0);

        assertEquals(getReservation(0).getBooked(), res.getBooked());
    }




    @Test
    void testReserveSpace_FailsWhenAlreadyReserved() throws NotExistedTableException {
        String username1 = "user1";
        String username2 = "user2";

        db.reserveSpace(1, username1);
        db.reserveSpace(1, username2);

        Reservation<String> res = getReservation(0);
        assertEquals(username1, res.getUsername());
    }



}
