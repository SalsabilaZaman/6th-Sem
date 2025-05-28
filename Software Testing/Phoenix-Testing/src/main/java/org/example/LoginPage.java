package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class LoginPage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By emailField = By.id("user_email");
    private final By passwordField = By.id("user_password");
    private final By loginButton = By.cssSelector("button");
    private final By createAccountLink = By.linkText("Create new account");
    private final By errorMessage = By.cssSelector(".error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToLogin() {
        logger.info("Navigating to http://localhost:4000/sign_in");
        driver.get("http://localhost:4000/sign_in");
        logger.info("Navigated to login page");
    }

    public void clickLoginButton() {
        logger.info("Clicking login button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
        logger.info("Clicked login button");
    }

    public void clickCreateAccountLink() {
        logger.info("Clicking create account link");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(createAccountLink));
        link.click();
        logger.info("Clicked create account link");
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

    public String getErrorMessage() {
        logger.info("Retrieving error message");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        String text = element.getText();
        logger.info("Retrieved error message: '{}'", text);
        return text;
    }
    public String getValidationMessageFromEmailField() {

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        String message = input.getAttribute("validationMessage");
        return message;
    }
    public String getValidationMessageFromPasswordField() {

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        String message = input.getAttribute("validationMessage");
        return message;
    }
}