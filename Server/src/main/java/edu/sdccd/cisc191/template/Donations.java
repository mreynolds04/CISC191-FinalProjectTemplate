package edu.sdccd.cisc191.template;

import java.util.Scanner;
// Ask user to input all the charities they donated to through the week
// must be > 0, can be decimals
// module 1, 5, 6, 7

public class Donations implements FinanceInt {
    public double[] donations;
    public String[] charities;

    public Donations() {
        donations = new double[0];
        charities = new String[0];
    }

    @Override
    public void transaction(double amount) {
        Scanner inputCharity = new Scanner(System.in);
        System.out.println("Which charity are you donating to? ");
        String charityName = inputCharity.nextLine();

        double[] newDonations = new double[donations.length + 1];
        String[] newCharities = new String[charities.length + 1];
        for (int i = 0; i < donations.length; i++) {
            newDonations[i] = donations[i];
            newCharities[i] = charities[i];
        }
        newDonations[donations.length] = amount;
        newCharities[charities.length] = charityName;
        donations = newDonations;
        charities = newCharities;
    }

    public double getTotalDonations() {
        double totalDonations = 0;
        for (double amount : donations) {
            totalDonations += amount;
        }
        return totalDonations;
    }
    public void printCharities() {
        if (charities.length == 0) {
            System.out.println("No charities found.");
        } else {
            System.out.println("Charities donated to:");
            for (int i = 0; i < charities.length; i++) {
                System.out.println(charities[i] + ": $" + donations[i]);
            }
        }
    }
}
