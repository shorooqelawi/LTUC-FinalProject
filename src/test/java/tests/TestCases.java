package tests;

import utils.DBUtils;
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
    

    @Test(priority = 1,enabled=true)
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
    
    
    @Test(priority = 2,enabled=true)
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
    
    
	@Test(priority = 3,enabled = true)
	public void logOutTest() throws InterruptedException {
	    Thread.sleep(2000);


	    LogOut logout = new LogOut(driver);
	    logout.openUserMenu();
	    logout.clickSignOut();
	    Thread.sleep(2000);

	    String currentUrl = driver.getCurrentUrl();
	    Assert.assertEquals(currentUrl, "https://practicesoftwaretesting.com/auth/login", "Logout did not redirect to login page.");
	}

    
    
}

