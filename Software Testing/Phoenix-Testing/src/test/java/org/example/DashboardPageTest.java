package org.example;

import junit.framework.TestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DashboardPageTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        driver.manage().window().setSize(new Dimension(654, 751));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void boardCreation() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 971));
        loginPage.clickLoginButton();
        dashboardPage.clickAddNewBoard();
        dashboardPage.enterBoardName("Study 2025");
        dashboardPage.clickSubmitButton();
        assertThat(dashboardPage.getBoardTitle(), is("Study 2025"));
    }

    @Test
    public void invalidBoardCreation() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 971));
        loginPage.clickLoginButton();
        dashboardPage.clickAddNewBoard();
        dashboardPage.enterBoardName("");
        dashboardPage.clickSubmitButton();
        assertThat(dashboardPage.getValidationMessage(), is("Please fill out this field."));
    }

    @Test
    public void logout() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 933));
        loginPage.clickLoginButton();
        dashboardPage.clickSignOut();
        assertEquals("http://localhost:4000/sign_in", driver.getCurrentUrl());
    }
}