package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CommonElements;
import pages.HomePage;
import pages.LoginPage;
import pages.ShoppingCartPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC04ShoppingCartTest extends ScriptBase {

    String siteUrl = "https://demo.nopcommerce.com/";
    String email = "test12jh@gmail.com";
    String password = "dummyPassword";

    @Test
    @DisplayName("Shopping Cart Test")
    void shoppingCartTest() {

        //login precondition
        page.navigate(siteUrl);
        HomePage homePage = new HomePage(page);
        homePage.waitForHomePageLoad();

        CommonElements commonElements = new CommonElements(page);
        commonElements.clickLoginMenu();

        LoginPage loginPage = new LoginPage(page);
        loginPage.waitForLoginPageLoad();
        loginPage.loginWithCredentials(email, password);
        assertTrue(page.isVisible(commonElements.logoutMenuLocator), "Login was not successful: Logout menu is not shown.");


        // Step 1: Hover over Shopping Cart – Menu
        commonElements.hoverShoppingCartMenu();

        // Step 2: Verify that ‘Go To Cart’ – button is displayed
        assertTrue(commonElements.isGoToCartButtonDisplayed(), "'Go To Cart' button is not displayed");

        // Step 3: Click ‘Go To Cart’ – button
        commonElements.clickGoToCartButton();

        // Step 4: Verify that we have navigated to Shopping Cart Page
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(page);
        assertTrue(shoppingCartPage.isShoppingCartPageDisplayed(), "Did not navigate to Shopping Cart Page");

        // Step 5: Verify that following buttons are displayed
        assertTrue(shoppingCartPage.isContinueShoppingButtonDisplayed(), "'Continue shopping' button is not displayed");
        assertTrue(shoppingCartPage.isEstimateShippingButtonDisplayed(), "'Estimate shipping' button is not displayed");

        // Step 6: Verify that the prices sum for all items is equal to Total Price in the end of the page
        double totalPriceOfProducts = shoppingCartPage.calculateTotalPriceOfProducts();
        double totalPriceDisplayed = shoppingCartPage.getTotalSumDisplayed();

        assertEquals(totalPriceOfProducts, totalPriceDisplayed, "Total Price mismatch");

    }
}
