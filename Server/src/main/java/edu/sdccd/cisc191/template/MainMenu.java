package edu.sdccd.cisc191.template;

import java.util.Scanner;

public class MainMenu {
// Modules 2, 4, 7, 8, 10
    private static PasswordStorage PasswordStorage;

    public static void main(String[] args) {
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

            switch (option) {
                case 1:
                    // Generate and save a password
                    String website = UserInput.getWebsite(scanner);
                    int length = UserInput.getPasswordLength(scanner);
                    boolean includeSymbols = UserInput.shouldIncludeSymbols(scanner);
                    boolean includeNumbers = UserInput.shouldIncludeNumbers(scanner);
                    boolean includeLetters = UserInput.shouldIncludeLetters(scanner);

                    String password = PasswordGenerator.generateRandomPassword(length, includeSymbols, includeNumbers, includeLetters);
                    System.out.println("Generated password: " + password);

                    String response = UserInput.confirmUsePassword(scanner, website);
                    while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        response = scanner.nextLine();
                    }

                    if (response.equalsIgnoreCase("yes")) {
                        edu.sdccd.cisc191.template.PasswordStorage.savePassword(website, password);
                        System.out.println("Password saved for " + website + ".");
                    } else {
                        System.out.println("Password not saved.");
                    }
                    break;

                case 2:
                    // Look up a saved password
                    System.out.print("Enter the website to look up the password: ");
                    website = scanner.nextLine();
                    String savedPassword = edu.sdccd.cisc191.template.PasswordStorage.getPassword(website);
                    if (savedPassword != null) {
                        System.out.println("Password for " + website + ": " + savedPassword);
                    } else {
                        System.out.println("No password found for " + website + ".");
                    }
                    break;

                case 3:
                    // Add a new password manually
                    System.out.print("Enter the website: ");
                    website = scanner.nextLine();
                    System.out.print("Enter the password: ");
                    password = scanner.nextLine();
                    edu.sdccd.cisc191.template.PasswordStorage.savePassword(website, password);
                    System.out.println("Password saved for " + website + ".");
                    break;

                case 4:
                    // Change a saved password
                    System.out.print("Enter the website to change the password: ");
                    website = scanner.nextLine();
                    System.out.print("Enter the new password: ");
                    password = scanner.nextLine();
                    boolean passwordChanged = edu.sdccd.cisc191.template.PasswordStorage.changePassword(website, password);
                    if (passwordChanged) {
                        System.out.println("Password for " + website + " changed successfully.");
                    } else {
                        System.out.println("No password found for " + website + ".");
                    }
                    break;

                case 5:
                    // Remove a saved password
                    System.out.print("Enter the website to remove the password: ");
                    website = scanner.nextLine();
                    boolean passwordRemoved = edu.sdccd.cisc191.template.PasswordStorage.removePassword(website);
                    if (passwordRemoved) {
                        System.out.println("Password for " + website + " removed successfully.");
                    } else {
                        System.out.println("No password found for " + website + ".");
                    }
                    break;

                case 6:
                    // Quit the program
                    generateMorePasswords = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for using the Best Password Manager Ever!");
        scanner.close();
    }

    public static void setPasswordStorage(PasswordStorage passwordStorage) {
        PasswordStorage = passwordStorage;
    }
}
