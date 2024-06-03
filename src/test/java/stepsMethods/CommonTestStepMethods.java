package stepsMethods;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.LogInPage;
import pageObjects.TasksTabPage;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;
import test.CommonTest;

public class CommonTestStepMethods extends BaseActi  {

	static TasksTabPage TasksTabPage = new TasksTabPage(driver);
	public static String dateTime() {
		// Getting the current date and time
		Date currentDate = new Date();
		// Formatting the date and time
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = dateFormat.format(currentDate);
		return formattedDateTime;
	}
	public static void login() throws InterruptedException {
		String emailValue = prop.getProperty("email");
		String passwordValue = prop.getProperty("passwd");
		ElementSelector ElementSelector = new ElementSelector(driver);
		SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);
		LogInPage LogInPage = new LogInPage(driver);
		log = LogManager.getLogger(CommonTest.class.getName());

		// Log in
		SendKeysToElement.snedKeysToElementById(LogInPage.userName(), prop.getProperty("userName"));
		Thread.sleep(1000);
		SendKeysToElement.sendKeysToElementByXpath(LogInPage.password(), prop.getProperty("password"));
		Thread.sleep(1000);
		ElementSelector.clickById(LogInPage.logInButton());
	}

	public static void openigTask() {
		ElementSelector.clickById(TasksTabPage.tasksTab());
		String ViewTimeTrackPageTabURL = driver.getCurrentUrl();
		Assert.assertEquals(ViewTimeTrackPageTabURL, "https://online.actitime.com/shvia/tasks/tasklist.do");
	}

	public static String creatingCustomer() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String formattedDateTime = dateTime();
		// creating new customer
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.addNewBtn())));
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		ElementSelector.clickByxpath(TasksTabPage.newCustomerBtn());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.enterCustomerNameTextBox())));
		driver.findElement(By.xpath(TasksTabPage.enterCustomerNameTextBox())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enterCustomerNameTextBox(),
				prop.getProperty("customerName") + formattedDateTime);
		System.out.println("Created customer name "+prop.getProperty("customerName") + formattedDateTime);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.createCustomer())));
		log.debug("Clicked on create customer button");
		return formattedDateTime;
		
	}

	public static void creatingProject(String dateTime) throws InterruptedException {
		String formattedDateTime = dateTime;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))));
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		log.debug("Clicked on add new button again");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.newProject()))));
		ElementSelector.clickByxpath(TasksTabPage.newProject());
		log.debug("clicked on new project");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(TasksTabPage.enterProjectName()))));
		driver.findElement(By.xpath(TasksTabPage.enterProjectName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enterProjectName(),
				prop.getProperty("projectName") + formattedDateTime);
		log.debug("Entered project name");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.createProject()))).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.createProject()))));
		ElementSelector.clickByxpath(TasksTabPage.createProject());
		log.debug("Clicked on create customer button");
	}

	public static void creatingTask(String dateTime) {
		String formattedDateTime = dateTime;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// creating new task
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))).perform(); //
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))));
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		log.debug("Clicked on add new button to create new task");
		ElementSelector.clickByxpath(TasksTabPage.createNewTasks());
		log.debug("Clicked on create new task");
		try {
		driver.findElement(By.xpath(TasksTabPage.enteringTaskName())).clear();
		}
		catch(StaleElementReferenceException e) {
			driver.findElement(By.xpath(TasksTabPage.enteringTaskName())).clear();
		}
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enteringTaskName(),
				prop.getProperty("taskName") + formattedDateTime);
		System.out.println("Created task : " +prop.getProperty("taskName") + formattedDateTime);
		log.debug("Entered Task name");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.createTask()))));
		ElementSelector.clickByxpath(TasksTabPage.createTask());
	}

	public static String creatingNewTaskFromAddTaskBtnTab() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		String formattedDateTime = dateTime();
		// Adding new task from tasks tab
		String newSubTask = (prop.getProperty("newTaskName") + formattedDateTime);
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.addNewTaskBtn())));
		ElementSelector.clickByxpath(TasksTabPage.addNewTaskBtn());
		}
		catch(ElementClickInterceptedException e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.addNewTaskBtn())));
			ElementSelector.clickByxpath(TasksTabPage.addNewTaskBtn());
		}
		log.debug("Clicked on add new button ");
		driver.findElement(By.xpath(TasksTabPage.addingNewTaskTextBox())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.addingNewTaskTextBox(), newSubTask);
		log.debug("Entered new task ");
		ElementSelector.clickByxpath(TasksTabPage.addNewBtnAfterEneteringTask());
		log.debug("Clicked on add new button after entering new task");
		System.out.println(newSubTask);
		return newSubTask;
	}
		
	public static void serachingClickingNewlyCreatedCustomer(String formattedDateTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
		driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),
				prop.getProperty("customerName") + formattedDateTime);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstCustomerName())));
		ElementSelector.clickByxpath(TasksTabPage.firstCustomerName());
		
	}
	
	public static void serachingClickingNewlyCreatedCustomerCreatingTask(String formattedDateTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
		driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),
				prop.getProperty("customerName") + formattedDateTime);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstCustomerName())));
		try {
			ElementSelector.clickByxpath(TasksTabPage.firstCustomerName());
			}
			catch(StaleElementReferenceException e) {
				ElementSelector.clickByxpath(TasksTabPage.firstCustomerName());
			}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.viewCustomerProjects())));
		ElementSelector.clickByxpath(TasksTabPage.viewCustomerProjects());
		ElementSelector.clickByxpath(TasksTabPage.firstProjectName());


	}
	
}
