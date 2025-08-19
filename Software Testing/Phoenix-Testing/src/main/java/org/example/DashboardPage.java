//package org.example;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//
//public class DashboardPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private By userNameLabel = By.cssSelector("span:nth-child(3)");
//    private By addNewBoardButton = By.id("add_new_board");
//    private By boardNameField = By.id("board_name");
//    private By submitButton = By.cssSelector("button");
//    private By boardTitle = By.cssSelector("h3");
//
//    private By errorMessage = By.xpath("//*[contains(text(), 'Please fill out this field.')]");
//
//    public DashboardPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public String getUserName() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLabel)).getText();
//    }
//
//    public void clickAddNewBoard() {
//        wait.until(ExpectedConditions.elementToBeClickable(addNewBoardButton)).click();
//    }
//
//    public void enterBoardName(String boardName) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameField)).sendKeys(boardName);
//    }
//
//    public void clickSubmitButton() {
//        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
//    }
//
//    public String getBoardTitle() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(boardTitle)).getText();
//    }
//    public String getErrorMessage() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
//    }
//}

package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By userNameLabel = By.cssSelector("span:nth-child(3)");
    private By addNewBoardButton = By.id("add_new_board");
    private By boardNameField = By.id("board_name");
    private By submitButton = By.cssSelector("button");
    private By boardTitle = By.cssSelector("h3");
    private By signOutLink = By.linkText("Sign out");
    private By errorMessage = By.xpath("//*[contains(text(), 'Please fill out this field.')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getUserName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLabel)).getText();
    }

    public void clickAddNewBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewBoardButton)).click();
    }

    public void enterBoardName(String boardName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameField)).sendKeys(boardName);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getBoardTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(boardTitle)).getText();
    }

    public String getValidationMessage() {

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameField));
        String message = input.getAttribute("validationMessage");
        return message;
    }

    public void clickSignOut() {
        wait.until(ExpectedConditions.elementToBeClickable(signOutLink)).click();
    }
}