package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.UserInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class UserInputTest {
    @Test
    public void testGetWebsite() {
        String input = "example.com\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        String website = UserInput.getWebsite(scanner);

        Assertions.assertEquals("example.com", website);
    }

    @Test
    public void testGetPasswordLength_ValidInput() {
        String input = "10\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        int passwordLength = UserInput.getPasswordLength(scanner);

        Assertions.assertEquals(10, passwordLength);
    }

    @Test
    public void testGetPasswordLength_InvalidInput() {
        String input = "4\n10\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        int passwordLength = UserInput.getPasswordLength(scanner);

        Assertions.assertEquals(10, passwordLength);
    }

    @Test
    public void testShouldIncludeSymbols_Yes() {
        String input = "yes\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        boolean includeSymbols = UserInput.shouldIncludeSymbols(scanner);

        Assertions.assertTrue(includeSymbols);
    }

    @Test
    public void testShouldIncludeSymbols_No() {
        String input = "no\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        boolean includeSymbols = UserInput.shouldIncludeSymbols(scanner);

        Assertions.assertFalse(includeSymbols);
    }

    @Test
    public void testShouldIncludeNumbers_Yes() {
        String input = "yes\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        boolean includeNumbers = UserInput.shouldIncludeNumbers(scanner);

        Assertions.assertTrue(includeNumbers);
    }

    @Test
    public void testShouldIncludeNumbers_No() {
        String input = "no\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        boolean includeNumbers = UserInput.shouldIncludeNumbers(scanner);

        Assertions.assertFalse(includeNumbers);
    }

    @Test
    public void testShouldIncludeLetters_Yes() {
        String input = "yes\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        boolean includeLetters = UserInput.shouldIncludeLetters(scanner);

        Assertions.assertTrue(includeLetters);
    }

    @Test
    public void testShouldIncludeLetters_No() {
        String input = "no\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        boolean includeLetters = UserInput.shouldIncludeLetters(scanner);

        Assertions.assertFalse(includeLetters);
    }

    @Test
    public void testConfirmUsePassword_Yes() {
        String input = "yes\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        String response = UserInput.confirmUsePassword(scanner, "example.com");

        Assertions.assertEquals("yes", response);
    }

    @Test
    public void testConfirmUsePassword_No() {
        String input = "no\n";
        provideInput(input);

        Scanner scanner = new Scanner(System.in);
        String response = UserInput.confirmUsePassword(scanner, "example.com");

        Assertions.assertEquals("no", response);
    }

    private void provideInput(String data) {
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }
}
