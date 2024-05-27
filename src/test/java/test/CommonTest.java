package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageObjects.LogInPage;
import pageObjects.TasksTabPage;
import pageObjects.addNewTab;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;

public class CommonTest extends BaseActi {

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */
	@Test
	public void LoginActiTest() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		String emailValue = prop.getProperty("email");
		String passwordValue = prop.getProperty("passwd");
		ElementSelector ElementSelector = new ElementSelector(driver);
		SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);
		LogInPage LogInPage = new LogInPage(driver);
		log = LogManager.getLogger(CommonTest.class.getName());

		// Getting the current date and time
		Date currentDate = new Date();
		// Formatting the date and time
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = dateFormat.format(currentDate);

		// Log in
		SendKeysToElement.snedKeysToElementById(LogInPage.userName(), prop.getProperty("userName"));
		SendKeysToElement.sendKeysToElementByXpath(LogInPage.password(), prop.getProperty("password"));
		ElementSelector.clickById(LogInPage.logInButton());

		// opening Tasks tab
		TasksTabPage TasksTabPage = new TasksTabPage(driver);
		ElementSelector.clickById(TasksTabPage.tasksTab());
		String ViewTimeTrackPageTabURL = driver.getCurrentUrl();
		Assert.assertEquals(ViewTimeTrackPageTabURL, "https://online.actitime.com/relanto/tasks/tasklist.do");

	
		// creating new customer
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		ElementSelector.clickByxpath(TasksTabPage.newCustomerBtn());
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enterCustomerNameTextBox(),
				prop.getProperty("customerName") + formattedDateTime);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.createCustomer())));
		log.debug("Clicked on create customer button");
		Thread.sleep(3000);

		// Creating project
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))));
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		log.debug("Clicked on add new button again");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.newProject()))));
		ElementSelector.clickByxpath(TasksTabPage.newProject());
		log.debug("clicked on new project");
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enterProjectName(),
				prop.getProperty("projectName") + formattedDateTime);
		log.debug("Entered project name");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.createProject()))).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.createProject()))));
		ElementSelector.clickByxpath(TasksTabPage.createProject());
		log.debug("Clicked on create customer button");

		// creating new task
		actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))).perform(); //
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))));
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		log.debug("Clicked on add new button to create new task");
		ElementSelector.clickByxpath(TasksTabPage.createNewTasks());
		log.debug("Clicked on create new task");
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enteringTaskName(),
				prop.getProperty("taskName") + formattedDateTime);
		log.debug("Entered Task name");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.createTask()))));
		ElementSelector.clickByxpath(TasksTabPage.createTask());
		log.debug("Clicked on create task");

		// Verification
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
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

		// verifying task list first
		List<WebElement> tasksNamesListAddedAddNewBtn = driver.findElements(By.xpath(TasksTabPage.tasksList()));
		for (WebElement tasksNames : tasksNamesListAddedAddNewBtn) {
			String tasksNameString = tasksNames.getText();
			if (tasksNameString.equalsIgnoreCase(prop.getProperty("taskName") + formattedDateTime))
				log.debug(tasksNameString + "task is added");
			else
				log.debug(prop.getProperty("taskName") + formattedDateTime + " Task is not added");
		}

		// Adding new task from tasks tab
		String newSubTask = (prop.getProperty("newTaskName") + formattedDateTime);
		addNewTab addNewTab = new addNewTab(driver);
		ElementSelector.clickByxpath(addNewTab.addNewBtn());
		log.debug("Clicked on add new button ");
		SendKeysToElement.sendKeysToElementByXpath(addNewTab.addingNewTaskTextBox(), newSubTask);
		log.debug("Entered new task ");
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath(addNewTab.addNewBtnAfterEneteringTask()))));
		ElementSelector.clickByxpath(addNewTab.addNewBtnAfterEneteringTask());
		log.debug("Clicked on add new button after entering new task");

		// Verifying tasks
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(TasksTabPage.tasksList()), 2));
		List<WebElement> tasksNamesList = driver.findElements(By.xpath(TasksTabPage.tasksList()));
		int totalTasksAfterAddingSubTask = tasksNamesList.size();
		for (WebElement tasksName : tasksNamesList) {
			String tasksNameString = tasksName.getText().trim();
			if (tasksNameString.contentEquals(newSubTask)) {
				Assert.assertEquals(tasksNameString, newSubTask);
				log.debug("'" + tasksNameString + "'" + " is the new task added");
			}
		}
	}
}
