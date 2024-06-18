package test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

public class tasksListCheckTest extends BaseActi {
	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */
	List<String> tasks = new ArrayList<>();

	@Test(priority = 1)
	public void tasksListCheckTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		tasksListCheckTestObjects tasksListCheckTestObjects = new tasksListCheckTestObjects(driver);
		// opening Tasks tab
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		// clicking on Big Bang company and getting all tasks list
		HashMap<String, List<String>> BigBangCompanyHM = CommonTestStepMethods.tasksListBigBangCompany();
		// clicking on Flight Operation Project and getting all tasks list
		HashMap<String, List<String>> FlightOperationProjectHM = CommonTestStepMethods
				.tasksListFlightOperationProjectHM();
		// clicking on Ship Building Operation and getting all tasks list
		HashMap<String, List<String>> ShipBuildingOperationHM = CommonTestStepMethods
				.tasksListSpaceShipBuildingProject();
		// clicking on Ship Testing Project and getting all tasks list
		HashMap<String, List<String>> ShipTestingProjectHM = CommonTestStepMethods.tasksListSpaceShipTestingProject();
		// calling methods to verify the whether the tasks are present in the Big bang
		CommonTestStepMethods.areTasksPresentInBigBangCust(BigBangCompanyHM, FlightOperationProjectHM);
		CommonTestStepMethods.areTasksPresentInBigBangCust(BigBangCompanyHM, ShipBuildingOperationHM);
		CommonTestStepMethods.areTasksPresentInBigBangCust(BigBangCompanyHM, ShipTestingProjectHM);

	}

	@Test(priority = 2)
	public void totalTasksTest() throws InterruptedException {
		tasksListCheckTestObjects tasksListCheckTestObjects = new tasksListCheckTestObjects(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.BigBangCompanyCust())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.BigBangCompanyCust());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='60%'");

		String spaceShipXpath = "//*[text()='OPEN TASKS']//following::*[@class='projectName' and text()='Spaceship testing']";
		WebElement Spaceship = driver.findElement(By.xpath(spaceShipXpath));
		if (Spaceship.isDisplayed()) {
			String[] projects = { "Spaceship testing", "Spaceship building", "Flight operations" };
			for (String project : projects) {
				String customerName = "//*[text()='OPEN TASKS']//following::*[@class='projectName' and text()='"
						+ project + "']";
				WebElement minimize = driver.findElement(By.xpath(customerName));
				wait.until(ExpectedConditions.elementToBeClickable(minimize));
				js.executeScript("arguments[0].click();", minimize);
			}
			/*
			 * try{ driver.findElement(By.xpath(tasksListCheckTestObjects.FirstTask())); }
			 * catch(Exception e) { String[] projects = {"Spaceship testing",
			 * "Spaceship building", "Flight operations"}; for(String project : projects) {
			 * String customerName =
			 * "//*[text()='OPEN TASKS']//following::*[@class='projectName' and text()='" +
			 * project + "']"; WebElement minimize =
			 * driver.findElement(By.xpath(customerName));
			 * wait.until(ExpectedConditions.elementToBeClickable(minimize));
			 * js.executeScript("arguments[0].click();", minimize); } }
			 */

			driver.navigate().refresh();
			ElementSelector.clickByxpath(tasksListCheckTestObjects.BigBangCompanyCust());

			// clicking on Big Bang company and getting all tasks list
			HashMap<String, List<String>> BigBangCompanyHM = CommonTestStepMethods.tasksListBigBangCompany();

			// clicking on Flight Operation Projecy and getting all tasks list
//		@SuppressWarnings("unchecked")
			HashMap<String, List<String>> FlightOperationProjectHM = CommonTestStepMethods
					.tasksListFlightOperationProjectHM();

			// clicking on Ship Building Operation and getting all tasks list
//		@SuppressWarnings("unchecked")
			HashMap<String, List<String>> ShipBuildingOperationHM = CommonTestStepMethods
					.tasksListSpaceShipBuildingProject();

			// clicking on Ship Testing Project and getting all tasks list
			HashMap<String, List<String>> ShipTestingProjectHM = CommonTestStepMethods
					.tasksListSpaceShipTestingProject();
			// Total number of tasks validation
			int TotalBigBangCompanyTasks = CommonTestStepMethods.getTotalTasks(BigBangCompanyHM);
			int TotalTasksIn_FlightOperationProjectHM = CommonTestStepMethods.getTotalTasks(FlightOperationProjectHM);
			int TotalTasksIn_ShipBuildingOperationHM = CommonTestStepMethods.getTotalTasks(ShipBuildingOperationHM);
			int TotalTasksIn_ShipTestingProjectHM = CommonTestStepMethods.getTotalTasks(ShipTestingProjectHM);

			int TotalTasksInDifferentStatuses = TotalTasksIn_FlightOperationProjectHM
					+ TotalTasksIn_ShipBuildingOperationHM + TotalTasksIn_ShipTestingProjectHM;
			Assert.assertEquals(TotalBigBangCompanyTasks, TotalTasksInDifferentStatuses);
			log.debug("Total number of tasks are verified");
		}
	}
}
