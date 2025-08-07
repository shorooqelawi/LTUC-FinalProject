package tests;

import utils.DBUtils;
import pages.DetailsPage;
import pages.HomePage;
import pages.LogOut;
import pages.LoginPage;
import pages.SignUpPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.Data;

public class TestCases {

    WebDriver driver = new ChromeDriver();
    String TheURL = "https://practicesoftwaretesting.com/";
    Connection con;
    String registeredEmail;
    String registeredPassword;


    @BeforeTest
    public void MySetUp() throws SQLException {
        con = DBUtils.connect();
        driver.get(TheURL);
        driver.manage().window().maximize();
    }
    
    
    @Test(enabled=false)
    public void SignUpWithEmptyForm() throws InterruptedException {
        
        driver.navigate().to("https://practicesoftwaretesting.com/auth/register");

       
        SignUpPage signup = new SignUpPage(driver);

    
        signup.submit();

        
        Thread.sleep(1000);

        String pageText = driver.getPageSource().toLowerCase();
        Assert.assertTrue(pageText.contains("required"), "Expected validation messages were not found.");
    }
    

    @Test(priority = 1,enabled=false)
    public void SignUpPage() throws InterruptedException, SQLException {
        Thread.sleep(1000);
        driver.navigate().to("https://practicesoftwaretesting.com/auth/register");

        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM customers");

        List<Integer> validIndexes = new ArrayList<>();
        int rowIndex = 0;

        while (rs.next()) {
            rowIndex++;
            String firstName = rs.getString("contactFirstName");
            String lastName = rs.getString("contactLastName");
            String street = rs.getString("addressLine1");
            String phone = rs.getString("phone");

            if (firstName != null && lastName != null && street != null && phone != null) {
                validIndexes.add(rowIndex);
            }
        }

        if (validIndexes.isEmpty()) {
            System.out.println("No valid users found.");
            return;
        }

        Random rand = new Random();
        int randomRow = validIndexes.get(rand.nextInt(validIndexes.size()));

        rs.absolute(randomRow);

        String firstName = rs.getString("contactFirstName");
        String lastName = rs.getString("contactLastName");
        String street = rs.getString("addressLine1");
        String city = rs.getString("city");
        String state = rs.getString("state") != null ? rs.getString("state") : "DefaultState";
        String postalCode = rs.getString("postalCode");
        String country = rs.getString("country") != null ? rs.getString("country") : "Jordan";
        String phone = rs.getString("phone").replaceAll("[^0-9]", "");

        // باقي البيانات
        int randomNum = rand.nextInt(10000);
        String cleanLastName = lastName.trim().replaceAll("\\s+", "");
        String email = cleanLastName.toLowerCase() + randomNum + "@test.com";
        String password = "UserPass@1233";
        String dob = "1995-01-01";

        SignUpPage signup = new SignUpPage(driver);
        signup.fillForm(firstName, lastName, dob, street, postalCode, city, state, country, phone, email, password);
        signup.submit();
        registeredEmail = email;
        registeredPassword = password;
        
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://practicesoftwaretesting.com/auth/login", "That not the login page");


    }
    
    
    @Test(priority = 2,enabled=false)
    public void loginWithRegisteredValidData() throws InterruptedException {
    	Thread.sleep(1000);
        LoginPage login = new LoginPage(driver);
        login.login(registeredEmail, registeredPassword);
        Thread.sleep(2000);
        login.submit();
        
      Thread.sleep(2000);

      String pageText = driver.getPageSource().toLowerCase();
        Assert.assertTrue( pageText.contains("account"), "Login did not reach user dashboard.");
    }
    
    
    

    @Test(enabled=false)
    public void loginWithRegisteredInvalidData() throws InterruptedException {
    	Thread.sleep(1000);
    	String email="sshelwo33@gmail.com";
    	String password="122dd#2w";
    	
        LoginPage login = new LoginPage(driver);
        login.login(email,password );
        Thread.sleep(2000);
        login.submit();
      Thread.sleep(2000);

      String pageText = driver.getPageSource().toLowerCase();
        Assert.assertTrue( pageText.contains("account"), "Login did not reach user dashboard.");
    }
    
    
	@Test(priority = 3,enabled = false)
	public void logOutTest() throws InterruptedException {
	    Thread.sleep(2000);


	    LogOut logout = new LogOut(driver);
	    logout.openUserMenu();
	    logout.clickSignOut();
	    Thread.sleep(2000);

	    String currentUrl = driver.getCurrentUrl();
	    Assert.assertEquals(currentUrl, "https://practicesoftwaretesting.com/auth/login", "Logout did not redirect to login page.");
	}

    
	//DANA
	
	
	
