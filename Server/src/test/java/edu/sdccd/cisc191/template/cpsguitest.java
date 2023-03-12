package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class cpsguitest {

    private cpsGUI cpsGUIUnderTest;

    @BeforeEach
    void setUp() {
        cpsGUIUnderTest = new cpsGUI();
        cpsGUIUnderTest.clickCounter = 0;
        cpsGUIUnderTest.timeStart = false;
        cpsGUIUnderTest.gameOver = false;
        cpsGUIUnderTest.player = 0;
        cpsGUIUnderTest.attemptNum = 0;
    }

    @Test
    void testMain() {
        // Setup
        // Run the test
        cpsGUI.main(new String[]{"args"});

        // Verify the results
    }
}