package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoredNumbersTest {

    private StoredNumbers storedNumbersUnderTest;

    @BeforeEach
    void setUp() {
        storedNumbersUnderTest = new StoredNumbers();
        storedNumbersUnderTest.rows = 3;
        storedNumbersUnderTest.columns = 2;
    }

    @Test
    void testClicksPerSecond() {
        // Setup
        // Run the test
        storedNumbersUnderTest.clicksPerSecond(0, 0, 0);

        // Verify the results
    }

    @Test
    void testAverageCPS() {
        assertEquals(0.0, storedNumbersUnderTest.averageCPS(0));
        assertEquals(0.0, storedNumbersUnderTest.averageCPS(1));
    }
}
