package tests;

import org.junit.jupiter.api.*;

import pages.CommonElements;
import pages.LoginPage;
import pages.RegisterPage;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

public class TC01RegisterTest extends ScriptBase {

    String siteUrl = "https://demo.nopcommerce.com/";
    String gender = "Female";
    String firstName = "testname";
    String lastName = "testlastname";
    Integer birthDay = 20;
    Integer birthMonth = 5;
    Integer birthYear = 2000;
    String email = "test121jh@gmail.com";
    String companyName = "test 123";
    String password = "dummyPassword";


    @Test
    @DisplayName("Register Test")
    void registerTest() {
        // Step 1: Navigate to the website
        page.navigate(siteUrl);
        HomePage homePage = new HomePage(page);
        homePage.waitForHomePageLoad();

        // Step 2: Click LogIn - Menu
        CommonElements commonElements = new CommonElements(page);
        commonElements.clickLoginMenu();

        // Step 3: Click Register - button
        LoginPage loginPage = new LoginPage(page);
        loginPage.waitForLoginPageLoad();
        loginPage.clickRegisterButton();

        // Step 4: Check the title of the page after clicking Register button
        RegisterPage registerPage = new RegisterPage(page);
        registerPage.waitForRegisterPageLoad();
        assertTrue(registerPage.verifyPageTitle(), "The title of the page is not as expected");

        // Step 5: Fill the register form
        registerPage.selectGenderOption(gender);
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        // registerPage.enterDayOfBirth(birthDay);
        // registerPage.enterMonthOfBirth(birthMonth);
        // registerPage.enterYearOfBirth(birthYear);
        registerPage.enterEmail(email);
        registerPage.fillCompanyName(companyName);
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
