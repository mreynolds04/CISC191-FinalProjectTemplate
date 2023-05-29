package edu.sdccd.cisc191.template;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    public static String getWebsite(Scanner scanner) {
        System.out.print("Enter the website: ");
        return scanner.nextLine();
    }

    public static int getPasswordLength(Scanner scanner) {
        int length = 0;
        try {
            System.out.print("Enter the desired password length (5-32): ");
            length = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            while (length < 5 || length > 32) {
                System.out.println("Invalid input. Please enter a password length between 5 and 32.");
                System.out.print("Enter the desired password length (5-32): ");
                length = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please type an integer between 5 and 32.");
        }
        return length;
    }

    public static boolean shouldIncludeSymbols(Scanner scanner) {
        System.out.print("Include symbols? (yes/no): ");
        String response = scanner.nextLine();
        while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            System.out.print("Include symbols? (yes/no): ");
            response = scanner.nextLine();
        }
        return response.equalsIgnoreCase("yes");
    }

    public static boolean shouldIncludeNumbers(Scanner scanner) {
        System.out.print("Include numbers? (yes/no): ");
        String response = scanner.nextLine();
        while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            System.out.print("Include numbers? (yes/no): ");
            response = scanner.nextLine();
        }
        return response.equalsIgnoreCase("yes");
    }

    public static boolean shouldIncludeLetters(Scanner scanner) {
        System.out.print("Include letters? (yes/no): ");
        String response = scanner.nextLine();
        while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            System.out.print("Include letters? (yes/no): ");
            response = scanner.nextLine();
        }
        return response.equalsIgnoreCase("yes");
    }

    public static String confirmUsePassword(Scanner scanner, String website) {
        System.out.println("Do you want to use this password for " + website + "? (yes/no)");
        String response = scanner.nextLine();
        while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            System.out.println("Do you want to use this password for " + website + "? (yes/no)");
            response = scanner.nextLine();
        }
        return response;
    }
}
