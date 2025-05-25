package pages;

import com.microsoft.playwright.Page;

public class RegisterPage {
    private final Page page;
    private final String expectedPageTitle = "Register";
    private final String maleRadioButtonLocator = "#gender-male";
    private final String femaleRadioButtonLocator = "#gender-female";
    private final String firstNameInputLocator = "#FirstName";
    private final String lastNameInputLocator = "#LastName";
    private final String dateOfBirthDropdownLocator = "[name='DateOfBirthDay']";
    private final String monthOfBirthDropdownLocator = "[name='DateOfBirthMonth']";
    private final String yearOfBirthDropdownLocator = "[name='DateOfBirthYear']";
    private final String emailInputLocator = "#Email";
    private final String companyInputLocator = "#Company";
    private final String newslettersCheckboxLocator = "#Newsletter";
    private final String passwordInputLocator = "#Password";
    private final String confirmPasswordInputLocator = "#ConfirmPassword";
    private final String registerButtonLocator = "#register-button";
    private final String registrationConfirmationText = "Your registration completed";

    public RegisterPage(Page page) {
        this.page = page;
    }

    public void waitForRegisterPageLoad() {
        page.waitForSelector(maleRadioButtonLocator);
        page.waitForSelector(femaleRadioButtonLocator);
        page.waitForSelector(firstNameInputLocator);
        page.waitForSelector(lastNameInputLocator);
        // page.waitForSelector(dateOfBirthDropdownLocator);
        // page.waitForSelector(monthOfBirthDropdownLocator);
        // page.waitForSelector(yearOfBirthDropdownLocator);
        page.waitForSelector(emailInputLocator);
        page.waitForSelector(companyInputLocator);
        page.waitForSelector(newslettersCheckboxLocator);
        page.waitForSelector(passwordInputLocator);
        page.waitForSelector(confirmPasswordInputLocator);
        page.waitForSelector(registerButtonLocator);
    }

    public String getPageTitle() {
        return page.title();
    }

    public boolean verifyPageTitle(){
        return (getPageTitle().contains(expectedPageTitle));
    }

    public void selectGenderOption(String gender) {
        if (gender.equalsIgnoreCase("Female")) {
            page.check(femaleRadioButtonLocator);
        } else if (gender.equalsIgnoreCase("Male")) {
            page.check(maleRadioButtonLocator);
        } else {
            System.out.println("Invalid gender option");
        }
    }

    public void enterFirstName(String firstName) {
        page.fill(firstNameInputLocator, firstName);
    }

    public void enterLastName(String lastName) {
        page.fill(lastNameInputLocator, lastName);
    }

    public void enterDayOfBirth(Integer day){
        String dayAsString = Integer.toString(day);
        page.selectOption(dateOfBirthDropdownLocator, dayAsString);
    }

    public void enterMonthOfBirth(Integer month){
        String monthAsString = Integer.toString(month);
        page.selectOption(monthOfBirthDropdownLocator, monthAsString);
    }

    public void enterYearOfBirth(Integer year){
        String yearAsString = Integer.toString(year);
        page.selectOption(yearOfBirthDropdownLocator, yearAsString);
    }

    public void enterEmail(String email) {
        page.fill(emailInputLocator, email);
    }

    public void fillCompanyName(String company){
        page.fill(companyInputLocator, company);
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
