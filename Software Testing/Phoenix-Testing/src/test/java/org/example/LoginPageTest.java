package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginPageTest {
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
    public void validLogin() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 933));
        loginPage.clickLoginButton();
        assertThat(dashboardPage.getUserName(), is("John Doe"));
    }

    @Test
    public void invalidLoginWithWrongPassword() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 933));
        loginPage.enterEmail("invalid@invalid.com");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLoginButton();
        assertThat(loginPage.getErrorMessage(), is("Invalid email or password"));
    }
    @Test
    public void invalidLoginWithNoEmail() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 933));
        loginPage.enterEmail("");
        loginPage.enterPassword("aa");
        loginPage.clickLoginButton();
        assertThat(loginPage.getValidationMessageFromEmailField(), is("Please fill out this field."));
    }
    @Test
    public void invalidLoginWithNoPassword() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 933));
        loginPage.enterEmail("invalid@invalid.com");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        assertThat(loginPage.getValidationMessageFromPasswordField(), is("Please fill out this field."));
    }
}