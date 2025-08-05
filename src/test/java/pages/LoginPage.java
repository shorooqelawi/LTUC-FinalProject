package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By emailField = By.id("email");
    By passwordField = By.id("password");
    By loginButton = By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input");

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        
    }
    
    public void submit() {
    	driver.findElement(loginButton).click();
    }
}
