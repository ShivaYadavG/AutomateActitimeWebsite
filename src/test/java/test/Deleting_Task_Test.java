package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.TasksTabPage;
import pageObjects.tasksListCheckTestObjects;
import resources.BaseActi;
import resources.ElementSelector;
import stepsMethods.CommonTestStepMethods;

public class Deleting_Task_Test extends BaseActi {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	TasksTabPage TasksTabPage = new TasksTabPage(driver);

	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */

	@Test
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
