package Group2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
public class TestCase2 {
    public WebDriver driver;
    public WebDriverWait wait;

    //Navigate to https://test-basqar.mersys.io/
    //Enter username & passport click on sign in button
    @BeforeClass
    @Parameters({"username", "password", "path"})
    public void setUp(String username, String password,String path) {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get("https://test-basqar.mersys.io");
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();
        //cookie part
        driver.findElement(By.xpath("//a[@class='cc-btn cc-dismiss']")).click();

    }
    @Test
    @Parameters({"name", "regStart", "regEnd"})
    public void EntranceExams(String name, String regStart,String regEnd ) {
        //Click on Entrance Exams
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2)")).click();
        //Click on setup
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(1)")).click();
        //Click on Entrance Exams
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(1) > .children > .nav-item:nth-child(1)")).click();
        //Click on plus icon
        driver.findElement(By.cssSelector("svg[data-icon='plus']")).click();
        //Enter name

        WebElement findname = driver.findElement(By.cssSelector("ms-text-field>input"));
        findname.sendKeys(name);
        // Academic period
        driver.findElement(By.cssSelector("mat-form-field mat-select[aria-label='Academic Period']")).click();
        driver.findElement(By.cssSelector(".cdk-overlay-container mat-option:first-child")).click();

        // Grade level
        driver.findElement(By.cssSelector("mat-select[aria-label='Grade Level']")).click();
        driver.findElement(By.cssSelector(".cdk-overlay-container mat-option:first-child")).click();

        // Reg start
        WebElement findregStart = driver.findElement(By.cssSelector("input[placeholder='Reg.Start']"));
        findregStart.sendKeys(regStart);
        //  Reg end

        WebElement findregEnd = driver.findElement(By.cssSelector("input[placeholder='Reg.End']"));
        findregEnd.sendKeys(regEnd);


        //Click on save
        driver.findElement(By.cssSelector("div[fxlayoutalign='start stretch']>button:nth-child(2)>span")).click();

        //Navigate to entrance exam again
        //Click on Entrance Exams
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2)")).click();
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(1)")).click();
        //Click on Entrance Exams
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(1) > .children > .nav-item:nth-child(1)")).click();
        //Verify all the inputs are as created from the table which is in the list

        WebElement text = driver.findElement(By.xpath(" //td[contains(text(),'Hacer')]"));
        String excepted = text.getText();
        Assert.assertEquals(name, excepted);
    }
    @Test
    public void remove()  {
        //remove
        driver.findElement(By.cssSelector(" ms-table tbody tr  td ms-delete-button")).click();
        driver.findElement(By.cssSelector("mat-dialog-container [type='submit']")).click();

        try {
            wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector("div[class='toast-success ngx-toastr ng-trigger ng-trigger-flyInOut']" ) ) );
            System.out.println("Removed success!");
        } catch( Exception e) {
            Assert.fail("Removed failure",e);
        }
    }

    @AfterClass
    public void quit(){

        driver.quit();
    }
}
