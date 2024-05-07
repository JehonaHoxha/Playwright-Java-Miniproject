package pages;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private final String welcomeText = "text=Welcome, Please Sign In!";
    private final String usernameInputLocator = "#Email";
    private final String passwordInputLocator = "#Password";
    private final String rememberMeCheckboxLocator = "#RememberMe";
    private final String loginButtonLocator = "button:text('Log in')";
    private final String forgotPasswordLinkLocator = "a[href='/passwordrecovery']";
    private final String registerButtonLocator = "button:text('Register')";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void waitForLoginPageLoad() {
        page.waitForSelector(welcomeText);
        page.waitForSelector(usernameInputLocator);
        page.waitForSelector(passwordInputLocator);
        page.waitForSelector(rememberMeCheckboxLocator);
        page.waitForSelector(loginButtonLocator);
        page.waitForSelector(forgotPasswordLinkLocator);
        page.waitForSelector(registerButtonLocator);
    }

    public void clickRegisterButton() {
        page.click(registerButtonLocator);
    }

    public void loginWithCredentials(String username, String password){
        page.fill(usernameInputLocator, username);
        page.fill(passwordInputLocator, password);

        page.click(loginButtonLocator);
    }


}
