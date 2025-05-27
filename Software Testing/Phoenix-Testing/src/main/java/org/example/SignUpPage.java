
package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class SignUpPage {
    private static final Logger logger = LoggerFactory.getLogger(SignUpPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By firstNameField = By.id("user_first_name");
    private final By lastNameField = By.id("user_last_name");
    private final By emailField = By.id("user_email");
    private final By passwordField = By.id("user_password");
    private final By passwordConfirmationField = By.id("user_password_confirmation");
    private final By signUpButton = By.cssSelector("button");
    private final By errorMessage = By.cssSelector(".error");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToSignUp() {
        driver.get("http://localhost:4000/sign_up");
        logger.info("Navigated to sign-up page");
    }

    public void clickSignUpButton() {
        logger.info("Clicking sign-up button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        button.click();
        logger.info("Clicked sign-up button");
    }

    public void enterFirstName(String firstName) {
        logger.info("Entering first name '{}'", firstName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        input.clear();
        input.sendKeys(firstName);
        logger.info("Entered first name '{}'", firstName);
    }

    public void enterLastName(String lastName) {
        logger.info("Entering last name '{}'", lastName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        input.clear();
        input.sendKeys(lastName);
        logger.info("Entered last name '{}'", lastName);
    }

    public void enterEmail(String email) {
        logger.info("Entering email '{}'", email);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        input.clear();
        input.sendKeys(email);
        logger.info("Entered email '{}'", email);
    }

    public void enterPassword(String password) {
        logger.info("Entering password");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        input.clear();
        input.sendKeys(password);
        logger.info("Entered password");
    }

    public void enterPasswordConfirmation(String passwordConfirmation) {
        logger.info("Entering password confirmation");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordConfirmationField));
        input.clear();
        input.sendKeys(passwordConfirmation);
        logger.info("Entered password confirmation");
    }

    public String getErrorMessage() {
        logger.info("Retrieving error message");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        String text = element.getText();
        logger.info("Retrieved error message: '{}'", text);
        return text;
    }
}
