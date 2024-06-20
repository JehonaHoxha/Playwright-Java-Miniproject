package pages;
import com.microsoft.playwright.Page;

public class CommonElements {
    private final Page page;
    public String loginMenuLocator = "a[class='ico-login']";
    public String logoutMenuLocator = "a[class='ico-logout']";
    public String shoppingCartMenuLocator = ".ico-cart";
    public String goToCartButtonLocator = "button[class='button-1 cart-button']";
    public String searchBarLocator = "#small-searchterms";
    public String submitSearchButton = "button[class='button-1 search-box-button']";


    public CommonElements(Page page) {
        this.page = page;
    }

    public void clickLoginMenu() {
        page.click(loginMenuLocator);
    }

    public void clickLogoutMenu() {
        page.click(logoutMenuLocator);
    }

    public void hoverShoppingCartMenu(){
        page.waitForSelector(shoppingCartMenuLocator);
        page.hover(shoppingCartMenuLocator);
    }

    public boolean isGoToCartButtonDisplayed(){
        page.waitForSelector(goToCartButtonLocator);
        return page.isVisible(goToCartButtonLocator);
    }

    public void clickGoToCartButton() {
        page.click(goToCartButtonLocator);
    }

    public void clickSearchBar(){
        page.click(searchBarLocator);
    }

    public void fillSearchBar (String text){
        page.fill(searchBarLocator, text);
    }

    public void clickSubmitSearchButton (){
        page.click(submitSearchButton);
    }
}
