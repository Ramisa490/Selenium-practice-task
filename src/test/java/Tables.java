import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Tables extends Base
{
    protected final String TABLES = String.format(PRECISE_TEXT_XPATH, "Sortable Data Tables");
    protected final String Due = "//table[@id='table1']/tbody/tr/td[4]";
    double Actual;
    double Expected = 251.0;
    String Currency = "[^\\d.]"; // Exclude all characters, only keep digits and dots

    @Test
    public void tables()
    {
        driver.findElement(By.xpath(TABLES)).click();

        // Find all elements in the Due column
        List<WebElement> elements = driver.findElements(By.xpath(Due));

        // Iterate through each element in the Due column
        for (WebElement element : elements) //for-each loop :  iterate over each WebElement in the elements list. For each iteration, the variable element is assigned the current WebElement in the list.
        {
            String tableValue = element.getText();
            String cleaned_amount = tableValue.replaceAll(Currency, ""); // Replace non-digits with an empty string
            Actual += Double.parseDouble(cleaned_amount); // Parse the cleaned "Due" value as a double and add
        }

        System.out.println("Total: " + Actual);

        // Assert the total sum
        Assert.assertEquals(Actual, Expected, "Incorrect");
    }
}
