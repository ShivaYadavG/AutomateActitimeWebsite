package stepsMethods;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.LogInPage;
import pageObjects.TasksTabPage;
import pageObjects.tasksListCheckTestObjects;
import pageObjects.reportPageObjects;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;
import test.CommonTest;

public class CommonTestStepMethods extends BaseActi {

	static TasksTabPage TasksTabPage = new TasksTabPage(driver);
	static tasksListCheckTestObjects tasksListCheckTestObjects = new tasksListCheckTestObjects(driver);
	static JavascriptExecutor js = (JavascriptExecutor) driver;
	static reportPageObjects reportPageObjects = new reportPageObjects(driver);

	public static String dateTime() {
		// Getting the current date and time
		Date currentDate = new Date();
		// Formatting the date and time
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = dateFormat.format(currentDate);
		return formattedDateTime;
	}

	// loging in
	public static void login() throws InterruptedException {
		String emailValue = prop.getProperty("email");
		String passwordValue = prop.getProperty("passwd");
		ElementSelector ElementSelector = new ElementSelector(driver);
		SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);
		LogInPage LogInPage = new LogInPage(driver);
		log = LogManager.getLogger(CommonTest.class.getName());
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Log in
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LogInPage.userName())));
		driver.findElement(By.id(LogInPage.userName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(LogInPage.userName(), prop.getProperty("userName"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LogInPage.password())));
		driver.findElement(By.xpath(LogInPage.password())).clear();
		SendKeysToElement.sendKeysToElementByXpath(LogInPage.password(), prop.getProperty("password"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id(LogInPage.logInButton())));
		ElementSelector.clickById(LogInPage.logInButton());
	}

	// Opening task tab from nav bar
	public static void openigTask() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.tasksTab())));
			ElementSelector.clickByxpath(TasksTabPage.tasksTab());
		} catch (Exception e) {
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.tasksTab())));
		}
	}

	//Creating customer 
	public static String creatingCustomer() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String formattedDateTime = dateTime();
		// creating new customer
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.addNewBtn())));
		try{
			ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		}
		catch(Exception e) {
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.addNewBtn())));
		}
		ElementSelector.clickByxpath(TasksTabPage.newCustomerBtn());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.enterCustomerNameTextBox())));
		driver.findElement(By.xpath(TasksTabPage.enterCustomerNameTextBox())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enterCustomerNameTextBox(),
				prop.getProperty("customerName") + formattedDateTime);
		System.out.println("Created customer name " + prop.getProperty("customerName") + formattedDateTime);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.createCustomer())));
		log.debug("Clicked on create customer button");
		return formattedDateTime;

	}

	// Creating project
	public static void creatingProject(String dateTime) throws InterruptedException {
		String formattedDateTime = dateTime;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))));
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		log.debug("Clicked on add new button again");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.newProject()))));
		ElementSelector.clickByxpath(TasksTabPage.newProject());
		log.debug("clicked on new project");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(TasksTabPage.enterProjectName()))));
		driver.findElement(By.xpath(TasksTabPage.enterProjectName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enterProjectName(),
				prop.getProperty("projectName") + formattedDateTime);
		log.debug("Entered project name");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.createProject()))).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.createProject()))));
		ElementSelector.clickByxpath(TasksTabPage.createProject());
		log.debug("Clicked on create customer button");
	}

	// Creting task 
	public static void creatingTask(String dateTime) {
		String formattedDateTime = dateTime;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// creating new task
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))).perform(); //
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))));
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		log.debug("Clicked on add new button to create new task");
		ElementSelector.clickByxpath(TasksTabPage.createNewTasks());
		log.debug("Clicked on create new task");
		try {
			driver.findElement(By.xpath(TasksTabPage.enteringTaskName())).clear();
		} catch (StaleElementReferenceException e) {
			driver.findElement(By.xpath(TasksTabPage.enteringTaskName())).clear();
		}
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enteringTaskName(),
				prop.getProperty("taskName") + formattedDateTime);
		System.out.println("Created task : " + prop.getProperty("taskName") + formattedDateTime);
		log.debug("Entered Task name");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.createTask()))));
		ElementSelector.clickByxpath(TasksTabPage.createTask());

	}

	// Adding new task from tasks tab
	public static String creatingNewTaskFromAddTaskBtnTab(String dateTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		String formattedDateTime = dateTime;
		String newSubTask = (prop.getProperty("newTaskName") + formattedDateTime);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.addNewTaskBtn())));
			ElementSelector.clickByxpath(TasksTabPage.addNewTaskBtn());
		} catch (ElementClickInterceptedException e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.addNewTaskBtn())));
			ElementSelector.clickByxpath(TasksTabPage.addNewTaskBtn());
		}
		log.debug("Clicked on add new button ");
		driver.findElement(By.xpath(TasksTabPage.addingNewTaskTextBox())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.addingNewTaskTextBox(), newSubTask);
		log.debug("Entered new task ");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.addNewBtnAfterEneteringTask())));
		try {
			ElementSelector.clickByxpath(TasksTabPage.addNewBtnAfterEneteringTask());
		} catch (Exception e) {
			WebElement addTaskBtn = driver.findElement(By.xpath(TasksTabPage.addNewBtnAfterEneteringTask()));
			wait.until(ExpectedConditions.elementToBeClickable(addTaskBtn));
			js.executeScript("arguments[0].click();", addTaskBtn);
		}
		log.debug("Clicked on add new button after entering new task");
		System.out.println(newSubTask);
		return newSubTask;
	}

	// Searching and clicking on the newly created customer
	public static void serachingClickingNewlyCreatedCustomer(String formattedDateTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
			driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		} catch (Exception e) {
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
			driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		}

		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),
				prop.getProperty("customerName") + formattedDateTime);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstCustomerName())));
		try {
			ElementSelector.clickByxpath(TasksTabPage.firstCustomerName());
		} catch (Exception ElementClickInterceptedException) {
			driver.findElement(By.xpath(TasksTabPage.firstCustomerName())).click();
		}

	}

	// Searching and clicking on the newly created project of customer
	public static void serachingClickingNewlyCreatedCustomerFirstProject(String formattedDateTime)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
		driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),
				prop.getProperty("customerName") + formattedDateTime);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstCustomerName())));
		try {
			ElementSelector.clickByxpath(TasksTabPage.firstCustomerName());
		} catch (StaleElementReferenceException e) {
			ElementSelector.clickByxpath(TasksTabPage.firstCustomerName());
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.viewCustomerProjects())));
		ElementSelector.clickByxpath(TasksTabPage.viewCustomerProjects());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstProjectName())));
		ElementSelector.clickByxpath(TasksTabPage.firstProjectName());
	}

	// Editing customer name
	public static String editingCustomerName(String dateTime) throws InterruptedException {
		// Editing customer name
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String formattedDateTime = dateTime;
		String newCustomerName = prop.getProperty("editedCustomeName") + formattedDateTime;
		try {
			ElementSelector.clickByxpath(TasksTabPage.editIcon());
		} catch (Exception e) {
			driver.findElement(By.xpath(TasksTabPage.editIcon())).click();
		}
		log.debug("Clicked on view customer edit icon");
		Actions actions = new Actions(driver);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.customerNameLabeToEdit())));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement customerNameLabeToEdit = driver.findElement(By.xpath(TasksTabPage.customerNameLabeToEdit()));
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", customerNameLabeToEdit);
		log.debug("Clicked on old customer name lable to edit");
		WebElement customerNameInputBox = driver.findElement(By.xpath(TasksTabPage.customerNameInputBox()));
		customerNameInputBox.clear(); 
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.customerNameInputBox(), newCustomerName);
		ElementSelector.clickByxpath(TasksTabPage.closeCustomerEditTab());
		log.debug("Edited customer name");
		return newCustomerName;

	}
	
	// deleting edited customer
	public static void deletingEditedCustomer(String newCustomerName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.editIcon()))));
		try {
			ElementSelector.clickByxpath(TasksTabPage.editIcon());
		} catch (Exception e) {
			ElementSelector.clickByxpath(TasksTabPage.editIcon());
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.actionDropDown())));
		WebElement adctionDropDown = driver.findElement(By.xpath(TasksTabPage.actionDropDown()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", adctionDropDown);
		WebElement deleteBtn = driver.findElement(By.xpath(TasksTabPage.delteBtn()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
		try {
			ElementSelector.clickByxpath(TasksTabPage.deletPermanentlyBtn());
		} catch (Exception e) {
			driver.findElement(By.xpath(TasksTabPage.deletPermanentlyBtn())).click();
		}
		log.debug(" customer name : " + newCustomerName + " is deleted");

	}

	// Validations of tasks present in the Big Bang customer
	public static void areTasksPresentInBigBangCust(HashMap<String, List<String>> BigBangCompanyHM,
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

	// Total number of tasks from a HashMap
	public static int getTotalTasks(HashMap<String, List<String>> tasksHM) {
		int totalTasks = 0;
		for (List<String> tasks : tasksHM.values()) {
			totalTasks += tasks.size();
		}
		return totalTasks;
	}

	// Getting tasks list and storing it in hashmpa-list collection
	public static HashMap<String, List<String>> gettingTasksList(String project_CustomerName)
			throws InterruptedException {
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
				Thread.sleep(1000);
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
		} else {
			List<String> tasks = new ArrayList<>(); // Create a new list for the projects of customer
			// Find all tasks in the project of customer

			Thread.sleep(1000);
			List<WebElement> tasksOfCustomerList = driver
					.findElements(By.xpath(tasksListCheckTestObjects.tasksInCustomersRow()));
			for (WebElement taskOfCustomer : tasksOfCustomerList) {
			
				try{
					wait.until(ExpectedConditions.visibilityOf(taskOfCustomer));
				tasks.add(taskOfCustomer.getText());
				}
				catch(Exception e) {
					wait.until(ExpectedConditions.visibilityOf(taskOfCustomer));
					tasks.add(taskOfCustomer.getText());	
				}
			}

			// Add the tasks for the customer to the map
			gettingTasksList.put(project_CustomerName, tasks);
		}

		return gettingTasksList;
	}

	// clicking on Big bang company and getting all tasks list
	public static HashMap tasksListBigBangCompany() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.BigBangCompanyCust())));
		try {
			ElementSelector.clickByxpath(tasksListCheckTestObjects.BigBangCompanyCust());
		} catch (Exception e) {
			driver.findElement(By.xpath(tasksListCheckTestObjects.BigBangCompanyCust())).click();
		}
		HashMap<String, List<String>> BigBangCompanyHM = CommonTestStepMethods.gettingTasksList("BigBangCompanyCust");
		return BigBangCompanyHM;
	}

	// clicking on Flight operation project and getting all tasks list
	public static HashMap tasksListFlightOperationProjectHM() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.FlightOperationProject())));
		WebElement flightOperationCustomer = driver
				.findElement(By.xpath(tasksListCheckTestObjects.FlightOperationProject()));
		js.executeScript("arguments[0].click()", flightOperationCustomer);
		HashMap<String, List<String>> FlightOperationProjectHM = CommonTestStepMethods
				.gettingTasksList("FlightOperationProject");
		return FlightOperationProjectHM;
	}

	public static HashMap tasksListSpaceShipBuildingProject() throws InterruptedException {
		// clicking on Space ship building project and getting all tasks list
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(tasksListCheckTestObjects.SpaceShipBuildingProject())));
		WebElement spaceShipBuildin = driver
				.findElement(By.xpath(tasksListCheckTestObjects.SpaceShipBuildingProject()));
		js.executeScript("arguments[0].click()", spaceShipBuildin);
		HashMap<String, List<String>> ShipBuildingOperationHM = CommonTestStepMethods
				.gettingTasksList("SpaceShipBuildingProject");
		return ShipBuildingOperationHM;
	}

	// clicking on Space ship testing project and getting all tasks list
	public static HashMap tasksListSpaceShipTestingProject() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(tasksListCheckTestObjects.SpaceShipTestingProject())));
		WebElement spaceShipTesting = driver.findElement(By.xpath(tasksListCheckTestObjects.SpaceShipTestingProject()));
		js.executeScript("arguments[0].click()", spaceShipTesting);
		HashMap<String, List<String>> ShipTestingProjectHM = CommonTestStepMethods
				.gettingTasksList("SpaceShipTestingProject");
		return ShipTestingProjectHM;
	}

	// Clicking on Reports tab from nav bar
	public static void clickingOnReportsTab() {
		ElementSelector.clickByxpath(reportPageObjects.reprtsTabXpath());		
	}
}