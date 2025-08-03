package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SignUpPage;

public class TestCases {

    WebDriver driver = new ChromeDriver();
    String TheURL = "https://practicesoftwaretesting.com/";
    Connection con;

    @BeforeTest
    public void MySetUp() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "shorooq2002_666");
        driver.get(TheURL);
        driver.manage().window().maximize();
    }

    @Test
    public void SignUpPage() throws InterruptedException, SQLException {
        Thread.sleep(1000);
        driver.navigate().to("https://practicesoftwaretesting.com/auth/register");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM customers");

        while (rs.next()) {
            String firstName = rs.getString("contactFirstName");
            String lastName = rs.getString("contactLastName");
            String street = rs.getString("addressLine1");
            String city = rs.getString("city");
            String state = rs.getString("state") != null ? rs.getString("state") : "DefaultState";
            String postalCode = rs.getString("postalCode");
            String country = rs.getString("country") != null ? rs.getString("country") : "Jordan";
            String phone = rs.getString("phone");

            if (firstName == null || lastName == null || street == null || phone == null) {
                continue;
            }

            // إزالة الرموز من رقم الهاتف
            phone = phone.replaceAll("[^0-9]", "");

            // توليد إيميل بدون نقطة
            int randomNum = (int)(Math.random() * 10000);
            String email = lastName.toLowerCase()+ randomNum+"@test.com";

            String password = "UserPass@1233";
            String dob = "1995-01-01";

            SignUpPage signup = new SignUpPage(driver);
            signup.fillForm(firstName, lastName, dob, street, postalCode, city, state, country, phone, email, password);
            signup.submit();

            break;
        }

    }
}

