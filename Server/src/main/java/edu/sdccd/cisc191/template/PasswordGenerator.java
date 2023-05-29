package edu.sdccd.cisc191.template;

import java.util.*;

public class PasswordGenerator {
    private static final String SYMBOLS = "!@#$%^&*";
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 32;

    private static final Random random = new Random();

    public static String generateRandomPassword(int length, boolean includeSymbols, boolean includeNumbers, boolean includeLetters) {
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new IllegalArgumentException("Password length must be between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters.");
        }

        StringBuilder password = new StringBuilder();

        List<Character> validChars = new ArrayList<>();
        if (includeSymbols) {
            for (char c : SYMBOLS.toCharArray()) {
                validChars.add(c);
            }
        }
        if (includeNumbers) {
            for (char c : NUMBERS.toCharArray()) {
                validChars.add(c);
            }
        }
        if (includeLetters) {
            for (char c : LETTERS.toCharArray()) {
                validChars.add(c);
            }
        }

        if (validChars.isEmpty()) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.size());
            password.append(validChars.get(randomIndex));
        }

        return password.toString();
    }
}
