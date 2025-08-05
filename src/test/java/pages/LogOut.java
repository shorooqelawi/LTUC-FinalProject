package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOut {
    WebDriver driver;

    By userMenu = By.xpath("//a[@id='menu']");

    By signOutButton = By.xpath("//a[@data-test='nav-sign-out']");

    public LogOut(WebDriver driver) {
        this.driver = driver;
    }

    public void openUserMenu() throws InterruptedException {
        Thread.sleep(1000); 
        driver.findElement(userMenu).click();
    }

    public void clickSignOut() throws InterruptedException {
        Thread.sleep(1000); 
        driver.findElement(signOutButton).click();
    }
}


