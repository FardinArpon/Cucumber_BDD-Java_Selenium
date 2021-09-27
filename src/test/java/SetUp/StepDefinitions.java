import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;

import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    public WebDriver driver;
    WebDriverWait wait;
    @Given("^User visits e-commerce website$")
    public void user_visits_e_commerce_website() {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops=new FirefoxOptions();
        ops.addArguments("--headed"); //uncomment if you want to run in headless mode
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");

    }

    @When("^User enters valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_valid_credentials(String username, String password) throws Exception {
        wait=new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.presenceOfElementLocated (By.className("login"))); // wait until getting the login button
        Login login=new Login(driver);
        login.doLogin(username,password);
    }

    @Then("^User can logged in successfully$")
    public void user_can_logged_in_successfully() throws Exception {
        WebElement lblUserName=driver.findElement(By.xpath("//span[contains(text(),'Test User')]"));
        Assert.assertEquals(lblUserName.getText(),"Test User");
    }
    @After
    public void closeBrowser(){
        driver.quit();
    }
}
