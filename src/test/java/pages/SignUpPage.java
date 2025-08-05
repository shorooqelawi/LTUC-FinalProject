package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By dob = By.id("dob");
    By street = By.id("street");
    By postalCode = By.id("postal_code");
    By city = By.id("city");
    By state = By.id("state");
    By country = By.id("country");
    By phone = By.id("phone");
    By email = By.id("email");
    By password = By.id("password");
    By submitBtn = By.xpath("//button[@type='submit']");

    public void fillForm(String fName, String lName, String date, String str, String zip, String ct,
                         String st, String cntry, String phn, String em, String pwd) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(dob).sendKeys(date);
        driver.findElement(street).sendKeys(str);
        driver.findElement(postalCode).sendKeys(zip);
        driver.findElement(city).sendKeys(ct);
        driver.findElement(state).sendKeys(st);
        driver.findElement(country).sendKeys(cntry);
        driver.findElement(phone).sendKeys(phn);
        driver.findElement(email).sendKeys(em);
        driver.findElement(password).sendKeys(pwd);
    }

    public void submit() {
        driver.findElement(submitBtn).click();
    }
}
