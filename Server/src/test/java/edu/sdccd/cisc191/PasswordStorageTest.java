package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.PasswordStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordStorageTest {

    @Test
    public void testGetPassword() {
        PasswordStorage passwordStorage = new PasswordStorage();

        // Add some passwords for testing
        passwordStorage.savePassword("website1", "password1");
        passwordStorage.savePassword("website2", "password2");
        passwordStorage.savePassword("website3", "password3");

        // Test retrieving existing passwords
        Assertions.assertEquals("password1", passwordStorage.getPassword("website1"));
        Assertions.assertEquals("password2", passwordStorage.getPassword("website2"));
        Assertions.assertEquals("password3", passwordStorage.getPassword("website3"));

        // Test retrieving non-existent password
        Assertions.assertNull(passwordStorage.getPassword("website4"));
    }
}
