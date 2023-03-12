package edu.sdccd.cisc191.template;

/**
 * class is used for storing values in a 3 x 2 array.
 * values are used to calculate the averages of each player
 * averages are sent to cpsGUI to be outputted
 * StoredNumbersInt implements the methods in this class.
 */
public class StoredNumbers implements StoredNumbersInt {
    int rows = 3; // attempts per player
    int columns = 2; // number of players

    // NumbersStored array is declared as a double
    public static double[][] NumbersStored;

    StoredNumbers() {
        NumbersStored = new double[rows][columns];
    }

    // calculates the clicks per second for each attempt
    @Override
    public void clicksPerSecond(int clickCounter, int player, int attemptNum) {
        NumbersStored[attemptNum][player] = (double) clickCounter / 15.0;

        // optional display in the console of each attempt's clicks per second (mainly for testing purposes)
        System.out.println(NumbersStored[attemptNum][player]);
    }

    // method calculates the average clicks per second for each player
    // values are passed to cpsGUI to be displayed
    @Override
    public double averageCPS(int player) {
        double sum = 0.0;
        double average = 0.0;

        for (int i = 1; i < rows; i++) {
            sum += NumbersStored[i][player];
        }
        average = sum / rows;
        return average;
    }
}