	@Test(enabled = true)
    public  void tsearchTest() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		String actual,expected;
		expected=Data.getTooles();
		
		homePage.searchByName(expected);
		
		Thread.sleep(1000);   
		
		homePage.getItemsNames();
		
		
		
		
		actual=Data.getCategory();
		
		System.out.println("Expected: " + expected);
		System.out.println("Actual: " + actual);

		Assert.assertTrue(actual.toLowerCase().contains(expected.toLowerCase()), 
			    "Expected value (ignoring case) to be in actual. Actual: " + actual + ", Expected: " + expected);


		  }
	
	@Test(enabled = false)
	public void testSlider() throws InterruptedException

	{	HomePage homePage = new HomePage(driver);
		
		
		homePage.minSlider();
		Thread.sleep(2000);
		homePage.maxSlider();
		System.out.println("num1="+Data.getMinValue()+" "+"num2"+Data.getMaxValue());

		int min=Data.getMinValue();
		int max=Data.getMaxValue();
		int actual;
	int count = Math.min(Data.getItemsPrices().size(), 3); // لو في أقل من 2، ما بصير error
	if(min<max)
		
	{for(int i=0;i<count;i++)
	{
	
	actual=Data.getItemsPrices().get(i);

		Assert.assertTrue(actual >= min && actual <= max,
		    "Expected value to be between " + min + " and " + max + ", but got: " + actual);

	}}
	else {
		for(int i=0;i<count;i++)
		{
		
		actual=Data.getItemsPrices().get(i);

			Assert.assertTrue(actual >= max && actual <= min,
			    "Expected value to be between " + max + " and " + min + ", but got: " + actual);

		}
	}
	
	

		
		
	}
	
	@Test(enabled = false)//f
	public void testFilters() throws InterruptedException
	{	Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
		DetailsPage detailsPage=new DetailsPage(driver);
		homePage.chooseFillters();
		Thread.sleep(1000);
		homePage.clickOnItems();

		detailsPage.getCategory();
		boolean found = false;

		for (int i = 0; i < Data.getChoosenFilltersNames().size(); i++) {
		    if (Data.getChoosenFilltersNames().get(i).toLowerCase().contains(Data.getCategory().toLowerCase())) {
		        found = true;
		        break;
		    }
		}
System.out.print("List ="+Data.getChoosenFilltersNames()+"real="+Data.getCategory());
		Assert.assertTrue(found, "Expected filter containing '" + Data.getCategory() + "' was not found in the list: " + Data.getChoosenFilltersNames());

		
	}
	
	@Test(enabled = false)
	public void testBrand() throws InterruptedException
	{
		HomePage homePage=new HomePage(driver);
		homePage.byBrand();
		homePage.clickOnItems();
		Thread.sleep(1000);
		DetailsPage detailsPage=new DetailsPage(driver);
		detailsPage.getBrand();
		System.out.println("Expected="+Data.getChoosenBrand()+"Actual="+Data.getBrand());
		Assert.assertTrue(Data.getChoosenBrand().contentEquals(Data.getBrand()),"Expected brand containing '" + Data.getChoosenBrand() + "' was not match: " + Data.getBrand());

		
	}
	
	@Test(enabled = false)//F
	public void testLinks()
	{
		HomePage homePage=new HomePage(driver);
		String[] keyStrings= {"GitHub"," Support","Privacy"," BarnImages"," Unsplash"};
		homePage.hyperLinkes();
		String res="";
		boolean found = false;

		for (int i = 0; i < keyStrings.length; i++) {
		    if (Data.getLinks().toLowerCase().contains(keyStrings[i].toLowerCase())) {
		        found = true;
		        res=keyStrings[i];
		        break;
		    }
		}
		System.out.print("List ="+Data.getLinks()+"real="+res);
		Assert.assertTrue(found, "Expected URL containing '" + Data.getLinks() + "' was not found in the list: " + res);


		}
    
}

