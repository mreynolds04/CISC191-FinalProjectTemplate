package edu.sdccd.cisc191.template;

// Ask user to input all the ways they received income through the week (paycheck, cash given, etc.)
// must be > 0, can be decimals
// module 1, 5, 6, 7

public class Income implements FinanceInt {
    public double[] income;
    public Income() {
        income = new double[0];
    }

    @Override
    public void transaction(double amount) {
        double[] newIncome = new double[income.length + 1];
        for (int i = 0; i < income.length; i++) {
            newIncome[i] = income[i];
        }
        newIncome[income.length] = amount;
        income = newIncome;
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        for (double amount : income) {
            totalIncome += amount;
        }
        return totalIncome;
    }
}