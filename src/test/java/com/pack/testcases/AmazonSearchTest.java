package com.pack.testcases;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class AmazonSearchTest {
	public static void main(String[] args) {
		
		//STEP1 - Declaration of variables for Search in Amazon 
		String Category = null;
		String Searchvalue = null;
		
		//STEP2 - launch browser
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohan\\Downloads\\Downloaded program files"
				+ "\\BrowserDrivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		//STEP3 - Open www.amazon.in
		driver.get("https://www.amazon.in/");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	    //STEP3 - Login to Amazon Page
	    driver.findElement(By.cssSelector("#nav-signin-tooltip .nav-action-inner")).click();
	    driver.findElement(By.id("ap_email")).sendKeys("mohanadesi@gmail.com");
	    
	    driver.findElement(By.cssSelector("input#continue")).click();
	    driver.findElement(By.id("ap_password")).sendKeys("s@thy@1840");
	    driver.findElement(By.id("signInSubmit")).click();

	    //STEP4 - connect to JDBC inside try loop
	    
	    try 
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
	    			+ "my_new_database","root", "password");
	    	Statement stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery("Select * from amazon");
	    //STEP5 - Retrieving values of Category and Searchvalue from the table amazon
	    	while (rs.next()) {
	    		System.out.println(rs.getString(2) + " " + rs.getString(3));
				Category = rs.getString(2);
				Searchvalue = rs.getString(3);
	    
	    	
	    	//STEP6 - Select category as per in table amazon from drop down
	    	
	    	WebElement dropDown = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
	    	Select sldropDown = new Select(dropDown);
	    	sldropDown.selectByVisibleText(Category);
	    	//STEP7 - sending Searchvalue field retrieved from table amazon
	    	WebElement searchText = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	    	searchText.clear();
	    	searchText.sendKeys(Searchvalue);
	    	WebElement searchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
	    	searchButton.click();
	    //STEP8 - list the type of products in the particular category	
	    	List<WebElement> ProductNameList = driver.findElements(By.xpath("//*[@data-component-type='s-search-result']//span[@class='a-size-medium a-color-base a-text-normal']"));
			for ( int i = 0; i < ProductNameList.size(); i++ ) {
				
				System.out.println( (i+1) +" " + ProductNameList.get(i).getText());
			}
	    //STEP9 - Getting the count of  result of search value - page wise
	    	List<WebElement> ResList = driver.findElements(By.xpath("//*[@data-component-type='s-search-result']"));
			System.out.println("Total search result pagewise are: " + ResList.size());
			String ResListCount = String.valueOf(ResList.size());
		//STEP10 - Retrieving the count of result of search value - pagewise from Result bar appearing in the page	
			WebElement ResultBarText = driver.findElement(By.xpath("//*[@class='a-section a-spacing-small a-spacing-top-small']/span[1]"));
			System.out.println("Result bar: " + ResultBarText.getText());
			
			// Example : 1-48 of over 10,000 results for

			String ResultCountTxt = ResultBarText.getText().substring(2, 4);
			System.out.println("Result Count from result bar = " + ResultCountTxt);
	    	//Count search result - when Example: 5 results for
	    	WebElement ResCount = driver.findElement(By.xpath("//*[contains(text(), 'results for')]"));
	    	String[] texts = ResCount.getText().split(" ");
	    	String num = "";
	    	for (int i = 0; i < texts.length; i++) {
	    		if (texts[i].equals("results")) {
	    			num = texts[i - 1];
	    			break;
	    		}
	    	}
			System.out.println("Total search results for the particular category are: " + num);
			//STEP11 - checking the search result count calculated in STEP8, if its equal to search result count appearing in Result bar text (STEP9) 
			if ( ResultCountTxt.equals(ResListCount)) {
				System.out.println("Yes! Result is matching");	
			}
			else {
				System.out.println("Oh no!! Result is not matching");
				
				System.out.println("Extra numbers are sponsored items");
			}
			System.out.println("____________");
			
	    }
	    	
	    	}
	    catch (ClassNotFoundException e) 
	    {
			
			// e.printStackTrace();
			System.out.println("Class not found");
	  	} catch (SQLException e) 
	    {
			
			
			System.out.println("SQL Exception");
				
		}
	    //STEP12 - capture screen 		
		
		TakesScreenshot TsObj = (TakesScreenshot) driver;
		
		File myFile = TsObj.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(myFile, new File("C://Amazon images//amazonsearch.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//STEP13 -  close after 5 seconds
				try {
					Thread.sleep(5000); //time is in ms (1000 ms = 1 second)
					driver.close();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
		

}}
