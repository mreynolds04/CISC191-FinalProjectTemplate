package edu.sdccd.cisc191.template;

// Ask user to input all the ways money was spent through the week (purchases, fines, etc)
// must be > 0, can be decimals
// module 1, 5, 6, 7

public class Expense implements FinanceInt {
    public double[] expenses;
    public Expense() {
        expenses = new double[0];
    }

    @Override
    public void transaction(double amount) {
        double[] newExpenses = new double[expenses.length + 1];
        for (int i = 0; i < expenses.length; i++) {
            newExpenses[i] = expenses[i];
        }
        newExpenses[expenses.length] = amount;
        expenses = newExpenses;
    }

    public double getTotalExpenses() {
        double totalExpenses = 0;
        for (double amount : expenses) {
            totalExpenses += amount;
        }
        return totalExpenses;
    }
}