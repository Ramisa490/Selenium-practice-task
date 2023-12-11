import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class Iframe extends Base
{
    protected final String frames= String.format(PRECISE_TEXT_XPATH,"Frames");
    protected final String iFrame= String.format(PRECISE_TEXT_XPATH,"iFrame");
    protected final String Initial= "Your content goes here.";
    protected final String iframe_id= "mce_0_ifr";
    protected final String input = UUID.randomUUID().toString();
    protected final String text = "tinymce";
    protected final String edit= String.format(PRECISE_TEXT_XPATH,"Edit");
    protected final String undo= String.format(PRECISE_TEXT_XPATH,"Undo");

    protected final String Initialtext= String.format(PRECISE_TEXT_XPATH,Initial);
    @Test
    public void Iframe()
    {
        driver.findElement(By.xpath(frames)).click();
        driver.findElement(By.xpath(iFrame)).click();

        driver.switchTo().frame(iframe_id);//switch from main page to iframe
        WebElement inputElement = driver.findElement(By.id(text));
        inputElement.sendKeys(Initial+input);

        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(text)));

        // Assert within the iframe
        Assert.assertTrue(inputElement.isDisplayed(), "Text input is not displayed within the iframe");

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath(edit)).click();
        driver.findElement(By.xpath(undo)).click();

        //Wait to see if working properly
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        driver.switchTo().frame(iframe_id);

        Assert.assertTrue(driver.findElement(By.xpath(Initialtext)).isDisplayed(), "Text input is not displayed within the iframe");

    }
}
