package pages;

import com.microsoft.playwright.Page;

public class RegisterPage {
    private final Page page;
    private final String firstNameInputLocator = "#FirstName";
    private final String lastNameInputLocator = "#LastName";
    private final String emailInputLocator = "#Email";
    private final String passwordInputLocator = "#Password";
    private final String confirmPasswordInputLocator = "#ConfirmPassword";
    private final String registerButtonLocator = "#register-button";
    private final String registrationConfirmationText = "Your registration completed";

    public RegisterPage(Page page) {
        this.page = page;
    }

    public String getPageTitle() {
        return page.title();
    }

    public void enterFirstName(String firstName) {
        page.fill(firstNameInputLocator, firstName);
    }

    public void enterLastName(String lastName) {
        page.fill(lastNameInputLocator, lastName);
    }

    public void enterEmail(String email) {
        page.fill(emailInputLocator, email);
    }

    public void enterPassword(String password) {
        page.fill(passwordInputLocator, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        page.fill(confirmPasswordInputLocator, confirmPassword);
    }

    public void clickRegisterButton() {
        page.click(registerButtonLocator);
    }

    public boolean isRegistrationSuccessful() {
        return page.textContent("body").contains(registrationConfirmationText);
    }

}
