package edu.sdccd.cisc191.template;

public class PasswordStorage {
    private static final int MAX_PASSWORDS = 3;
    private static final String[][] passwords = new String[MAX_PASSWORDS][2];
    private static int passwordCount = 0;

    public static void savePassword(String website, String password) {
        if (passwordCount >= MAX_PASSWORDS) {
            System.out.println("Maximum number of passwords reached. Cannot save password.");
            return;
        }

        passwords[passwordCount][0] = website;
        passwords[passwordCount][1] = password;
        passwordCount++;

        System.out.println("Password saved. You now have " + passwordCount + " passwords saved.");
    }

    public static void printPasswords() {
        System.out.println("Stored passwords:");
        for (int i = 0; i < passwordCount; i++) {
            System.out.println("Website: " + passwords[i][0] + ", Password: " + passwords[i][1]);
        }
    }

    public static String getPassword(String website) {
        for (int i = 0; i < passwordCount; i++) {
            if (passwords[i][0].equalsIgnoreCase(website)) {
                return passwords[i][1];
            }
        }
        return null; // Password not found
    }

    public static boolean changePassword(String website, String newPassword) {
        for (int i = 0; i < passwordCount; i++) {
            if (passwords[i][0].equalsIgnoreCase(website)) {
                passwords[i][1] = newPassword;
                return true;
            }
        }
        return false; // Password not found
    }

    public static boolean removePassword(String website) {
        for (int i = 0; i < passwordCount; i++) {
            if (passwords[i][0].equalsIgnoreCase(website)) {
                // Move the last password to the current position to maintain order
                passwords[i][0] = passwords[passwordCount - 1][0];
                passwords[i][1] = passwords[passwordCount - 1][1];
                passwordCount--;
                return true;
            }
        }
        return false; // Password not found
    }
}
