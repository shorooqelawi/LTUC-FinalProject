package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class baseTest {

    WebDriver driver = new ChromeDriver();
    String TheURL = "https://practicesoftwaretesting.com/";

    @BeforeTest
    public void MySetUp() {
        driver.get(TheURL);
        driver.manage().window().maximize();
    }

    @Test
    public void verifySetup() {
        // هذا بس للتأكد إنّ الكود يشتغل
        System.out.println("Base setup is running successfully!");
    }
}
