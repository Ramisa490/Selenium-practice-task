import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;

public class UploadFile extends Base
{
    private final String FILE_UPLOAD = String.format(PRECISE_TEXT_XPATH, "File Upload");
    private final String FILE_NAME = "selenium-snapshot.jpeg";

    private final String FILE_PATH = RELATIVE_RESOURCE_PATH + FILE_NAME;

    private final String UPLOAD = "file-upload";
    private final String SUBMIT = "file-submit";
    private final String UPLOADFILE = "uploaded-files"; //after finished to upload

    @Test
    public void fileUploadTest()
    {
        driver.findElement(By.xpath(FILE_UPLOAD)).click();
        File fileToUpload = new File(FILE_PATH);
        driver.findElement(By.id(UPLOAD)).sendKeys(fileToUpload.getAbsolutePath()); //Use absolute path
        driver.findElement(By.id(SUBMIT)).click();
        WebElement e = driver.findElement(By.id(UPLOADFILE));
        Assert.assertEquals(e.getText(),FILE_NAME,"File is incorrect");
    }
}
