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
import org.openqa.selenium.Keys;
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
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
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
		ElementSelector.clickById(LogInPage.logInButton());
	}

	// Opening task tab from nav bar
	public static void openigTask() {
		try {
			ElementSelector.clickByxpath(TasksTabPage.tasksTab());
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.tasksTab())));
		}
	}

	// Creating customer
	public static String creatingCustomer() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String formattedDateTime = dateTime();
		// creating new customer
		try {
			ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
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
	public static void creatingProject(String formattedDateTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		log.debug("Clicked on add new button again");
		ElementSelector.clickByxpath(TasksTabPage.newProject());
		log.debug("clicked on new project");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(TasksTabPage.enterProjectName()))));
		driver.findElement(By.xpath(TasksTabPage.enterProjectName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enterProjectName(),
				prop.getProperty("projectName") + formattedDateTime);
		log.debug("Entered project name");
		Actions actions = new Actions(driver);
		try {
			actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.createProject()))).perform();
			ElementSelector.clickByxpath(TasksTabPage.createProject());
			log.debug("Clicked on create customer button");
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.createProject())));
		}
	}

	// Creating task
	public static void creatingTask(String formattedDateTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// creating new task
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(TasksTabPage.addNewBtn()))).perform(); //
		ElementSelector.clickByxpath(TasksTabPage.addNewBtn());
		log.debug("Clicked on add new button to create new task");
		ElementSelector.clickByxpath(TasksTabPage.createNewTasks());
		log.debug("Clicked on create new task");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TasksTabPage.enteringTaskName())));
		try {
			driver.findElement(By.xpath(TasksTabPage.enteringTaskName())).clear();
		} catch (StaleElementReferenceException e) {
			driver.findElement(By.xpath(TasksTabPage.enteringTaskName())).clear();
		}
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.enteringTaskName(),
				prop.getProperty("taskName") + formattedDateTime);
		System.out.println("Created task : " + prop.getProperty("taskName") + formattedDateTime);
		log.debug("Entered Task name");
		ElementSelector.clickByxpath(TasksTabPage.createTask());

	}

	// Adding new task from tasks tab
	public static String creatingNewTaskFromAddTaskBtnTab(String formattedDateTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		String newSubTask = (prop.getProperty("newTaskName") + formattedDateTime);
		try {
			ElementSelector.clickByxpath(TasksTabPage.addNewTaskBtn());
		} catch (ElementClickInterceptedException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.addNewTaskBtn())));
		}
		log.debug("Clicked on add new button ");

		try {
			SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.addingNewTaskTextBox(), newSubTask);
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1];",
					driver.findElement(By.xpath(TasksTabPage.addingNewTaskTextBox())), newSubTask);
		}
		log.debug("Entered new task ");
		try {
			ElementSelector.clickByxpath(TasksTabPage.addTaskBtnAfterEneteringTask());
		} catch (Exception e) {
			WebElement addTaskBtn = driver.findElement(By.xpath(TasksTabPage.addTaskBtnAfterEneteringTask()));
			JavascriptExecutor js = (JavascriptExecutor) driver;
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TasksTabPage.searchCustomerName())));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = '';",
					driver.findElement(By.xpath(TasksTabPage.searchCustomerName())));
		}
		String createdCustomerName = prop.getProperty("customerName") + formattedDateTime;
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(), createdCustomerName);
		String createdCustomerXpath = "//div[@class='filteredContainer']//div[@class='itemsContainer']//*[text()='"
				+ createdCustomerName + "']";
		try {
			ElementSelector.clickByxpath(createdCustomerXpath);
		} catch (Exception e) {
			WebElement createdCustomerWebElement = driver.findElement(By.xpath(createdCustomerXpath));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", createdCustomerWebElement);
		}
	}

	// Searching and clicking on the newly created project of customer
	public static void serachingClickingNewlyCreatedCustomerFirstProject(String formattedDateTime)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			wait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
			driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='';", driver.findElement(By.xpath(TasksTabPage.searchCustomerName())));
		}
		String createdCustomerName = prop.getProperty("customerName") + formattedDateTime;
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(), createdCustomerName);
		String createdCustomerXpath = "//div[@class='filteredContainer']//div[@class='itemsContainer']//*[text()='"
				+ createdCustomerName + "']";
		try {
			ElementSelector.clickByxpath(createdCustomerXpath);
		} catch (Exception e) {
			WebElement createdCustomerWebElement = driver.findElement(By.xpath(createdCustomerXpath));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", createdCustomerWebElement);
		}

		ElementSelector.clickByxpath(TasksTabPage.viewCustomerProjects(createdCustomerName));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.firstProjectName())));
		ElementSelector.clickByxpath(TasksTabPage.firstProjectName());
	}

	// Editing customer name
	public static String editingCustomerName(String formattedDateTime) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String newCustomerName = prop.getProperty("editedCustomeName") + formattedDateTime;
		    try {
		        driver.navigate().refresh();
		        CommonTestStepMethods.serachingClickingNewlyCreatedCustomer(formattedDateTime);
		        WebElement editIcon = driver.findElement(By.xpath(TasksTabPage.editIcon()));
		        try {
		            ElementSelector.clickByxpath(TasksTabPage.editIcon());
		        } catch (Exception e1) {
		            try {
		                editIcon.click();
		            } catch (Exception e2) {
		                js.executeScript("arguments[0].click();", editIcon);
		            }
		        }

		        log.debug("Clicked on view customer edit icon");
		        WebElement customerNameLabelToEdit = driver.findElement(By.xpath(TasksTabPage.customerNameLabeToEdit()));
		        js.executeScript("arguments[0].click();", customerNameLabelToEdit);
		        log.debug("Clicked on old customer name label to edit");
		        WebElement customerNameInputBox = driver.findElement(By.xpath(TasksTabPage.customerNameInputBox()));
		        customerNameInputBox.clear();
		        customerNameInputBox.sendKeys(newCustomerName);
		        customerNameInputBox.sendKeys(Keys.RETURN);
		        ElementSelector.clickByxpath(TasksTabPage.closeCustomerEditTab());
		        log.debug("Edited customer name");
		    } catch (Exception e) {
		    	System.out.println("Exception in editing customer name : ");
		    }
		Thread.sleep(2000);
		return newCustomerName;

	}

	// Searching and clicking on the newly created customer
		public static void serachingClickingEditedCreatedCustomer(String editedCustomerName) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
				driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
			} catch (Exception e) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TasksTabPage.searchCustomerName())));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value = '';",
						driver.findElement(By.xpath(TasksTabPage.searchCustomerName())));
			}
			SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(), editedCustomerName);
			String createdCustomerXpath = "//div[@class='filteredContainer']//div[@class='itemsContainer']//*[text()='"
					+ editedCustomerName + "']";
			try {
				ElementSelector.clickByxpath(createdCustomerXpath);
			} catch (Exception e) {
				WebElement createdCustomerWebElement = driver.findElement(By.xpath(createdCustomerXpath));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", createdCustomerWebElement);
			}
		}
		
	// deleting edited customer
	public static void deletingEditedCustomer() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			ElementSelector.clickByxpath(TasksTabPage.editIcon());
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(TasksTabPage.editIcon())));
		}
		
		WebElement actionDropDown = driver.findElement(By.xpath(TasksTabPage.actionDropDown()));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.actionDropDown())));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionDropDown);
		}
		catch(Exception e) {
			Actions action = new Actions(driver);
			action.moveToElement(actionDropDown).click().perform();
			
		}
		WebElement deleteBtn = driver.findElement(By.xpath(TasksTabPage.delteBtn()));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TasksTabPage.delteBtn())));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
		WebElement deletePermanentlyBtn = driver.findElement(By.xpath(TasksTabPage.deletPermanentlyBtn()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deletePermanentlyBtn);
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
		System.out.println("Total tasks in tasksHM are" + totalTasks );
		return totalTasks;
	}

	// Getting tasks list and storing it in hashmap-list collection
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
				Thread.sleep(2000);
				List<WebElement> tasksOfCustomerList = driver
						.findElements(By.xpath(tasksListCheckTestObjects.tasksInCustomersRow()));
				for (WebElement taskOfCustomer : tasksOfCustomerList) {
//					wait.until(ExpectedConditions.visibilityOf(taskOfCustomer));
					tasks.add(taskOfCustomer.getText());
				}
				
				// Add the tasks for the current project to the map
				gettingTasksList.put(project, tasks);
				// Minimize the project 
				String customerName = "//*[text()='OPEN TASKS']//following::*[@class='projectName' and text()='"
						+ project + "']";
				WebElement minimize = driver.findElement(By.xpath(customerName));
				js.executeScript("arguments[0].click();", minimize);
			}
		} else {
			List<String> tasks = new ArrayList<>(); // Create a new list for the projects of customer
			// Find all tasks in the project of customer

			Thread.sleep(2000);
			List<WebElement> tasksOfCustomerList = driver
					.findElements(By.xpath(tasksListCheckTestObjects.tasksInCustomersRow()));
			for (WebElement taskOfCustomer : tasksOfCustomerList) {

				try {
					wait.until(ExpectedConditions.visibilityOf(taskOfCustomer));
					tasks.add(taskOfCustomer.getText());
				} catch (Exception e) {
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
		try {
			ElementSelector.clickByxpath(tasksListCheckTestObjects.BigBangCompanyCust());
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",
					driver.findElement(By.xpath(tasksListCheckTestObjects.BigBangCompanyCust())));
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", spaceShipTesting);
		HashMap<String, List<String>> ShipTestingProjectHM = CommonTestStepMethods
				.gettingTasksList("SpaceShipTestingProject");
		return ShipTestingProjectHM;
	}
	
	// Refresh and clicking on task tab 
	public static void refreshClickOnTasksTab(WebDriver driver) {
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		driver.navigate().refresh();
		CommonTestStepMethods.openigTask();
		}
		
}