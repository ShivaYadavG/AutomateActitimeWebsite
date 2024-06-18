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
import pageObjects.tasksListCheckTestObjects;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;
import stepsMethods.CommonTestStepMethods;

public class customerTest extends BaseActi {

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - 
	 * Delete and verify the whether customer is delted or not
	 */
	
	@Test(priority = 1)
	public void delete_VerifyTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		CommonTestStepMethods CommonTestStepMethods = new CommonTestStepMethods();
		tasksListCheckTestObjects tasksListCheckTestObjects = new tasksListCheckTestObjects(driver);
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		String DateTime = CommonTestStepMethods.creatingCustomer();
		CommonTestStepMethods.creatingProject(DateTime);
		CommonTestStepMethods.creatingTask(DateTime);
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(DateTime);
		driver.navigate().refresh();
		CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(DateTime);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.taskCheckbox())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.taskCheckbox());
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(tasksListCheckTestObjects.taskDeleteBtn())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.taskDeleteBtn());
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(tasksListCheckTestObjects.taskDeletePermanently())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.taskDeletePermanently());
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(tasksListCheckTestObjects.noTaskMessage())));
		boolean noTaskDisplayed = driver.findElement(By.xpath(tasksListCheckTestObjects.noTaskMessage())).isDisplayed();
		Assert.assertTrue(noTaskDisplayed);
	}

	
	
}
