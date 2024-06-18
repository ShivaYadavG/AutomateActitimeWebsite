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
import org.testng.annotations.BeforeSuite;
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
	 * Create a customer and verify 
	 */
	@Test(priority = 1)
	// Crate a customer and verify it 
	public void creatingCustomerTest() throws InterruptedException, IOException {
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		TasksTabPage TasksTabPage = new TasksTabPage(driver);
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
		String searchedCustomer = driver.findElement(By.xpath(
				"//div[@class='filteredContainer']//div[@class='itemsContainer']//div[contains(@class,'customerNode')]//div[@class='title']//div[@class='text']"))
				.getText();
		System.out.println("Searched customer = " + searchedCustomer);
		String customerName = prop.getProperty("customerName") + formattedDateTime;
		System.out.println("Actual customer = " + customerName);
		Assert.assertEquals(customerName, searchedCustomer);
	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * creating a project and verifying it
	 */
	@Test(priority = 2)
	public void creatingProjectTest() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
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
	 * Creating a task and verifying
	 */
	@Test(priority = 3)
	public void creatingTaskTest() throws InterruptedException, IOException {
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.creatingProject(formattedDateTime);
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomerFirstProject(formattedDateTime);
		CommonTestStepMethods.creatingTask(formattedDateTime);
		driver.navigate().refresh();
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
		// verifying task list first
		String cretedTask = driver.findElement(By.xpath(TasksTabPage.tasksList())).getText();
		Assert.assertEquals(prop.getProperty("taskName") + formattedDateTime, cretedTask);
	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 *  creating a task from add task button and verifying it
	 */
	@Test(priority = 4)
	public void creatingNewTaskFromTaskTabTest() throws IOException, InterruptedException {
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.creatingProject(formattedDateTime);
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomerFirstProject(formattedDateTime);
		CommonTestStepMethods.creatingTask(formattedDateTime);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		String newSubTask = CommonTestStepMethods.creatingNewTaskFromAddTaskBtnTab(formattedDateTime);
		driver.navigate().refresh();
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
		driver.navigate().refresh();
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomerFirstProject(formattedDateTime);
		List<WebElement> tasksNamesList = driver.findElements(By.xpath(TasksTabPage.tasksList()));
		for (WebElement tasksName : tasksNamesList) {
			String tasksNameString = tasksName.getText().trim();

			if (tasksNameString.contentEquals(newSubTask)) {
				System.out.println("'" + tasksNameString + "'" + " is the new task added");
				Assert.assertEquals(tasksNameString, newSubTask);
				log.debug("'" + tasksNameString + "'" + " is the new task added");
			}
		}
	}
	
	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime application
	 * Edit the created customer name and verify it
	 */
	@Test(priority = 5)
	public void editinCustomerNameTest() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		String newCustomerName = CommonTestStepMethods.editingCustomerName(formattedDateTime);
		 TasksTabPage TasksTabPage = new TasksTabPage(driver);
			
		// Verifying edited customer name
		driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(), newCustomerName);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstCustomerNameNotSelected())));
		ElementSelector.clickByxpath(TasksTabPage.firstCustomerNameNotSelected());
		String searchedCustomerAfterEdit = driver.findElement(By.xpath(TasksTabPage.firstCustomerNameNotSelected())).getText();
		Assert.assertEquals(newCustomerName, searchedCustomerAfterEdit);
		log.debug(newCustomerName + " is present in the customer list after edit");
	}
	
	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime application
	 * Deleting a created customer and verifying
	 */
	@Test(priority = 6)
	public void deletingCustomerTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		String newCustomerName = CommonTestStepMethods.editingCustomerName(formattedDateTime);
		CommonTestStepMethods.deletingEditedCustomer(newCustomerName);
		 TasksTabPage TasksTabPage = new TasksTabPage(driver);
		
		// verifying whether customer is deleted or not
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(), newCustomerName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TasksTabPage.noCustomerPresentLable())));
		boolean isnoCustomerPresentLableDisplayed = driver.findElement(By.xpath(TasksTabPage.noCustomerPresentLable())).isDisplayed();
		Assert.assertTrue(isnoCustomerPresentLableDisplayed);
		log.debug(newCustomerName + " is deleted");
	}
}
