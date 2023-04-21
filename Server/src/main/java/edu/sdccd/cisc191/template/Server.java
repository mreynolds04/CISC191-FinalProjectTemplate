package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");

            while (true) {
                // Accept a new client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                handleClient(clientSocket);

                // Close the client socket
                clientSocket.close();
            }

        } catch (IOException e) {
            System.err.println("Error while starting the server: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {

            // Initialize the modules
            Income income = new Income();
            Expense expense = new Expense();
            Donations donation = new Donations();

            boolean running = true;
            while (running) {
                // Display options to the client
                out.println("Choose an option:");
                out.println("1. Add income");
                out.println("2. Add expense");
                out.println("3. Add donation");
                out.println("4. Generate financial report");
                out.println("5. Quit");

                // Receive the user's choice from the client
                int choice = Integer.parseInt(in.readLine());

                // Handle the user's choice
                switch (choice) {
                    case 1:
                        // Takes values from income
                        // Prints these values and adds them up.
                        out.println("Enter income amount:");
                        double incomeAmount = Double.parseDouble(in.readLine());
                        if (incomeAmount <= 0) {
                            out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            income.transaction(incomeAmount);
                            out.println("$" + incomeAmount + " added to income");
                        }
                        break;

                    case 2:
                        // Takes values from expenses
                        // Prints these values and adds them up, subtracts from income
                        out.println("Enter expense amount:");
                        double expenseAmount = Double.parseDouble(in.readLine());
                        if (expenseAmount <= 0) {
                            out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            expense.transaction(expenseAmount);
                            out.println("$" + expenseAmount + " added to expenses");
                        }
                        break;

                    case 3:
                        // Takes values from donations
                        // Prints these values and adds them up.
                        out.println("Enter donation amount:");
                        double donationAmount = Double.parseDouble(in.readLine());
                        if (donationAmount <= 0) {
                            out.println("Invalid input. Please enter a number greater than zero.");
                        } else {
                            donation.transaction(donationAmount);
                            out.println("$" + donationAmount + " added to donations");
                        }
                        break;

                    case 4:
                        // Prints income, expense, and donation totals
                        out.println("Income total: $" + income.getTotalIncome());
                        out.println("Expense total: $" + expense.getTotalExpenses());
                        out.println("Donation total: $" + donation.getTotalDonations());
                        break;

                    case 5:
                        // Quit the program
                        out.println("Goodbye!");
                        running = false;
                        break;

                    default:
                        // Handle invalid input
                        out.println("Invalid input. Please enter a number between 1 and 5.");
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error while handling client request: " + e.getMessage());
        }
    }
}
