package com.example.automation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LoginAutomationTestRunner {
    
    @Test
    void testLoginAutomation() {
        // Test the LoginAutomationTest's testLogin method
        LoginAutomationTest loginTest = new LoginAutomationTest();
        
        assertDoesNotThrow(() -> loginTest.testLogin(), 
            "The testLogin method should not throw any exceptions.");
    }
}
