package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CommonElements;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC02LoginTest extends ScriptBase {
    String siteUrl = "https://demo.nopcommerce.com/";
    String email = "test12jh@gmail.com";
    String password = "dummyPassword";
    String welcomeMessage = "Welcome to our store";

    @Test
    @DisplayName("Login Test")
    void loginTest() {
        // Step 1: Navigate to the website
        page.navigate(siteUrl);
        HomePage homePage = new HomePage(page);
        homePage.waitForHomePageLoad();

        // Step 2: Click LogIn - Menu
        CommonElements commonElements = new CommonElements(page);
        commonElements.clickLoginMenu();

        // Step 3: Login with the credentials created from Test 1
        LoginPage loginPage = new LoginPage(page);
        loginPage.waitForLoginPageLoad();
        loginPage.loginWithCredentials(email, password);

        // Step 4: Verify that login is successful
        assertTrue(page.textContent("body").contains(welcomeMessage), "Login was not successful: Welcome message not found");
        assertTrue(page.isVisible(commonElements.logoutMenuLocator), "Login was not successful: Logout menu is not shown.");

        // Step 5: Log out
        commonElements.clickLogoutMenu();
    }
}
