package day27.Dropdown_synchronization;

import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Practice_Screenshot {
	WebDriver driver; 
	
public void launchingBrowser() throws Exception  {
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.navigate().to("https://phptravels.com/demo/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	
	
	driver.findElement(By.xpath("//input[contains(@name, 'first_name')]")).sendKeys("sivakumar");
	driver.findElement(By.xpath("//input[starts-with(@class,'last_')]")).sendKeys("A");
	driver.findElement(By.xpath("//input[@type='text'][@name='business_name']")).sendKeys("Ashok");
	driver.findElement(By.xpath("//div[@class='form-floating mb-3']/child::input[@name='email']")).sendKeys("sivadot07@gmail.com");
	
	WebElement num1 = driver.findElement(By.id("numb1"));
	String text1 = num1.getText();
	int fnum = Integer.valueOf(text1);
	WebElement num2 = driver.findElement(By.id("numb2"));
	String text2 = num2.getText();
	int snum = Integer.valueOf(text2);
	int ans=(fnum+snum); System.out.println(ans);
	String ans1 =""+ans;
	driver.findElement(By.id("number")).sendKeys(ans1);
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
	WebElement sumbit = driver.findElement(By.id("demo"));
	wait.until(ExpectedConditions.elementToBeClickable(sumbit));
	//sumbit.click();
}
//	Taking screenshot using	TakesScreenshot Interface
	public void firstmethod() throws Exception {
	launchingBrowser();
	TakesScreenshot tc =(TakesScreenshot)driver;
	File source = tc.getScreenshotAs(OutputType.FILE);
	File des= new File("C:\\Users\\HP\\Eclipse workspace new\\SeleniumPractice\\screenshot\\sc_one.png");
	
//	to copy the file here we used FileUtils.copyFile it is from org.apache.commons.io.FileUtils package;
	FileUtils.copyFile(source, des);
//	there is an another way	to copy file from org.openqa.selenium.io.FileHandler package;
	FileHandler.copy(source, des);
	
	}
	
//  In Robot class we can able to perform takes screenshot actions	
	public void secondtmethod() throws Exception {
	launchingBrowser();
	Robot robot = new Robot();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Rectangle rct = new Rectangle(screenSize);
    BufferedImage source = robot.createScreenCapture(rct);
    File des= new File("C:\\Users\\HP\\Eclipse workspace new\\SeleniumPractice\\screenshot\\file.png");
    ImageIO.write(source, "png", des);
	}

	public static void main(String[] args) throws Exception {
		Practice_Screenshot pc = new Practice_Screenshot();
	//	pc.firstmethod();
		pc.secondtmethod();
	}
	
}
