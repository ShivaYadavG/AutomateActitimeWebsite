package test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

public class customerTest extends BaseActi {
	// Getting the current date and time
	Date currentDate = new Date();
	// Formatting the date and time
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String formattedDateTime = dateFormat.format(currentDate);
	TasksTabPage TasksTabPage = new TasksTabPage(driver);
	LogInPage LogInPage = new LogInPage(driver);

	public void login() {

		// Log in
		SendKeysToElement.snedKeysToElementById(LogInPage.userName(), prop.getProperty("userName"));
		SendKeysToElement.sendKeysToElementByXpath(LogInPage.password(), prop.getProperty("password"));
		ElementSelector.clickById(LogInPage.logInButton());
	}
	
	public void openingTask() {
		// opening Tasks tab
				ElementSelector.clickById(TasksTabPage.tasksTab());
				String ViewTimeTrackPageTabURL = driver.getCurrentUrl();
				Assert.assertEquals(ViewTimeTrackPageTabURL, "https://online.actitime.com/relanto/tasks/tasklist.do");
	}

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */

	@Test
	public void customerCreationDeletion() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String emailValue = prop.getProperty("email");
		String passwordValue = prop.getProperty("passwd");
		ElementSelector ElementSelector = new ElementSelector(driver);
		SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);
		LogInPage LogInPage = new LogInPage(driver);
		log = LogManager.getLogger(CommonTest.class.getName());
		
		login();
		openingTask();

		// creating new customer
		String customerName = prop.getProperty("customerName") + formattedDateTime;
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		ElementSelector.clickByxpath(TasksTabPage.newCustomerBtn());
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enterCustomerNameTextBox(), customerName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.createCustomer())));
		log.debug("Clicked on create customer button");

		// Verifying customer name
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(), customerName);
		ElementSelector.clickByxpath(TasksTabPage.firstCustomerNameAfterSearch());
		String searchedCustomer = driver.findElement(By.xpath(TasksTabPage.firstCustomerNameAfterSearch())).getText();
		Assert.assertEquals(customerName, searchedCustomer);
		log.debug(customerName + " is present in the customer list");

		// Editing customer name
		String newCustomerName = prop.getProperty("editedCustomeName") + formattedDateTime;
		ElementSelector.clickByxpath(TasksTabPage.editIcon());
		log.debug("Clicked on view customer edit icon");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.customerNameLabeToEdit()))).click().perform();
		ElementSelector.clickByxpath(TasksTabPage.customerNameLabeToEdit());
		log.debug("Clicked on old customer name lable to edit");
		driver.findElement(By.xpath(TasksTabPage.customerNameInputBox())).clear();

		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.customerNameInputBox(), newCustomerName);
		ElementSelector.clickByxpath(TasksTabPage.closeCustomerEditTab());
		log.debug("Edited customer name");
		driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(), newCustomerName);
		ElementSelector.clickByxpath(TasksTabPage.firstCustomerNameAfterSearch());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstCustomerNameAfterSearch())));
		String searchedCustomerAfterEdit = driver.findElement(By.xpath(TasksTabPage.firstCustomerNameAfterSearch()))
				.getText();
		Assert.assertEquals(newCustomerName, searchedCustomerAfterEdit);
		log.debug(newCustomerName + " is present in the customer list after edit");

		// deleting edited customer
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.editIcon()))));
		ElementSelector.clickByxpath(TasksTabPage.editIcon());
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.actionDropDown()))));
		ElementSelector.clickByxpath(TasksTabPage.actionDropDown());
		ElementSelector.clickByxpath(TasksTabPage.delteBtn());
		ElementSelector.clickByxpath(TasksTabPage.deletPermanentlyBtn());
		log.debug(" customer name : " + newCustomerName + " is deleted");

		// verifying whether customer is deleted or not
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TasksTabPage.noCustomerPresentLable())));
		boolean isnoCustomerPresentLableDisplayed = driver.findElement(By.xpath(TasksTabPage.noCustomerPresentLable()))
				.isDisplayed();
		Assert.assertTrue(isnoCustomerPresentLableDisplayed);
		log.debug(newCustomerName + " is deleted");

	}
}
