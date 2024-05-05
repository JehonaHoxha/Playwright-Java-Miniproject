package tests;

import com.nopcommerce.base.ScriptBase;
import org.junit.jupiter.api.*;
import pages.CommonElements;
import pages.LoginPage;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.*;

public class TC01RegisterTest extends ScriptBase {
    String expectedPageTitle = "Register";
    String firstName = "testname";
    String lastName = "testlastname";
    String email = "test2@gmail.com";
    String password = "dummyPassword";

    @Test
    @DisplayName("Register Test")
    void registerTest() {
        // Step 1: Navigate to the website
        page.navigate("https://demo.nopcommerce.com/");

        // Step 2: Click LogIn - Menu
        CommonElements commonElements = new CommonElements(page);
        commonElements.clickLoginMenu();

        // Step 3: Click Register - button
        LoginPage loginPage = new LoginPage(page);
        loginPage.clickRegisterButton();

        // Step 4: Check the title of the page after clicking Register button
        RegisterPage registerPage = new RegisterPage(page);
        assertTrue(registerPage.getPageTitle().contains(expectedPageTitle));

        // Step 5: Fill the register form
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(password);

        // Click register button
        registerPage.clickRegisterButton();

        // Step 6: Verify that register is successful
        assertTrue(registerPage.isRegistrationSuccessful(), "Registration was not successful");

        // Step 7: Click Log out - Menu
        commonElements.clickLogoutMenu();
    }

}
