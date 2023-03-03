package com.pack.testcases;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;

import com.pack.pages.HomePage;

public class HomePageTest {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohan\\Downloads\\Downloaded program files"
				+ "\\BrowserDrivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		//STEP2 - Open www.amazon.in
		driver.get("https://www.amazon.in/");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	 // Creating object of HomePage and driver instance is passed as parameter to constructor of Homepage.Java
        HomePage homePage= new HomePage(driver);
     // Click on SignIn Button
        homePage.clickSignIn(); 
     // Type EmailAddress
        homePage.typeEmailId("mohanadesi@gmail.com"); 
    // EmailId value is passed as parameter which in turn will be assigned to the method in HomePage.Java
     // Click on Continue Button
        homePage.clickContinueButton();
    // Type Password Value
        homePage.typePassword("s@thy@1840"); 
    // Password value is passed as parameter which in turn will be assigned to the method in HomePage.Java
     // Click on SignInSubmit Button
        homePage.clickSignInSubmit();
        //Get title of login page and verify if login is successful
        homePage.titleVerify();
    	
      //Quit browser
        driver.quit();
    	
	}
}
