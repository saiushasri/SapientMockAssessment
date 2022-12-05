package exam;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task1 {
	WebDriver driver;
	DesiredCapabilities capabilities;
	@BeforeMethod
	public void setup()
	{
		String USERNAME="saiushasrikashi_uCqxgy";
		String AUTOMATE_KEY="wvL3TRwmUsGokbV7fjgq";
		URL url=null;
		try {
			url = new URL("https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		capabilities=new DesiredCapabilities();
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("os", "Windows");
		capabilities.setCapability("osVersion", "10");
		driver=new RemoteWebDriver(url, capabilities);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://137.184.76.209/orangehrm-4.9");
		
		
	}
	@Test
	public void test1()
	{
		SoftAssert sAssert=new SoftAssert();
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("ASDFwhuhpp3lhdz3k47iw%");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		sAssert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		sAssert.assertTrue(driver.findElement(By.xpath("//input[@id='btnLogin']")).isDisplayed());
		sAssert.assertAll();
	}
	@Test
	public void test2()
	{
		SoftAssert sAssert=new SoftAssert();
		driver.findElement(By.id("txtUsername")).sendKeys("Admin1");
		driver.findElement(By.id("txtPassword")).sendKeys("ASDFwhuhpp3lhdz3k47iw%_");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		sAssert.assertTrue(driver.findElement(By.xpath("//span[@id='spanMessage']")).getText().contains("Invalid credentials"));
		sAssert.assertAll();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
