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
import static org.junit.Assert.assertThat;

public class SignUpPageTest  {
    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private SignUpPage signUpPage;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        signUpPage = new SignUpPage(driver);
        loginPage= new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        driver.manage().window().setSize(new Dimension(654, 751));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void validSignUp() {
        signUpPage.navigateToSignUp();
        driver.manage().window().setSize(new Dimension(602, 781));
        signUpPage.enterFirstName("Salsabila");
        signUpPage.enterLastName("Zaman");
        signUpPage.enterEmail("bssae1443@iit.du.ac.bd");
        signUpPage.enterPassword("iit123");
        signUpPage.enterPasswordConfirmation("iit123");
        signUpPage.clickSignUpButton();
        assertThat(dashboardPage.getUserName(), is("Salsabila Zaman"));
    }

    @Test
    public void invalidSignUp() {
        signUpPage.navigateToSignUp();
        driver.manage().window().setSize(new Dimension(602, 781));
        signUpPage.enterFirstName("Salsabila");
        signUpPage.enterLastName("Zaman");
        signUpPage.enterEmail("john@phoenix-trello.com");
        signUpPage.enterPassword("iit123");
        signUpPage.enterPasswordConfirmation("iit123");
        signUpPage.clickSignUpButton();
        assertThat(signUpPage.getErrorMessage(), is("Email already taken"));
    }

    @Test
    public void passLength() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(758, 1013));
        loginPage.clickCreateAccountLink();
        signUpPage.enterFirstName("salsa");
        signUpPage.enterLastName("zaman");
        signUpPage.enterEmail("abc@gmail.com");
        signUpPage.enterPassword("123");
        signUpPage.enterPasswordConfirmation("123");
        signUpPage.clickSignUpButton();
        assertThat(signUpPage.getErrorMessage(), is("should be at least 5 character(s)"));
    }
    @Test
    public void invalidSignUpWithInvalidEmail() {
        signUpPage.navigateToSignUp();
        driver.manage().window().setSize(new Dimension(602, 781));
        signUpPage.enterFirstName("Salsabila");
        signUpPage.enterLastName("Zaman");
        signUpPage.enterEmail("john");
        signUpPage.enterPassword("iit123");
        signUpPage.enterPasswordConfirmation("iit123");
        signUpPage.clickSignUpButton();
        assertThat(signUpPage.getValidationMessageFromEmailField(), is("Please enter an email address."));
    }

}