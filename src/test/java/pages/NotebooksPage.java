package pages;

import com.microsoft.playwright.Page;

public class NotebooksPage {
    private final Page page;
    private final String expectedPageTitle = "Notebooks";
    private final String notebooksHeadingLocator = "h1:has-text('Notebooks')";
    private final String productListLocator = ".products-wrapper";
    private final String productsPerPageDropdownSelector = "#products-pagesize";
    private final String itemGridLocator = ".item-grid";
    private final String attributeFilter16GBCheckboxLocator = "#attribute-option-10";
    private final String wishlistButtonsLocator = "button[title='Add to wishlist']";
    private final String addedToWishlistSuccessText = "The product has been added to your wishlist";
    private final String addToCartButtonsLocator = "//button[contains(text(), 'Add to cart')]";
    private final String addedToShoppingCartSuccessText = "The product has been added to your shopping cart";
    private final String successBarLocator = "#bar-notification";
    private final String closeSuccessBarButton = ".close";
    private final String wishlistItemCountLocator = ".ico-wishlist .wishlist-qty";
    private final String shoppingCartItemCountLocator = ".ico-cart .cart-qty";


    public NotebooksPage(Page page) {
        this.page = page;
    }

    public boolean verifyNotebooksPage(){
        waitForProductListToLoad();
        boolean isPageTitleCorrect = verifyNotebooksPageTitle();
        boolean isNotebooksHeadingDisplayed = isNotebooksHeadingDisplayed();
        boolean isProductListDisplayed = isProductListDisplayed();
        return isPageTitleCorrect && isNotebooksHeadingDisplayed && isProductListDisplayed;
    }

    public boolean verifyNotebooksPageTitle(){
        String pageTitle = page.title();
        return pageTitle.contains(expectedPageTitle);
    }

    private boolean isNotebooksHeadingDisplayed() {
        return page.isVisible(notebooksHeadingLocator);
    }

    public boolean isProductListDisplayed() {
        return page.isVisible(productListLocator);
    }

    public void waitForProductListToLoad() {
        page.waitForSelector(productListLocator);
    }

    public void selectDisplayDropdownValue(Integer value) {
        page.selectOption(productsPerPageDropdownSelector, String.valueOf(value));
    }

    public int getNumberOfDisplayedItems() {
        return page.querySelectorAll(itemGridLocator + " > *").size();
    }

    public void checkAttribute16GB(){
        page.check(attributeFilter16GBCheckboxLocator);
    }

    public void uncheckAttribute16GB(){
        page.uncheck(attributeFilter16GBCheckboxLocator);
    }

    public void addItemToWishlist(int itemNumber) {
        page.querySelectorAll(wishlistButtonsLocator).get(itemNumber - 1).click();
    }

    public boolean isWishlistSuccessNotificationDisplayed() {
        page.waitForSelector(successBarLocator);
        boolean isSuccessBarVisible = page.isVisible(successBarLocator);
        boolean isWishlistSuccessTextVisible = page.textContent("body").contains(addedToWishlistSuccessText);
        return isSuccessBarVisible && isWishlistSuccessTextVisible;
    }

    public void closeSuccessNotificationBar(){
        page.click(closeSuccessBarButton);
    }

    public void addItemToShoppingCart(int itemNumber) {
        page.querySelectorAll(addToCartButtonsLocator).get(itemNumber - 1).click();
    }

    public boolean isAddToCartSuccessNotificationDisplayed() {
        page.waitForSelector(successBarLocator);
        boolean isSuccessBarVisible = page.isVisible(successBarLocator);
        boolean isAddToCartSuccessTextVisible = page.textContent("body").contains(addedToShoppingCartSuccessText);
        return isSuccessBarVisible && isAddToCartSuccessTextVisible;
    }

    public String getWishlistItemCount() {
        // String wishlistNumberOfItems = page.textContent(wishlistItemCountLocator);
        return page.textContent(wishlistItemCountLocator).replaceAll("[^0-9]", "");
      //  return Integer.parseInt(wishlistNumberOfItems);
    }

    public String getShoppingCartItemCount() {
       // String shoppingCartNumberOfItems = page.textContent(shoppingCartItemCountLocator).trim();
      //  return Integer.parseInt(shoppingCartNumberOfItems);
        return page.textContent(shoppingCartItemCountLocator).replaceAll("[^0-9]", "");
    }
}
