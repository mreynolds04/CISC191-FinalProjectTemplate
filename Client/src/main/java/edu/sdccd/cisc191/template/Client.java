package edu.sdccd.cisc191.template;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    // MAIN CLASS FOR THE PROGRAM
    // Gathers all the data from Income, Expense, and Donations

    // PROGRAM USES MODULE 1, 4, 5, 6, 7
    public static void main(String[] args) throws Exception {
        // Declares variables and allows for user to input the amounts (module 5)
        Scanner sc = new Scanner(System.in);
        Income income = new Income();
        Expense expense = new Expense();
        Donations donation = new Donations();

        // Connect to server
        Socket socket = new Socket("localhost", 8080);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        boolean running = true;
        while (running) {
            // Displays all options
            System.out.println("Choose an option:");
            System.out.println("1. Add income");
            System.out.println("2. Add expense");
            System.out.println("3. Add donation");
            System.out.println("4. Generate financial report");
            System.out.println("5. Quit");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        // Takes values from income
                        // Prints these values and adds them up.
                        System.out.println("Enter income amount:");
                        double incomeAmount = sc.nextDouble();
                        if (incomeAmount <= 0) {
                            System.out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            income.transaction(incomeAmount);
                            System.out.println("$" + incomeAmount + " added to income");
                            System.out.println("Current income balance: $" + income.getTotalIncome());
                        }
                        break;

                    case 2:
                        // Takes values from expenses
                        // Prints these values and adds them up, subtracts from income
                        System.out.println("Enter expense amount:");
                        double expenseAmount = sc.nextDouble();
                        if (expenseAmount <= 0) {
                            System.out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            expense.transaction(expenseAmount);
                            System.out.println("$" + expenseAmount + " added to expenses");
                            System.out.println("Current expense balance: $" + expense.getTotalExpenses());
                        }
                        break;

                    case 3:
                        // Takes values from donations
                        // Prints these values and adds them up, subtracts from income
                        // Displays which individual charities you donated to, along with the amount for each
                        System.out.println("Enter donation amount:");
                        double donationAmount = sc.nextDouble();
                        if (donationAmount <= 0) {
                            System.out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            donation.transaction(donationAmount);
                            System.out.println("$" + donationAmount + " donated to charity");
                            System.out.println("Current donation balance: $" + donation.getTotalDonations());
                        }
                        break;

                    case 4:
                        // gets all values that were gathered
                        double totalIncome = income.getTotalIncome();
                        double totalExpenses = expense.getTotalExpenses();
                        double totalDonations = donation.getTotalDonations();

                        // Show individual totals, along with the grand balance
                        System.out.println("Total income: $" + totalIncome);
                        System.out.println("-----------");
                        System.out.println("Total expenses: $" + totalExpenses);
                        System.out.println("-----------");
                        System.out.println("Total donations: $" + totalDonations);

                        donation.printCharities();
                        System.out.println("-----------");

                        // Adds and subtracts all respective values and displays them
                        double finalBalance = totalIncome - totalExpenses - totalDonations;
                        double totalSpending = totalExpenses + totalDonations;

                        // Final balance is displayed
                        // Shows the status message of how the user is doing in regards to spending and saving
                        System.out.println("Final balance: $" + finalBalance);
                        if (finalBalance < totalSpending) {
                            System.out.println("You are spending more than you are earning. Consider reducing expenses or increasing income.");
                        } else if (finalBalance > totalSpending) {
                            System.out.println("You are saving more than you are spending. Good job!");
                        } else if (finalBalance < 0) {
                            System.out.println("You have an overdraft. You may be subject to overdraft fees.");
                        }
                        break;

                    case 5:
                        // Exits program
                        running = false;
                        break;

                    default:
                        // invalid choice, shows menu again
                        System.out.println("Invalid choice.");
                }
            }
            catch (InputMismatchException e) {
                // if a letter or special character is included in the input, this exception is handled
                // it prompts the user to enter a valid input
                // only integers are allowed when the program asks which choice

                System.out.println("Invalid input. Please enter a number (integer or two decimals).");
                sc.next();
            }
        }
    }
}
