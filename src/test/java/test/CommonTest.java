package test;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LogInPage;
import pageObjects.TasksTabPage;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;
import stepsMethods.CommonTestStepMethods;

public class CommonTest extends BaseActi {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	TasksTabPage TasksTabPage = new TasksTabPage(driver);
	
	
	
	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */
	@Test(priority = 1)
	// Log in test
	public void LoginActiTest() throws IOException, InterruptedException {
		String emailValue = prop.getProperty("email");
		String passwordValue = prop.getProperty("passwd");
		ElementSelector ElementSelector = new ElementSelector(driver);
		SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);
		LogInPage LogInPage = new LogInPage(driver);
		log = LogManager.getLogger(CommonTest.class.getName());

		CommonTestStepMethods.login();
		Thread.sleep(2000);
		String expectedURL = "https://online.actitime.com/shvia/timetrack/enter.do";
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL);
		log.debug("User succesfully logged in");
	}
	


	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */
	@Test(priority = 2)
	// Verifying created customer name test
	public void creatingCustomerTest() throws InterruptedException, IOException {
//		CommonTestStepMethods.login();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
		driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),
				prop.getProperty("customerName") + formattedDateTime);
		WebElement searchedCustomerName = driver.findElement(By.xpath(TasksTabPage.firstCustomerName()));
		wait.until(ExpectedConditions.visibilityOf(searchedCustomerName));
		String newlyCreatedCustomer = searchedCustomerName.getText();
		Assert.assertEquals(prop.getProperty("customerName") + formattedDateTime, newlyCreatedCustomer);
		log.debug("Newly created customer name is verified");

	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */
	@Test(priority = 3)
	// creating a project and verifying it 
	public void creatingProjectTest() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		CommonTestStepMethods.login();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
		CommonTestStepMethods.creatingProject(formattedDateTime);
		// Verification
		Actions actions = new Actions(driver);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
		driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),
				prop.getProperty("customerName") + formattedDateTime);
		log.debug("Searched customer name");
		List<WebElement> elements = driver.findElements(By.xpath(TasksTabPage.customerNamesList()));
		for (WebElement element : elements) {
			String customerNameAfterSearching = element.getText();
			if (customerNameAfterSearching.equalsIgnoreCase(prop.getProperty("customerName") + formattedDateTime)) {
				log.debug("Found the customer name that is : " + customerNameAfterSearching);
				Assert.assertEquals(customerNameAfterSearching, prop.getProperty("customerName") + formattedDateTime);
				actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.viewCustomerProjects())));
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.viewCustomerProjects()))));
				ElementSelector.clickByxpath(TasksTabPage.viewCustomerProjects());
				log.debug("Clicked on customer view customer projects");
				String customerProjectName = driver.findElement(By.xpath(TasksTabPage.customerProjectName())).getText();
				ElementSelector.clickByxpath(TasksTabPage.customerProjectName());
				log.debug("Found the project that is : " + customerProjectName);
				Assert.assertEquals(customerProjectName, prop.getProperty("projectName") + formattedDateTime);
				log.debug("Assertion of project name");
				break;
			}
		}

	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */
	@Test(priority = 4)
	// Creating a task and verifying
	public void creatingTaskTest() throws InterruptedException, IOException {
//		CommonTestStepMethods.login();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.creatingProject(formattedDateTime);
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomerCreatingTask(formattedDateTime);
		CommonTestStepMethods.creatingTask(formattedDateTime);
		// verifying task list first
		List<WebElement> tasksNamesListAddedAddNewBtn = driver.findElements(By.xpath(TasksTabPage.tasksList()));
		for (WebElement tasksNames : tasksNamesListAddedAddNewBtn) {
			String tasksNameString = tasksNames.getText();
			if (tasksNameString.equalsIgnoreCase(prop.getProperty("taskName") + formattedDateTime))
				log.debug(tasksNameString + "task is added");
			else
				log.debug(prop.getProperty("taskName") + formattedDateTime + " Task is not added");
		}
	}
	
	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */
	@Test(priority = 5)
	// creating a task from add task button and verifying it
	public void creatingNewTaskFromTaskTabTest() throws IOException, InterruptedException {
//		CommonTestStepMethods.login();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer(); 
		CommonTestStepMethods.creatingProject(formattedDateTime);
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomerCreatingTask(formattedDateTime);
		CommonTestStepMethods.creatingTask(formattedDateTime);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String newSubTask = CommonTestStepMethods.creatingNewTaskFromAddTaskBtnTab();
		
		// Verifying tasks
		if(driver.findElement(By.xpath(TasksTabPage.viewCustomerProjects())).isDisplayed()) {
			ElementSelector.clickByxpath(TasksTabPage.viewCustomerProjects());
			System.out.println("Clicked on view customer projects");
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstProjectNameAfterAddingSubTask())));
		ElementSelector.clickByxpath(TasksTabPage.firstProjectNameAfterAddingSubTask());
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(TasksTabPage.tasksList()), 2));
		List<WebElement> tasksNamesList = driver.findElements(By.xpath(TasksTabPage.tasksList()));
		for (WebElement tasksName : tasksNamesList) {
			String tasksNameString = tasksName.getText().trim();
			if (tasksNameString.contentEquals(newSubTask)) {
				Assert.assertEquals(tasksNameString, newSubTask);
				System.out.println("Newly added sub task " + newSubTask);
				log.debug("'" + tasksNameString + "'" + " is the new task added");
			}
		}
	}

	
}
