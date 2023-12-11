import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Auth extends Base
{
    protected final String BASIC_AUTH = String.format(PRECISE_TEXT_XPATH, "Basic Auth");
    private final By SUCCESS_AUTH = By.xpath(String.format(PARTICULAR_TEXT_XPATH,"Congratulations! You must have the proper credentials."));
    @BeforeMethod
    public void basic()
    {
        ((HasAuthentication) driver).register(UsernameAndPassword.of("admin", "admin")); // Doing because of pop up window and cannot inspect element
    }
    @Test

    public void auth()
    {
        driver.findElement(By.xpath(BASIC_AUTH)).click();

        Assert.assertTrue(driver.findElement(SUCCESS_AUTH).isDisplayed(), "Message is not displayed"); //If the condition is true, the test continues execution without any interruption.
    }
}
