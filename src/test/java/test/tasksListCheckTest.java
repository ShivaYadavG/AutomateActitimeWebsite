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
		Thread.sleep(5000);
		HashMap<String, List<String>> BigBangCompanyHM = gettingTasksList("BigBangCompanyCust");

		// clicking on Flight operation project and getting all tasks list
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.FlightOperationProject())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.FlightOperationProject());
		Thread.sleep(3000);
		HashMap<String, List<String>> FlightOperationProjectHM = gettingTasksList("FlightOperationProject");

		// clicking on Space ship building project and getting all tasks list
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(tasksListCheckTestObjects.SpaceShipBuildingProject())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.SpaceShipBuildingProject());
		Thread.sleep(3000);
		HashMap<String, List<String>> ShipBuildingOperationHM = gettingTasksList("SpaceShipBuildingProject");

		// clicking on Space ship testing project and getting all tasks list
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.SpaceShipTestingProject())));
		ElementSelector.clickByxpath(tasksListCheckTestObjects.SpaceShipTestingProject());
		Thread.sleep(3000);
		HashMap<String, List<String>> ShipTestingProjectHM = gettingTasksList("SpaceShipTestingProject");

		// Calllin mehtod to print all the tasks in big bang compay
		printTasks(BigBangCompanyHM);

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

	// Getting all tasks in Big bang company customer and its projects
	public HashMap gettingTasksList(String project_CustomerName) throws InterruptedException {
		tasksListCheckTestObjects tasksListCheckTestObjects = new tasksListCheckTestObjects(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Actions actions = new Actions(driver);
		HashMap<String, List<String>> gettingTasksList = new HashMap<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='40%'");
		
		String StatusXpath = "//*[text()[contains(.,'OPEN TASKS')]]//following::*[@class='name gradientEllipsis']";
		
		WebElement flightOperation = driver.findElement(By.xpath("//*[text()='OPEN TASKS']//following::*[@class='projectName' and text()='Flight operations']/..//div[@class='icon']"));
		js.executeScript("arguments[0].click();", flightOperation);
		Thread.sleep(1000);
		List<WebElement> list =driver.findElements(By.xpath(StatusXpath));
		for(WebElement ele : list) {
			System.out.println(ele.getText());
		}
		WebElement spaceBuilding = driver.findElement(By.xpath("//*[text()='OPEN TASKS']//following::*[@class='projectName' and text()='Spaceship building']/..//div[@class='icon']"));
		js.executeScript("arguments[0].click();", spaceBuilding);
		Thread.sleep(1000);
		List<WebElement> list2 =driver.findElements(By.xpath(StatusXpath));
		for(WebElement ele : list2) {
			System.out.println(ele.getText());
		}
		WebElement spaceTesting = driver.findElement(By.xpath("//*[text()='OPEN TASKS']//following::*[@class='projectName' and text()='Spaceship testing']/..//div[@class='icon']"));
		js.executeScript("arguments[0].click();", spaceTesting);
		Thread.sleep(1000);
		List<WebElement> list3 =driver.findElements(By.xpath(StatusXpath));
		for(WebElement ele : list3) {
			System.out.println(ele.getText());
		}
	
		List<WebElement> minimizeMaximizeIcons = driver.findElements(By.xpath(tasksListCheckTestObjects.minimizeCustomer()));
		List<WebElement> tasksOfCustomerList = driver.findElements(By.xpath(tasksListCheckTestObjects.tasksInCustomersRow()));
		for (WebElement customerMaxMinIcon : minimizeMaximizeIcons) {
			for (WebElement taskOfCutomer : tasksOfCustomerList) {
				wait.until(ExpectedConditions.visibilityOf(taskOfCutomer));
				tasks.add(taskOfCutomer.getText());
				System.out.println(taskOfCutomer.getText());
			}
			customerMaxMinIcon.click();
		}
		
		
		// Add tasksList to the hash map
		gettingTasksList.put(project_CustomerName, tasks);
		return gettingTasksList;
	}

	// Adding tasks in tasks list
	public void addingTasksInHashmapList(String headerXpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> inNewTabTasksList = driver.findElements(By.xpath(headerXpath));
		for (WebElement inNewTabTask : inNewTabTasksList) {
			wait.until(ExpectedConditions.visibilityOf(inNewTabTask));
			tasks.add(inNewTabTask.getText());
		}

	}

	// Total number of tasks from a HashMap
	private int getTotalTasks(HashMap<String, List<String>> tasksHM) {
		int totalTasks = 0;
		for (List<String> tasks : tasksHM.values()) {
			totalTasks += tasks.size();
		}
		return totalTasks;
	}

	// printing big bang company tasks for manual verification
	public void printTasks(HashMap<String, List<String>> BigBangCompanyHM) {
		for (Map.Entry<String, List<String>> tasks : BigBangCompanyHM.entrySet()) {
			for (List<String> task : BigBangCompanyHM.values()) {
				System.out.println("Big bang company tasks : " + task);
			}
		}
	}

	// Validations of tasks present in the Big Bang customer
	public void areTasksPresentInBigBangCust(HashMap<String, List<String>> BigBangCompanyHM,
			HashMap<String, List<String>> FlightOperationProjectHM) {
		for (Map.Entry<String, List<String>> flightEntry : FlightOperationProjectHM.entrySet()) {
			for (String flightTask : flightEntry.getValue()) {
				boolean taskFound = false;
				for (List<String> bigBangTasks : BigBangCompanyHM.values()) {
					if (bigBangTasks.contains(flightTask)) {
						System.out.println(flightTask + " is present");
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
