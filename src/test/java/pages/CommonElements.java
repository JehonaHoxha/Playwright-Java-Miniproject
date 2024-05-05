package pages;
import com.microsoft.playwright.Page;

public class CommonElements {
    private final Page page;
    String loginMenuLocator = "a[class='ico-login']";
    String logoutMenuLocator = "a[class='ico-logout']";

    public CommonElements(Page page) {
        this.page = page;
    }

   // String registerButtonText = "text=Register";


    public void clickLoginMenu() {
        page.click(loginMenuLocator);
    }

    public void clickLogoutMenu() {
        page.click(logoutMenuLocator);
    }
}
