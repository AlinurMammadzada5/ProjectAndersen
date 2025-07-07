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

    private DBConnect dbc;

    @BeforeEach
    void setUp() {
        dbc = DBConnect.getInstance();
    }

    @Test
    void testAddSpace() {
        int before = dbc.getSpaceCount();
        dbc.addSpace();
        int after = dbc.getSpaceCount();
        assertEquals(before + 1, after);
    }


@Test
 void testRemoveSpace() {
        int before = dbc.getSpaceCount();
      ScannerGet.scanner = new Scanner("1");
        dbc.deleteSpace();
       int after = dbc.getSpaceCount();
       assertEquals(before - 1, after);
    }

}
