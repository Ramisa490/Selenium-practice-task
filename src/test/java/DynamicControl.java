import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.UUID;


public class DynamicControl extends Base
{
    private final String DYNAMIC_CONTROL = String.format(PRECISE_TEXT_XPATH, "Dynamic Controls");
    private final String Enable = String.format(PRECISE_TEXT_XPATH, "Enable");
    private final String input = "//*[@id='input-example']//input";
    private String dynamicValue = UUID.randomUUID().toString();

    @Test
    public void setDynamic()
    {
        driver.findElement(By.xpath(DYNAMIC_CONTROL)).click();
        driver.findElement(By.xpath(Enable)).click();
        WebElement inputfield = driver.findElement(By.xpath(input));
        Assert.assertTrue(isClickable(inputfield));
        inputfield.sendKeys(dynamicValue);
        Assert.assertEquals(inputfield.getAttribute("value"),dynamicValue,"Text is not displayed");
    }

    public boolean isClickable(WebElement element)
    {
        try
        {
            Duration timeout = Duration.ofSeconds(10);     // explicit wait
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true; // Return true if the element is clickable
        }
        catch (Exception e)
        {
            return false; // Return false if the element is not clickable or not found
        }
    }
}