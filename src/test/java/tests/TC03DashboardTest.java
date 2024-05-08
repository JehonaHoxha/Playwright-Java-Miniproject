package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CommonElements;
import pages.HomePage;
import pages.LoginPage;
import pages.NotebooksPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TC03DashboardTest extends ScriptBase {
    String siteUrl = "https://demo.nopcommerce.com/";
    String email = "test12jh@gmail.com";
    String password = "dummyPassword";


    @Test
    @DisplayName("Dashboard Test")

    void dashboardTest() {

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


        // Step 1: Hover over Computers Menu
        homePage.hoverOverComputersMenu();

        // Step 2: Click Notebooks
        homePage.clickNotebooks();

        // Step 3: Verify that we have navigated to Notebooks Page
        NotebooksPage notebooksPage = new NotebooksPage(page);
        assertTrue(notebooksPage.verifyNotebooksPage(), "Notebooks page not displayed correctly");

        // Step 4: Choose 9 on Display dropdown
        notebooksPage.selectDisplayDropdownValue(9);

        // Step 5: Verify that only 6 items are displayed
        assertEquals(6, notebooksPage.getNumberOfDisplayedItems(), "Unexpected number of displayed items");

        // Step 6: On Filter by attributes, check 16GB
        notebooksPage.checkAttribute16GB();

        // Step 7: Verify that only 1 item is displayed
        assertEquals(1, notebooksPage.getNumberOfDisplayedItems(), "Unexpected number of displayed items checking attribute 16GB");

        // Step 8: Uncheck the 16GB checkbox
        notebooksPage.uncheckAttribute16GB();

        // Step 9: Verify that 6 items are displayed now
        assertEquals(6, notebooksPage.getNumberOfDisplayedItems(), "Unexpected number of displayed items after unchecking attribute 16GB");

        // Steps 10-11: Add the second and the third item on wishlist and verify notifications
        notebooksPage.addItemToWishlist(2);
        assertTrue(notebooksPage.isWishlistSuccessNotificationDisplayed(), "Wishlist notification not displayed");
        notebooksPage.closeSuccessNotificationBar();

        notebooksPage.addItemToWishlist(3);
        assertTrue(notebooksPage.isWishlistSuccessNotificationDisplayed(), "Wishlist notification not displayed");
        notebooksPage.closeSuccessNotificationBar();

        // Steps 12-13: Add the fourth, fifth, and sixth item on Shopping Cart and verify notifications
        notebooksPage.addItemToShoppingCart(4);
        assertTrue(notebooksPage.isAddToCartSuccessNotificationDisplayed(), "Add to Cart notification not displayed");
        notebooksPage.closeSuccessNotificationBar();

        notebooksPage.addItemToShoppingCart(5);
        assertTrue(notebooksPage.isAddToCartSuccessNotificationDisplayed(), "Add to Cart notification not displayed");
        notebooksPage.closeSuccessNotificationBar();

        notebooksPage.addItemToShoppingCart(6);
        assertTrue(notebooksPage.isAddToCartSuccessNotificationDisplayed(), "Add to Cart notification not displayed");
        notebooksPage.closeSuccessNotificationBar();


         // Step 14: Verify that Wishlist on Menu bar displays 2
        assertEquals("2", notebooksPage.getWishlistItemCount(), "Unexpected number of items in Wishlist");

        // Step 15: Verify that Shopping Cart on Menu bar displays 3
        assertEquals("3", notebooksPage.getShoppingCartItemCount(), "Unexpected number of items in Shopping Cart");
    }
}
