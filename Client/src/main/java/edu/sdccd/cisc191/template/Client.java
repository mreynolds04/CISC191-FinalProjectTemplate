package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 8080);
            System.out.println("Connected to server");

            // Setup input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // Send requests to the server
            String userInput;
            do {
                // Display options to the client
                System.out.println("Choose an option:");
                System.out.println("1. Add income");
                System.out.println("2. Add expense");
                System.out.println("3. Add donation");
                System.out.println("4. Generate financial report");
                System.out.println("5. Quit");

                // Receive the user's choice from the console
                int choice = Integer.parseInt(consoleIn.readLine());

                // Send the user's choice to the server
                out.println(choice);

                // Handle the user's choice
                switch (choice) {
                    case 1:
                        // Takes values from income
                        // Prints these values and adds them up.
                        System.out.println("Enter income amount:");
                        double incomeAmount = Double.parseDouble(consoleIn.readLine());
                        if (incomeAmount <= 0) {
                            System.out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            System.out.println("$" + incomeAmount + " added to income");
                        }
                        break;

                    case 2:
                        // Takes values from expenses
                        // Prints these values and adds them up, subtracts from income
                        System.out.println("Enter expense amount:");
                        double expenseAmount = Double.parseDouble(consoleIn.readLine());
                        if (expenseAmount <= 0) {
                            System.out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            System.out.println("$" + expenseAmount + " added to expenses");
                        }
                        break;

                    case 3:
                        // Takes values from donations
                        // Prints these values and adds them up.
                        System.out.println("Enter donation amount:");
                        double donationAmount = Double.parseDouble(consoleIn.readLine());
                        if (donationAmount <= 0) {
                            System.out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            System.out.println("$" + donationAmount + " added to donations");
                        }
                        break;

                    case 4:
                        // Generate the financial report
                        String report = in.readLine();
                        System.out.println(report);
                        break;

                    case 5:
                        // Exit the loop
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose a number from 1 to 5.");
                        break;
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
