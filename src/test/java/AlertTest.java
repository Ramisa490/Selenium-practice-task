import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AlertTest extends  Base
{
    protected final String alerts= String.format(PRECISE_TEXT_XPATH,"JavaScript Alerts");
    protected final String click= String.format(PRECISE_TEXT_XPATH,"Click for JS Alert");
    protected final String text= String.format(PRECISE_TEXT_XPATH,"You successfully clicked an alert");
    protected final String result= "result" ;

    @Test
    public void alert()
    {
        driver.findElement(By.xpath(alerts)).click();
        driver.findElement(By.xpath(click)).click();
        // Switching to the alert
        Alert alert = driver.switchTo().alert();

        // Getting the text of the alert
        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);
        //Wait to see pop up window shows
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        // Clicking the OK button
        alert.accept();

        WebElement r = driver.findElement(By.id(result));
        WebElement t = driver.findElement(By.xpath(text));
        Assert.assertEquals(r,t,"Alert is not Showing");

    }
}
