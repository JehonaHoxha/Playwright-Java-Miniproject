package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CommonElements;
import pages.HomePage;
import pages.LoginPage;
import pages.ShoppingCartPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC05EmptyShoppingCartTest extends ScriptBase {
    String siteUrl = "https://demo.nopcommerce.com/";
    String email = "test12jh@gmail.com";
    String password = "dummyPassword";

    @Test
    @DisplayName("Empty Shopping Cart Test")
    void emptyShoppingCartTest() {

        //login precondition & go to Shopping Cart Page
        page.navigate(siteUrl);
        HomePage homePage = new HomePage(page);
        homePage.waitForHomePageLoad();

        CommonElements commonElements = new CommonElements(page);
        commonElements.clickLoginMenu();

        LoginPage loginPage = new LoginPage(page);
        loginPage.waitForLoginPageLoad();
        loginPage.loginWithCredentials(email, password);
        assertTrue(page.isVisible(commonElements.logoutMenuLocator), "Login was not successful: Logout menu is not shown.");

        commonElements.hoverShoppingCartMenu();
        assertTrue(commonElements.isGoToCartButtonDisplayed(), "'Go To Cart' button is not displayed");

        commonElements.clickGoToCartButton();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(page);
        assertTrue(shoppingCartPage.isShoppingCartPageDisplayed(), "Did not navigate to Shopping Cart Page");

        // Step 1: Delete the first item on shopping cart &  Step 2: Verify that the number of elements in Shopping Cart table is decreased by 1
        int initialNumberOfCartItems = shoppingCartPage.getNumberOfItemsInCart();
        shoppingCartPage.clickNthDeleteButton(1);
        int numberOfCartItemsAfterDeletion = shoppingCartPage.getNumberOfItemsInCart();
        assertEquals(initialNumberOfCartItems - 1, numberOfCartItemsAfterDeletion);


        // Step 3: Repeat steps 1&2 until the last item is deleted
        while (!shoppingCartPage.isShoppingCartEmpty()){
            shoppingCartPage.clickNthDeleteButton(1);
        }

        // Step 4: Verify that Shopping Cart is empty
        assertEquals(0, shoppingCartPage.getNumberOfItemsInCart(), "Shopping cart is not empty");
        assertTrue(shoppingCartPage.isShoppingCartEmpty(),"Shopping cart is not empty");

    }
}
