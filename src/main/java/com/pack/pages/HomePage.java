package com.pack.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
	WebDriver driver;
	
	By SigninButton = By.cssSelector("#nav-signin-tooltip .nav-action-inner");
	By EmailAddress = By.id("ap_email");
	By ContinueButton = By.cssSelector("input#continue");
	By Password = By.id("ap_password");
	By SigninSubmit = By.id("signInSubmit");
			// Method to click SignIn Button
		    public void clickSignIn(){
		        driver.findElement(SigninButton).click();
		    }
			 // Method to type EmailId
		    public void typeEmailId(String Id){
		        driver.findElement(EmailAddress).sendKeys(Id);
		    }
		    public void clickContinueButton(){
		        driver.findElement(ContinueButton).click();
		    }
		    // Method to type Password
		    public void typePassword(String PasswordValue){
		        driver.findElement(Password).sendKeys(PasswordValue);
		    }
		  
		    public void clickSignInSubmit(){
		        driver.findElement(SigninSubmit).click();
		    }
		    //Verification of Actual title with expected title of the page after login
		    String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		    public void titleVerify(){
		        try {
					Thread.sleep(5000);
					String actualTitle = driver.getTitle();
					System.out.println("Actual title of the page = " + actualTitle);
			        System.out.println("Expected title of the page = " +expectedTitle);
			        if(actualTitle.equals(expectedTitle)) {
			        	System.out.println("Login test completed successfully");
			        } else{
			        	System.out.println("Login test failed");
			        }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
			
		 private boolean Actual_Title(String expected_Title2) {
				// TODO Auto-generated method stub
				return false;
			}
		// Constructor
		    // Gets called when object of this page is created in MainClass.java
		    public HomePage(WebDriver driver)
		    {
		        // "this" keyword is used here to distinguish global and local variable "driver"
		        //gets driver as parameter from MainClass.java and assigns to the driver instance in this class 
		 
		        this.driver=driver;
		    }
}
