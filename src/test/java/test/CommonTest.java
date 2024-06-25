package test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.TasksTabPage;
import pageObjects.tasksListCheckTestObjects;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;
import stepsMethods.CommonTestStepMethods;

public class CommonTest extends BaseActi {

	TasksTabPage TasksTabPage = new TasksTabPage(driver);

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * Create a customer and verify
	 */
	@Test(priority = 1)
	// Crate a customer and verify it
	public void creatingCustomerTest() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		TasksTabPage TasksTabPage = new TasksTabPage(driver);
		// Verifying created customer
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
		List<WebElement> ListOfSearchedCustomer = driver
				.findElements(By.xpath(TasksTabPage.ListOfCustomersAfterSearchXpath()));
		for (WebElement SearchedCustomer : ListOfSearchedCustomer) {
			System.out.println(SearchedCustomer.getText());
			String SearchedCustomerName = SearchedCustomer.getText();
			if (SearchedCustomerName.equalsIgnoreCase(prop.getProperty("customerName") + formattedDateTime)) {
				System.out.println("Searched customer is present and verified " + SearchedCustomer.getText());
			} else {
				throw new Exception("Customer is not found");
			}
		}
	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application Edit the created customer name and verify it
	 */
	@Test(priority = 2)
	public void editinCustomerNameTest() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		driver.navigate().refresh();
		String newCustomerName = CommonTestStepMethods.editingCustomerName(formattedDateTime);
		TasksTabPage TasksTabPage = new TasksTabPage(driver);
		
		// Verifying created customer
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
				try {
					verifyingCreatedCustomer(formattedDateTime);
				}
				catch(Exception e) {
					verifyingCreatedCustomer(formattedDateTime);
				}
	}
	public void verifyingCreatedCustomer(String formattedDateTime) throws Exception {
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
		CommonTestStepMethods.serachingClickingEditedCreatedCustomer("lenova"+formattedDateTime);
		List<WebElement> ListOfSearchedCustomer = driver
				.findElements(By.xpath(TasksTabPage.ListOfCustomersAfterSearchXpath()));
		for (WebElement SearchedCustomer : ListOfSearchedCustomer) {
			System.out.println(SearchedCustomer.getText());
			String SearchedCustomerName = SearchedCustomer.getText();
			if (SearchedCustomerName.equalsIgnoreCase("lenova"+ formattedDateTime)) {
				System.out.println("Searched customer is present and verified " + SearchedCustomer.getText());
			} else {
				throw new Exception("Customer is not found");
			}
		}
		
	}
	
	@Test(priority = 3)
	public void creatingProjectTest() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
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
				String createdCustomer = prop.getProperty("customerName") + formattedDateTime;
				Assert.assertEquals(customerNameAfterSearching, createdCustomer);
				actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.viewCustomerProjects(createdCustomer))));
				ElementSelector.clickByxpath(TasksTabPage.viewCustomerProjects(createdCustomer));
				log.debug("Clicked on customer view customer projects");
				String customerProjectName = driver
						.findElement(By.xpath(TasksTabPage.customerProjectName(createdCustomer))).getText();
				ElementSelector.clickByxpath(TasksTabPage.customerProjectName(createdCustomer));
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
	@Test(priority = 4)
	public void creatingTaskTest() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.creatingProject(formattedDateTime);
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomerFirstProject(formattedDateTime);
		CommonTestStepMethods.creatingTask(formattedDateTime);
		String createdCustomer = prop.getProperty("customerName") + formattedDateTime;
		for (; true;) {
			try {
				CommonTestStepMethods.refreshClickOnTasksTab(driver);
				CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
				ElementSelector.clickByxpath(TasksTabPage.viewCustomerProjects(createdCustomer));
				ElementSelector.clickByxpath(TasksTabPage.firstProjectName());
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TasksTabPage.tasksList())));
				String cretedTask = driver.findElement(By.xpath(TasksTabPage.tasksList())).getText();
				Assert.assertEquals(prop.getProperty("taskName") + formattedDateTime, cretedTask);
				break;
			} catch (Exception e) {

			}

		}
	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * creating a task from add task button and verifying it
	 */
	@Test(priority = 5)
	public void creatingNewTaskFromTaskTabTest() throws IOException, InterruptedException {
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.creatingProject(formattedDateTime);
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomerFirstProject(formattedDateTime);
		CommonTestStepMethods.creatingTask(formattedDateTime);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		String newSubTask = CommonTestStepMethods.creatingNewTaskFromAddTaskBtnTab(formattedDateTime);
		// Verifying created task
		for (int i = 0; i < 5; i++) {
			try {
				CommonTestStepMethods.refreshClickOnTasksTab(driver);
				CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
				wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(TasksTabPage.tasksList()), 2));
				List<WebElement> tasksNamesList = driver.findElements(By.xpath(TasksTabPage.tasksList()));
				for (WebElement tasksName : tasksNamesList) {
					String tasksNameString = tasksName.getText().trim();
					if (tasksNameString.contentEquals(newSubTask)) {
						System.out.println("'" + tasksNameString + "'" + " is the new task added");
						Assert.assertEquals(tasksNameString, newSubTask);
						log.debug("'" + tasksNameString + "'" + " is the new task added");
					}
				}
				break;
			} catch (Exception e) {

			}
		}

	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application Deleting a created customer and verifying
	 */
	@Test(priority = 6)
	public void deletingCustomerTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
		String formattedDateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
		CommonTestStepMethods.deletingEditedCustomer();
		TasksTabPage TasksTabPage = new TasksTabPage(driver);
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
		// verifying whether customer is deleted or not
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),
				prop.getProperty("customerName") + formattedDateTime);
		driver.navigate().refresh();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),
				prop.getProperty("customerName") + formattedDateTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TasksTabPage.noCustomerPresentLable())));
		boolean isnoCustomerPresentLableDisplayed = driver.findElement(By.xpath(TasksTabPage.noCustomerPresentLable()))
				.isDisplayed();
		Assert.assertTrue(isnoCustomerPresentLableDisplayed);
	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */

	@Test(priority = 7)
	public void DeletingTaskTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		CommonTestStepMethods CommonTestStepMethods = new CommonTestStepMethods();
		tasksListCheckTestObjects tasksListCheckTestObjects = new tasksListCheckTestObjects(driver);
		CommonTestStepMethods.refreshClickOnTasksTab(driver);
		String DateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.creatingProject(DateTime);
		CommonTestStepMethods.creatingTask(DateTime);

		for (int i = 0; i < 4; i++) {
			try {
				try {
					driver.navigate().refresh();
					CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(DateTime);
					ElementSelector.clickByxpath(tasksListCheckTestObjects.taskCheckbox());
					break;
				} catch (Exception e) {
					driver.navigate().refresh();
					CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(DateTime);
					WebElement taskCheckbox = driver.findElement(By.xpath(tasksListCheckTestObjects.taskCheckbox()));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", taskCheckbox);
					break;
				}
			} catch (Exception e) {
				log.error("Exception occurred in editing customer name on attempt " + (i + 1), e);
			}
		}
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(tasksListCheckTestObjects.taskDeleteBtn())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.taskDeleteBtn());
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(tasksListCheckTestObjects.taskDeletePermanently())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.taskDeletePermanently());
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(tasksListCheckTestObjects.noTaskMessage())));
		boolean noTaskDisplayed = driver.findElement(By.xpath(tasksListCheckTestObjects.noTaskMessage())).isDisplayed();
		Assert.assertTrue(noTaskDisplayed);

	}
}
