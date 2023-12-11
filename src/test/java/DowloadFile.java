import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;

public class DowloadFile extends Base
{
    String files= "text.txt";
    protected final String file = String.format(PRECISE_TEXT_XPATH,"File Download");
    protected final String specificfile = String.format(PRECISE_TEXT_XPATH,files);
    protected final String filepath = String.format(RELATIVE_RESOURCE_PATH+specificfile);

    protected final File downloadedfile= new File(filepath);

    @Test
    public void download()
    {
        driver.findElement(By.xpath(file)).click();
        WebElement element= driver.findElement(By.xpath(specificfile));
        Assert.assertTrue(element.isDisplayed(),"File is not Displayed");
        if (doesFileExist(downloadedfile))
        {
            System.out.println("File exists!");
        }
        else
        {
            System.out.println("File does not exist.");
        }
        element.click();
    }

    public boolean doesFileExist(File file)
    {
        try
        {
            // Check if the file exists
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicit wait
            return file.exists();
        }
        catch (Exception e)
        {
            // Handle exceptions, if necessary
            return false; // Return false if there's an exception
        }
    }
    @AfterMethod
    public void deletefile()
    {
        if(downloadedfile.exists())
        {
            boolean delete = downloadedfile.delete();
            if (delete) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("File deletion failed.");
            }
        }
    }
}
