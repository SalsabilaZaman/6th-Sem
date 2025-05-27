//package org.example;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.time.Duration;
//
//public class ListPage {
//    private static final Logger logger = LoggerFactory.getLogger(ListPage.class);
//    private final WebDriver driver;
//    private final WebDriverWait wait;
//    private final By addCardLink = By.linkText("Add a new card...");
//    private final By cardNameField = By.id("card_name");
//    private final By submitButton = By.cssSelector("button");
//    private final By cardContent = By.cssSelector(".card-content > span");
//    private final By errorMessage = By.cssSelector(".error");
//
//
//    public ListPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public void clickAddCardLink() {
//        logger.info("Clicking add card link");
//        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(addCardLink));
//        link.click();
//        logger.info("Clicked add card link");
//    }
//
//    public void enterCardName(String cardName) {
//        logger.info("Entering card name '{}'", cardName);
//        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameField));
//        input.clear();
//        input.sendKeys(cardName);
//        logger.info("Entered card name '{}'", cardName);
//    }
//
//    public void clickSubmitButton() {
//        logger.info("Clicking submit button");
//        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
//        button.click();
//        logger.info("Clicked submit button");
//    }
//
//    public String getCardContent() {
//        logger.info("Retrieving card content");
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent));
//        String text = element.getText();
//        logger.info("Retrieved card content: '{}'", text);
//        return text;
//    }
//
//    public String getErrorMessage() {
//        logger.info("Retrieving error message");
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
//        String text = element.getText();
//        logger.info("Retrieved error message: '{}'", text);
//        return text;
//    }
//}

package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class ListPage {
    private static final Logger logger = LoggerFactory.getLogger(ListPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By addListLink = By.cssSelector(".list.add-new > .inner");
    private final By listNameField = By.id("list_name");
    private final By listTitle =By.cssSelector("div[id*='list_'] .inner header h4");
    private final By submitListButton =  By.cssSelector("#new_list_form button[type='submit']");
    private final By addCardLink = By.linkText("Add a new card...");
    private final By cardNameField = By.id("card_name");
    private final By submitCardButton = By.cssSelector("button");
    private final By cardContent = By.cssSelector(".card-content > span");
    private final By cardDescriptionField = By.cssSelector(".form-wrapper textarea");
    private final By cardMembersField = By.id("card_members");
    private final By cardTagsField = By.id("card_tags");
    private final By addCommentField = By.id("add_comment");
    private final By saveCommentButton = By.xpath("//button[contains(., 'Save comment')]");

    public ListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddListLink() {
        logger.info("Clicking add list link");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(addListLink));
        link.click();
        logger.info("Clicked add list link");
    }

    public void enterListName(String listName) {
        logger.info("Entering list name '{}'", listName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameField));
        input.clear();
        input.sendKeys(listName);
        logger.info("Entered list name '{}'", listName);
    }

    public void clickSubmitListButton() {
        logger.info("Clicking submit list button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitListButton));
        button.click();
        logger.info("Clicked submit list button");
    }

    public String getListTitle() {
        logger.info("Retrieving list title");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(listTitle));
        String text = element.getText();
        logger.info("Retrieved list title: '{}'", text);
        return text;
    }

    public void clickAddCardLink() {
        logger.info("Clicking add card link");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(addCardLink));
        link.click();
        logger.info("Clicked add card link");
    }

    public void enterCardName(String cardName) {
        logger.info("Entering card name '{}'", cardName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameField));
        input.clear();
        input.sendKeys(cardName);
        logger.info("Entered card name '{}'", cardName);
    }

    public void clickSubmitCardButton() {
        logger.info("Clicking submit card button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitCardButton));
        button.click();
        logger.info("Clicked submit card button");
    }

    public String getCardContent() {
        logger.info("Retrieving card content");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent));
        String text = element.getText();
        logger.info("Retrieved card content: '{}'", text);
        return text;
    }

    public String getValidationMessage() {
        logger.info("Retrieving validation message");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameField));
        String message = input.getAttribute("validationMessage");
        logger.info("Retrieved validation message: '{}'", message);
        return message;
    }
    public void enterCardDescription(String description) {
        logger.info("Entering card description '{}'", description);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardDescriptionField));
        input.clear();
        input.sendKeys(description);
        logger.info("Entered card description '{}'", description);
    }

    public void enterCardMembers(String members) {
        logger.info("Entering card members '{}'", members);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardMembersField));
        input.clear();
        input.sendKeys(members);
        logger.info("Entered card members '{}'", members);
    }

    public void enterCardTags(String tags) {
        logger.info("Entering card tags '{}'", tags);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardTagsField));
        input.clear();
        input.sendKeys(tags);
        logger.info("Entered card tags '{}'", tags);
    }

    public void enterComment(String comment) {
        logger.info("Entering comment '{}'", comment);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(addCommentField));
        input.clear();
        input.sendKeys(comment);
        logger.info("Entered comment '{}'", comment);
    }

    public void clickSaveCommentButton() {
        logger.info("Clicking save comment button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveCommentButton));
        button.click();
        logger.info("Clicked save comment button");
    }


}