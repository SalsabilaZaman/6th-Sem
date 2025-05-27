package org.example;

import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

public class ListPageTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private LoginPage loginPage;
    private ListPage listPage;
    private DashboardPage boardPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        loginPage = new LoginPage(driver);
        listPage = new ListPage(driver);
        boardPage= new DashboardPage(driver);
        driver.manage().window().setSize(new Dimension(654, 751));
        loginPage.navigateToLogin();
        loginPage.clickLoginButton();
        boardPage.clickAddNewBoard();
        boardPage.enterBoardName("New Board");
        boardPage.clickSubmitButton();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void validListCreation() {
        listPage.clickAddListLink();
        listPage.enterListName("New List");
        listPage.clickSubmitListButton();
        assertThat(listPage.getListTitle(), is("New List"));

    }
    @Test
    public void validCardCreation() {
        listPage.clickAddListLink();
        listPage.enterListName("New List");
        listPage.clickSubmitListButton();
        assertThat(listPage.getListTitle(), is("New List"));

        listPage.clickAddCardLink();
        listPage.enterCardName("testCard");
        listPage.clickSubmitCardButton();
        assertThat(listPage.getCardContent(), is("testCard"));
    }

    @Test
    public void invalidListCreation() {
        listPage.clickAddListLink();
        listPage.enterListName("");
        listPage.clickSubmitListButton();
        assertThat(listPage.getValidationMessage(), is("Please fill out this field."));
    }

    @Test
    public void cancelCardCreation() {
        listPage.clickAddListLink();
        listPage.enterListName("New List");
        listPage.clickSubmitListButton();
        assertThat(listPage.getListTitle(), is("New List"));

        listPage.clickAddCardLink();
        assertNull("No card should be created", driver.findElements(By.cssSelector(".card-content > span")).isEmpty() ? null : driver.findElement(By.cssSelector(".card-content > span")).getText());
    }
    @Test
    public void testCardDescription() {
        listPage.clickAddListLink();
        listPage.enterListName("ffff");
        listPage.clickSubmitListButton();
        listPage.clickAddCardLink();
        listPage.enterCardName("hyyy");
        listPage.clickSubmitCardButton();
        listPage.enterCardDescription("Test description");
        // Note: Add assertion if description field has a visible output
        // e.g., assertThat(driver.findElement(By.id("card_description_display")).getText(), is("Test description"));
    }
}