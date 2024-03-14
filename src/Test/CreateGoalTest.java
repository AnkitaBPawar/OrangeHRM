package Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateGoalTest {

	public static void main(String[] args) throws InterruptedException {

		// Initialize ChromeDriver
		WebDriver driver = new ChromeDriver();

		// Navigate to the website
		driver.get("https://klaaradmin-trials711.orangehrmlive.com/client/#/dashboard");
		driver.manage().window().maximize();

		// Login
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("SyN6Ktl@O0");
		driver.findElement(By.xpath("//img[@class='icon login-icon']")).click();

		// Navigate to the Performance module
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[normalize-space()='More']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='More']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='left_menu_item_18']//a[1]//span[1]")))
				.click();

		Thread.sleep(5000);
		// Select "Goals" -> "My Goals"
		driver.findElement(By.xpath("//a[@data-automation-id='more_menu_child_menu_top_more']")).click();
		Thread.sleep(5000);
		WebElement m = driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/header[1]/nav[1]/top-menu[1]/div[1]/div[1]/div[3]/top-level-menu-item[1]/div[1]/sub-menu-container[1]/div[1]/div[1]"));
		Actions a = new Actions(driver);
		a.moveToElement(m).perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/header[1]/nav[1]/top-menu[1]/div[1]/div[1]/div[3]/top-level-menu-item[1]/div[1]/sub-menu-container[1]/div[1]/div[1]/sub-menu-container[1]/div[1]/div[2]/a[1]"))
				.click();

		// Click on the "Create Goal" button and verify landing on the page
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[3]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/list[1]/div[1]/div[1]/button[1]/span[1]"))
				.click();
		WebElement createGoalPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[3]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[1]/span[1]/h4[1]")));
		System.out.println("Landed on the Create Goal page: " + createGoalPageTitle.getText());

		// Fill in all required fields to create a goal
		Thread.sleep(10000);
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[3]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/oxd-decorator[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"))
				.sendKeys("Test Goal");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		// actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='dueDate']"))).click();
		driver.findElement(By.xpath("//i[normalize-space()='date_range']")).click();
		driver.findElement(By.xpath("//div[@aria-label='2024-03-15']")).click();

		Thread.sleep(3000);
		// SCROLL UP
		actions.sendKeys(Keys.PAGE_UP).build().perform();
		// Insert an image in the editor
		// Code to insert image in the editor

		Thread.sleep(3000);
		// Set the priority of the goal to "High" and weight to "20"
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[3]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/oxd-decorator[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/button[1]/i[2]"))
				.click();
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[3]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/oxd-decorator[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]"))
				.click();
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[3]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/oxd-decorator[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/oxd-spinner[1]/div[1]/input[1]"))
				.clear();
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[3]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/oxd-decorator[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/oxd-spinner[1]/div[1]/input[1]"))
				.sendKeys("10");

		actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		// Save the goal
		driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();

		// Verify Goal Details
		WebElement goalDetailsTitle = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Goal Details']")));
		System.out.println("Verified Goal Details: " + goalDetailsTitle.getText());

		// Close the browser
		driver.quit();
	}
}
