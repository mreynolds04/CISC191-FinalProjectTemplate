package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.PasswordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordGeneratorTest {

    @Test
    public void testGenerateRandomPassword() {
        // Test generating a password with symbols, numbers, and letters
        String password1 = PasswordGenerator.generateRandomPassword(10, true, true, true);
        Assertions.assertEquals(10, password1.length());

        // Test generating a password with symbols and numbers
        String password2 = PasswordGenerator.generateRandomPassword(8, true, true, false);
        Assertions.assertEquals(8, password2.length());

        // Test generating a password with letters only
        String password3 = PasswordGenerator.generateRandomPassword(12, false, false, true);
        Assertions.assertEquals(12, password3.length());

        // Test generating a password with an invalid length
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PasswordGenerator.generateRandomPassword(4, true, true, true);
        });

        // Test generating a password without selecting any character types
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PasswordGenerator.generateRandomPassword(6, false, false, false);
        });
    }
}
