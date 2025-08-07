package pages;

import pages.HomePage;
import data.*;
import utils.CommonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DetailsPage {
	WebDriver driver;
 
	public DetailsPage(WebDriver driver)
	{
		this.driver=driver;
	}
	By category=By.cssSelector("[aria-label='category']");
	By brand=By.cssSelector("[aria-label=\"brand\"]");
	
	public void getCategory()
	{
		WebElement categoryElement=CommonUtils.waitForVisibility(driver,category,3);
		
		if (driver.getPageSource().contains("There are no products found.")) 
		{HomePage homePage=new HomePage(driver);
         homePage.chooseFillters();
         Data.setCategory(categoryElement.getText());
		}
		else {
			{Data.setCategory(categoryElement.getText());}
		}
		
	}
	
	public void getBrand()
	{
		WebElement brandElement=CommonUtils.waitForVisibility(driver,brand,3);
		Data.setBrand(brandElement.getText());
	}

}
