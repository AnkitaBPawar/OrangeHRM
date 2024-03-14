package Test;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeeTest {
	private static final String[] FIRST_NAMES = { "John", "Alice", "Michael", "Emma", "James", "Sophia", "William",
			"Olivia", "Benjamin", "Charlotte" };
	private static final String[] LAST_NAMES = { "Smith", "Johnson", "Brown", "Taylor", "Wilson", "Martinez",
			"Anderson", "Thomas", "Jackson", "White" };
	private static final String[] USER_NAMES = { "User1", "User2", "User3", "User4", "User5", "User6", "User7" };

	public static String generateRandomFirstName() {
		Random random = new Random();
		return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
	}

	public static String generateRandomLastName() {
		Random random = new Random();
		return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
	}

	public static String generateRandomUserName() {
		Random random = new Random();
		return USER_NAMES[random.nextInt(USER_NAMES.length)];
	}

	public static void main(String[] args) throws InterruptedException {

		// Initialize ChromeDriver
		WebDriver driver = new ChromeDriver();

		// Open the website
		driver.get("https://klaaradmin-trials711.orangehrmlive.com/client/#/dashboard");
		driver.manage().window().maximize();

		// Log in
		WebElement usernameInput = driver.findElement(By.id("txtUsername"));
		usernameInput.sendKeys("Admin");
		WebElement passwordInput = driver.findElement(By.id("txtPassword"));
		passwordInput.sendKeys("SyN6Ktl@O0");
		driver.findElement(By.xpath("//img[@class='icon login-icon']")).click();

		// Wait until Employee Management Module is visible
		Thread.sleep(2000);
		WebElement employeeManagementModule = driver
				.findElement(By.xpath("//li[@id='left_menu_item_11']//a[1]//span[1]"));

		// Navigate to Employee Management Module
		employeeManagementModule.click();
		Thread.sleep(10000);

		// Click on "Add Employee" button
		driver.findElement(By.xpath("//i[normalize-space()='add']")).click();

		// Wait until "Add Employee" modal is visible
		Thread.sleep(10000);

		String firstName = generateRandomFirstName();
		String lastName = generateRandomLastName();
		String userName = generateRandomUserName();

		// Fill in the required fields
		WebElement firstNameInput = driver.findElement(By.xpath("//input[@id='first-name-box']"));
		firstNameInput.sendKeys(firstName);
		WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='last-name-box']"));
		lastNameInput.sendKeys(lastName);

		// Disable Auto Generate Employee ID
		driver.findElement(By.xpath(
				"//div[@class='form-group col-5 auto-generate-employee-id']//div[@class='custom-control custom-switch']"))
				.click();

		// Change joined date
		driver.findElement(By.xpath("//i[@class='material-icons date-picker-open-icon']")).click();
		driver.findElement(By.xpath("//div[@aria-label='2024-03-18']")).click();

		// Choose location as "India Office"
		WebElement locationDropdown = driver.findElement(
				By.xpath("//button[@type='button']//i[@class='material-icons'][normalize-space()='arrow_drop_down']"));
		locationDropdown.click();
		driver.findElement(By.xpath("//option[text()='India Office']")).click();

		// Enable "Create Login Details" and add username/password
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/oxd-decorator[1]/div[1]/div[2]/div[1]/div[5]/div[1]/div[1]/span[1]/div[2]"))
				.click();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("YourPassword");
		driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("YourPassword");

		// Set Admin Role to "Regional HR Admin"
		WebElement adminRoleSelect = driver.findElement(By.xpath(
				"//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/oxd-decorator[1]/div[1]/div[2]/div[1]/div[11]/div[1]/div[3]/div[1]/div[2]/div[1]/button[1]/i[2]"));
		adminRoleSelect.click();
		driver.findElement(By.xpath("//option[text()='Regional HR Admin']")).click();

		// Disable all regions and select the same region as the location
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/oxd-decorator[1]/div[1]/div[2]/div[1]/div[12]/div[1]/div[1]/div[1]/div[1]/span[1]/div[2]"))
				.click();
		WebElement regionCheckbox = driver.findElement(By.xpath("//input[@placeholder='Select Regions']"));
		regionCheckbox.click();

		String region = locationDropdown.toString();
		String region1 = region.substring(0, 2);
		String newregion = regionCheckbox.toString();
		String newregion1 = newregion.substring(0, 2);

		if (region1.equals(newregion1)) {
			driver.findElement(By.xpath("//li[@id='IN']")).click();
		}

		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/oxd-decorator[1]/div[1]/div[2]/div[1]/div[12]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/span[1]/div[2]"))
				.click();
		driver.findElement(By.xpath("//input[@placeholder='Select Locations']")).click();
		driver.findElement(By.xpath("//span[@class='multi-select-title']")).click();

		// Click on "Next" button and verify personal details page
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='modal-save-button']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement personalDetailsPage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Personal Details']")));
		System.out.println("Landed on the personal details page: " + personalDetailsPage.getText());

		Thread.sleep(10000);
		// Skip adding personal details
		driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();

		// Confirm landing on Employee Details page
		WebElement employeeDetailsPage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Employment Details']")));
		System.out.println("Landed on the employee details page: " + employeeDetailsPage.getText());

		Thread.sleep(10000);
		// Select "Full-Time Permanent" as employment status and add comments
		driver.findElement(By.xpath("//div[4]//div[1]//div[2]//div[1]//button[1]//i[2]")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Full-Time Permanent']")).click();
		driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys("YourComments");

		// Click on "Next" button and verify landing on Contact Details page
		driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
		WebElement contactDetailsPage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Contact Details']")));
		System.out.println("Landed on the contact details page: " + contactDetailsPage.getText());

		// Skip adding contact details
		Thread.sleep(10000);

		driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();

		// Verify landing on Onboarding page
		WebElement onboardingPage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Onboarding']")));
		System.out.println("Landed on the onboarding page: " + onboardingPage.getText());

		// In dropdown, select "Onboarding - India" and Save
		Thread.sleep(5000);

		WebElement m = driver.findElement(By.xpath("//input[@value='-- Select --']"));
		Actions a = new Actions(driver);
		a.moveToElement(m).perform();

		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

		// Verify landing on employee details page after saving
		Thread.sleep(5000);
		WebElement employeepage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Profile']")));
		System.out.println("Landed on the Employee details page: " + employeepage.getText());

		// Close the browser
		driver.quit();
	}
}
