package Packgae;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Class {
	WebDriver driver;

	@BeforeClass

	public void Beforeclass() {
		System.setProperty("Webdriver.chrome.driver",
				"C:\\Users\\banti\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@BeforeMethod

	public void Beformethod() {
		driver.get("https://www.entrata.com/");
		driver.manage().window().maximize();

	}

	@Test
	public void applywatchdemo() throws IOException, InterruptedException {

		WebElement watchdemobutton = driver.findElement(
				By.xpath("(//a[@class='button-default solid-dark-button'][normalize-space()='Watch Demo'])[1]"));
		watchdemobutton.click();
		// using Assert
		String url = driver.getCurrentUrl();
		System.out.println(url);
        Assert.assertEquals(url, "https://go.entrata.com/watch-demo.html");
        
		WebElement firstname = driver.findElement(By.xpath("//input[@id='FirstName']"));
		firstname.sendKeys("Gopal");

		WebElement lastname = driver.findElement(By.xpath("//input[@id='LastName']"));
		lastname.sendKeys("Mundhe");

		WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
		email.sendKeys("gopal5@gmail.com");

		WebElement Company = driver.findElement(By.xpath("//input[@id='Company']"));
		Company.sendKeys("google");

		WebElement Phone = driver.findElement(By.xpath("//input[@id='Phone']"));
		Phone.sendKeys("7667890982");
        
		WebElement unitcount = driver.findElement(By.xpath("//select[@id='Unit_Count__c']"));
		Select s1 = new Select(unitcount);
		s1.selectByIndex(1);

		WebElement jobtitle = driver.findElement(By.xpath("//input[@id='Title']"));
		jobtitle.sendKeys("Tester");

		WebElement iam = driver.findElement(By.xpath("//select[@id='demoRequest']"));
		Select s2 = new Select(iam);
		s2.selectByIndex(1);

		WebElement submitbutton = driver.findElement(By.xpath("//*[@id=\"mktoForm_1108\"]/div[17]/span/button"));

		Actions as = new Actions(driver);
		as.click(submitbutton).perform();

		Thread.sleep(2000);
		WebElement text1 = driver
				.findElement(By.xpath("(//h2[normalize-space()='Thank you for requesting more information!'])[1]"));
		String text = text1.getText();
		System.out.println(text);

		File sours = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destine = new File("C:\\Users\\banti\\Documents\\Screenshot\\test" + ".jpg");
		FileHandler.copy(sours, destine);
		
		// navigating page
		driver.navigate().to("https://www.entrata.com/");
		
		// using Assert
		String actual = driver.getCurrentUrl();
		System.out.println(actual);
		boolean result = actual.equals("https://www.entrata.com/");
		Assert.assertTrue(result);

		// verifying dynamic element

		List<WebElement> list = driver.findElements(By.xpath("//div[@class='main-nav-link']"));
		System.out.println(list.size());

		for (int i = 0; i < list.size(); i++) {

			WebElement element = list.get(i);
			element.click();
			Thread.sleep(3000);
			System.out.println(element.getText());

		}

	}

}
