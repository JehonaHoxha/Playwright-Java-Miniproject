package pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;
    private final String logoLocator = ".header-logo img";
    private final String searchBoxLocator = "#small-searchterms";
    private final String navigationMenuLocator = ".header-menu";
    private final String sliderProductsLocator = "#nivo-slider";
    private final String computersMenuLocator = "a[href='/computers']";
    private final String notebooksMenuLocator = "a[href='/notebooks']";

    public HomePage(Page page) {
        this.page = page;
    }

    public void waitForHomePageLoad() {
        page.waitForSelector(logoLocator);
        page.waitForSelector(searchBoxLocator);
        page.waitForSelector(navigationMenuLocator);
        page.waitForSelector(sliderProductsLocator);
    }

    public void hoverOverComputersMenu() {
        page.hover(computersMenuLocator);
    }
    public void clickNotebooks() {
        page.waitForSelector(notebooksMenuLocator);
        page.click(notebooksMenuLocator);
    }

}
