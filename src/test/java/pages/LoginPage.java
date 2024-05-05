package pages;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    String registerButtonLocator = "button:text('Register')";

    public void clickRegisterButton() {
        page.click(registerButtonLocator);
    }

}
