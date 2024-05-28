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

public class tasksListCheckTest extends BaseActi {
	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */
	List<String> tasks = new ArrayList<>();

	@Test
	public void tasksListCheckTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		tasksListCheckTestObjects tasksListCheckTestObjects = new tasksListCheckTestObjects(driver);
		LogInPage LogInPage = new LogInPage(driver);
		ElementSelector ElementSelector = new ElementSelector(driver);
		SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);
		String emailValue = prop.getProperty("email");
		String passwordValue = prop.getProperty("passwd");

		// Log in
		SendKeysToElement.snedKeysToElementById(LogInPage.userName(), prop.getProperty("userName"));
		SendKeysToElement.sendKeysToElementByXpath(LogInPage.password(), prop.getProperty("password"));
		ElementSelector.clickById(LogInPage.logInButton());

		// opening Tasks tab
		TasksTabPage TasksTabPage = new TasksTabPage(driver);
		ElementSelector.clickById(TasksTabPage.tasksTab());
		String ViewTimeTrackPageTabURL = driver.getCurrentUrl();
		Assert.assertEquals(ViewTimeTrackPageTabURL, "https://online.actitime.com/relanto/tasks/tasklist.do");

		// clicking on Big bang company and getting all tasks list
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.BigBangCompanyCust())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.BigBangCompanyCust());
		HashMap<String, List<String>> BigBangCompanyHM = gettingTasksList("BigBangCompanyCust");

		// clicking on Flight operation project and getting all tasks list
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.FlightOperationProject())));
		WebElement flightOperationCustomer = driver
				.findElement(By.xpath(tasksListCheckTestObjects.FlightOperationProject()));
		js.executeScript("arguments[0].click()", flightOperationCustomer);
		HashMap<String, List<String>> FlightOperationProjectHM = gettingTasksList("FlightOperationProject");

		// clicking on Space ship building project and getting all tasks list
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(tasksListCheckTestObjects.SpaceShipBuildingProject())));
		WebElement spaceShipBuildin = driver
				.findElement(By.xpath(tasksListCheckTestObjects.SpaceShipBuildingProject()));
		js.executeScript("arguments[0].click()", spaceShipBuildin);
		HashMap<String, List<String>> ShipBuildingOperationHM = gettingTasksList("SpaceShipBuildingProject");

		// clicking on Space ship testing project and getting all tasks list
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.SpaceShipTestingProject())));
		WebElement spaceShipTesting = driver.findElement(By.xpath(tasksListCheckTestObjects.SpaceShipTestingProject()));
		js.executeScript("arguments[0].click()", spaceShipTesting);
		HashMap<String, List<String>> ShipTestingProjectHM = gettingTasksList("SpaceShipTestingProject");

		// calling methods to verify the whether the tasks are present in the Big bang
		areTasksPresentInBigBangCust(BigBangCompanyHM, FlightOperationProjectHM);
		areTasksPresentInBigBangCust(BigBangCompanyHM, ShipBuildingOperationHM);
		areTasksPresentInBigBangCust(BigBangCompanyHM, ShipTestingProjectHM);

		// Total number of tasks validation
		int TotalBigBangCompanyTasks = getTotalTasks(BigBangCompanyHM);
		int TotalTasksIn_FlightOperationProjectHM = getTotalTasks(FlightOperationProjectHM);
		int TotalTasksIn_ShipBuildingOperationHM = getTotalTasks(ShipBuildingOperationHM);
		int TotalTasksIn_ShipTestingProjectHM = getTotalTasks(ShipTestingProjectHM);

		int TotalTasksInDifferentStatuses = TotalTasksIn_FlightOperationProjectHM + TotalTasksIn_ShipBuildingOperationHM
				+ TotalTasksIn_ShipTestingProjectHM;
		Assert.assertEquals(TotalBigBangCompanyTasks, TotalTasksInDifferentStatuses);
		log.debug("Total number of tasks are verified");
	}

	public HashMap<String, List<String>> gettingTasksList(String project_CustomerName) throws InterruptedException {
		tasksListCheckTestObjects tasksListCheckTestObjects = new tasksListCheckTestObjects(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Actions actions = new Actions(driver);

		HashMap<String, List<String>> gettingTasksList = new HashMap<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='60%'");

		if (project_CustomerName.equalsIgnoreCase("BigBangCompanyCust")) {
			String[] projects = { "Flight operations", "Spaceship building", "Spaceship testing" };
			List<WebElement> minimizeMaximizeIcons = driver
					.findElements(By.xpath(tasksListCheckTestObjects.minimizeCustomer()));

			for (String project : projects) {
				List<String> tasks = new ArrayList<>(); // Create a new list for each customer

				// Find all tasks for the customer
				Thread.sleep(3000);
				List<WebElement> tasksOfCustomerList = driver
						.findElements(By.xpath(tasksListCheckTestObjects.tasksInCustomersRow()));
				for (WebElement taskOfCustomer : tasksOfCustomerList) {
					wait.until(ExpectedConditions.visibilityOf(taskOfCustomer));
					tasks.add(taskOfCustomer.getText());
				}

				// Add the tasks for the current project to the map
				gettingTasksList.put(project, tasks);

				// Minimize the project if necessary
				String customerName = "//*[text()='OPEN TASKS']//following::*[@class='projectName' and text()='"
						+ project + "']";
				WebElement minimize = driver.findElement(By.xpath(customerName));
				wait.until(ExpectedConditions.elementToBeClickable(minimize));
				js.executeScript("arguments[0].click();", minimize);
			}
		} 
		else {
			List<String> tasks = new ArrayList<>(); // Create a new list for the projects of customer

			// Find all tasks in the project of customer
			Thread.sleep(3000);
			List<WebElement> tasksOfCustomerList = driver
					.findElements(By.xpath(tasksListCheckTestObjects.tasksInCustomersRow()));
			for (WebElement taskOfCustomer : tasksOfCustomerList) {
				wait.until(ExpectedConditions.visibilityOf(taskOfCustomer));
				tasks.add(taskOfCustomer.getText());
			}

			// Add the tasks for the customer to the map
			gettingTasksList.put(project_CustomerName, tasks);
		}

		return gettingTasksList;
	}

	// Total number of tasks from a HashMap
	private int getTotalTasks(HashMap<String, List<String>> tasksHM) {
		int totalTasks = 0;
		for (List<String> tasks : tasksHM.values()) {
			totalTasks += tasks.size();
		}
		return totalTasks;
	}

	// Validations of tasks present in the Big Bang customer
	public void areTasksPresentInBigBangCust(HashMap<String, List<String>> BigBangCompanyHM,
			HashMap<String, List<String>> FlightOperationProjectHM) {
		for (Map.Entry<String, List<String>> flightEntry : FlightOperationProjectHM.entrySet()) {
			for (String flightTask : flightEntry.getValue()) {
				boolean taskFound = false;
				for (List<String> bigBangTasks : BigBangCompanyHM.values()) {
					if (bigBangTasks.contains(flightTask)) {
						taskFound = true;
						break;
					}
				}
				if (!taskFound) {
					System.out.println(flightTask + " is not present in the Big Bang company");
				}
			}
		}
	}
}
