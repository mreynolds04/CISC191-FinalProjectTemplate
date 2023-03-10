package edu.sdccd.cisc191.template;

public class StoredNumbers implements StoredNumbersInt {
    protected int clickCounter;
    int rows = 3; // attempts per player
    int columns = 2; // number of players
    public static int[][] NumbersStored;

    StoredNumbers() {
        NumbersStored = new int[rows][columns];
    }

    @Override
    public void clicksPerSecond(int clickCounter, int player, int attemptNum) {
        NumbersStored[attemptNum][player] = clickCounter / 15;
    }

    @Override
    public int averageCPS(int player) {
        int sum = 0;
        int average = 0;

        for (int i = 0; i < rows; i++) {
            sum += NumbersStored[i][player];
        }
        average = sum / rows;
        return average;
    }
}
