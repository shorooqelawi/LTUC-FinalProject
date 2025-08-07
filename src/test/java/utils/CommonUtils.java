package utils;
import pages.HomePage;
import data.*;
import net.bytebuddy.asm.Advice.This;

import java.util.List;
import java.util.Random; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CommonUtils {
	public static int random(int range)
	{
		Random randoom =new Random();
		
		return randoom.nextInt(range);
	}

	 
	 public static void flushList(List<?>list)
	 {
		list.clear();
	 }
	 
	    public static WebElement waitForVisibility(WebDriver driver, By locator, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    public static WebElement waitToBeClickable(WebDriver driver, By locator, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        return wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }
	    
	   public static void refreshPage(WebDriver driver)
	   {driver.navigate().refresh();
}
	}

