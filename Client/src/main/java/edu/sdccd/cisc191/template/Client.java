package edu.sdccd.cisc191.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to server.");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to The Best Password Manager Ever!");

            boolean generateMorePasswords = true;

            while (generateMorePasswords) {
                System.out.println("\nPlease select an option:");
                System.out.println("1. Generate and save a password");
                System.out.println("2. Look up a saved password");
                System.out.println("3. Add a new password manually");
                System.out.println("4. Change a saved password");
                System.out.println("5. Remove a saved password");
                System.out.println("6. Quit");

                int option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                String request;
                switch (option) {
                    case 1:
                        // Generate and save a password
                        String website = getPasswordFromUser(scanner, "Enter the website: ");
                        int length = getPasswordLengthFromUser(scanner);
                        boolean includeSymbols = shouldIncludeSymbolsFromUser(scanner);
                        boolean includeNumbers = shouldIncludeNumbersFromUser(scanner);
                        boolean includeLetters = shouldIncludeLettersFromUser(scanner);
                        request = "GENERATE:" + website + ":" + length + ":" + includeSymbols + ":" + includeNumbers + ":" + includeLetters;
                        break;

                    case 2:
                        // Look up a saved password
                        website = getPasswordFromUser(scanner, "Enter the website to look up the password: ");
                        request = "RETRIEVE:" + website;
                        break;

                    case 3:
                        // Add a new password manually
                        website = getPasswordFromUser(scanner, "Enter the website: ");
                        String password = getPasswordFromUser(scanner, "Enter the password: ");
                        request = "STORE:" + website + ":" + password;
                        break;

                    case 4:
                        // Change a saved password
                        website = getPasswordFromUser(scanner, "Enter the website to change the password: ");
                        password = getPasswordFromUser(scanner, "Enter the new password: ");
                        request = "CHANGE:" + website + ":" + password;
                        break;

                    case 5:
                        // Remove a saved password
                        website = getPasswordFromUser(scanner, "Enter the website to remove the password: ");
                        request = "DELETE:" + website;
                        break;

                    case 6:
                        // Quit the program
                        request = "QUIT";
                        generateMorePasswords = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        continue;
                }

                writer.println(request);

                String response = reader.readLine();
                System.out.println("\nServer response: " + response);

                if (option == 6) {
                    System.out.println("Disconnected from server.");
                    break;
                }
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPasswordFromUser(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getPasswordLengthFromUser(Scanner scanner) {
        System.out.print("Enter the password length: ");
        return scanner.nextInt();
    }

    private static boolean shouldIncludeSymbolsFromUser(Scanner scanner) {
        System.out.print("Include symbols? (true/false): ");
        return scanner.nextBoolean();
    }

    private static boolean shouldIncludeNumbersFromUser(Scanner scanner) {
        System.out.print("Include numbers? (true/false): ");
        return scanner.nextBoolean();
    }

    private static boolean shouldIncludeLettersFromUser(Scanner scanner) {
        System.out.print("Include letters? (true/false): ");
        return scanner.nextBoolean();
    }
}
