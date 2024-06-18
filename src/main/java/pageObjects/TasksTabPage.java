package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TasksTabPage {
	WebDriver driver;

	public TasksTabPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private static String timeTrackTab = "container_tt";
	
	public static String timeTrackTab() {
		return timeTrackTab;
	}
	
	/* Using id for tasks tab
	private String tasksTab = "container_tasks";

	public String tasksTab() {
		return tasksTab;
	}
	*/
	
	//using xpath for tasks tab
	private String tasksTab = "//div[@id='container_tasks']/following-sibling::div[text()='Tasks']";

	public String tasksTab() {
		return tasksTab;
	}
	
	
	private String addNewBtn = "//div[@class='addNewContainer']/descendant::div[@class='title ellipsis']/following-sibling::div";

	public String addNewBtn() {
		return addNewBtn;
	}

	private String newCustomerBtn = "//div[@class='dropdownContainer addNewMenu']/descendant::div[@class='item createNewCustomer']";

	public String newCustomerBtn() {
		return newCustomerBtn;
	}

	private String enterCustomerNameTextBox = "//div[@class='innerContent']//div[@class='customerNameDiv']//input";

	public String enterCustomerNameTextBox() {
		return enterCustomerNameTextBox;
	}

	private String createCustomer = "//div[text()='Create Customer']";

	public String createCustomer() {
		return createCustomer;
	}

	private String newProject = "//div[@class=\"dropdownContainer addNewMenu\"]//descendant::div[@class=\"item createNewProject\"]";

	public String newProject() {
		return newProject;
	}

	private String enterProjectName = "//div[@class=\"projectSection\"]/descendant::input[contains(@class,'projectNameField')]";

	public String enterProjectName() {
		return enterProjectName;
	}

	private String taskName = "//div[@class=\"projectSection\"]/descendant::td[contains(@class,'nameCell first')][1]";

	public String taskName() {
		return taskName;
	}

	private String createProject = "//div[text()='Create Project']";

	public String createProject() {
		return createProject;
	}

	private String createNewTasks = "//div[@class='dropdownContainer addNewMenu']/descendant::div[@class='item createNewTasks']";

	public String createNewTasks() {
		return createNewTasks;
	}

	private String enteringTaskName = "(//div[@class='tablePlaceholder']/descendant::tbody/tr/td/input)[1]";

	public String enteringTaskName() {
		return enteringTaskName;
	}

	private String createTask = "(//div[@class='basicLightboxFooter']/descendant::div[@class='commitButtonPlaceHolder'])[3]";

	public String createTask() {
		return createTask;
	}

	private String searchCustomerName = "//div[@class='customersProjectsPanel']/descendant::input";

	public String searchCustomerName() {
		return searchCustomerName;
	}

	private String firstCustomerNameAfterSearch = "//div[contains(@class,'customerNode')]//following::div[text()='view customer projects']//ancestor::div[@class='itemsContainer']//div[contains(@class,'customerNode')]//div[@class='title']";

	public String firstCustomerNameAfterSearch() {
		return firstCustomerNameAfterSearch;
	}

	private String customerNameLabeToEdit = "//div[@class='editCustomerPanelHeader']/div[@class='customerNamePlaceHolder']//div[@class='nameLabel']";
//	private String customerNameLabeToEdit = "//div[@class='editCustomerPanelHeader']/div[@class='customerNamePlaceHolder']";
	public String customerNameLabeToEdit() {
		return customerNameLabeToEdit;
	}

	private String customerNameInputBox = "//div[@class='nameInput']/input[@placeholder = 'Enter Customer Name']";

	public String customerNameInputBox() {
		return customerNameInputBox;
	}

	private String closeCustomerEditTab = "(//div[@class='hideButton_panelContainer'])[1]";

	public String closeCustomerEditTab() {
		return closeCustomerEditTab;
	}

	private String noCustomerPresentLable = "//div[@class='notFoundMessage']";

	public String noCustomerPresentLable() {
		return noCustomerPresentLable;
	}

	private String customerName = "//*[@id=\"taskManagementPage\"]/div[1]/div[1]/div[2]/div/div[3]/div/div[1]/div[2]/div/div[3]/div[1]/span";

	public String customerName() {
		return customerName;
	}

	private String collapseButton = "//*[@id=\"taskManagementPage\"]/div[1]/div[1]/div[2]/div/div[3]/div/div[1]/div[2]/div[1]/div[1]";

	public String collapseButton() {
		return collapseButton;
	}

	private String viewCustomerProjects = "//div[text()='view customer projects'][1]";

	public String viewCustomerProjects() {
		return viewCustomerProjects;
	}

	private String customerProjectName = "(//div[@class=\"filteredContainer\"]//div[contains(@class,'projectNode')]//div[@class='title'])[2]";

	public String customerProjectName() {
		return customerProjectName;
	}

	private String customerNamesList = "//div[@class='unfilteredContainer']//div[contains(@class,'customerNode')]//div[contains(@class,'text')]";

	public String customerNamesList() {
		return customerNamesList;
	}

	String tasksList = "(//div[contains(@class,'tasksColumn')])[1]//div[contains(@class,'name gradientEllipsis')]";

	public String tasksList() {
		return tasksList;
	}

	String editIcon = "//div[@class='itemsContainer']/div[contains(@class,'selected')]/descendant::div[@class='editButton']";

	public String editIcon() {
		return editIcon;
	}

	String actiondDropDown = "(//div[@class='actionButtonWrapper']/div[@class='actionButton'])[1]";

	public String actionDropDown() {
		return actiondDropDown;
	}

	String deletBtn = "(//div[@class='dropdownContainer actionsMenu']//div[@class='deleteButton'])[1]";

	public String delteBtn() {
		return deletBtn;
	}

	String deletPermanentlyBtn = "//span[contains(text(),'Delete permanently')]//parent::div[@class='buttonIcon']";

	public String deletPermanentlyBtn() {
		return deletPermanentlyBtn;
	}

	String firstNewlyAddedTask = "(//div[contains(@class,'tasksColumn')])[1]//div[contains(@class,'recentlyCreated')]";

	public String firstNewlyAddedTask() {
		return firstNewlyAddedTask;
	}

	String firstCustomerNameNotSelected = "//div[@class='filteredContainer']//div[@class='itemsContainer']//div[@class='title']";

	public String firstCustomerNameNotSelected() {
		return firstCustomerNameNotSelected;
	}

	String firstCustomerName = "//div[@class='filteredContainer']//div[@class='itemsContainer']//div[contains(@class,'customerNode')]//div[@class='title']//div[@class='text']";

	public String firstCustomerName() {
		return firstCustomerName;
	}

	String firstProjectName = "//div[@class='filteredContainer']//div[@class='itemsContainer']//div[contains(@class,'node projectNode notSelected editable')]//div[@class='title']";

	public String firstProjectName() {
		return firstProjectName;
	}

	String firstProjectNameAfterAddingSubTask = "//div[@class='filteredContainer']//div[@class='itemsContainer']//div[contains(@class,'node projectNode editable selected')]//div[@class='title']";

	public String firstProjectNameAfterAddingSubTask() {
		return firstProjectNameAfterAddingSubTask;
	}
	// minimize and maximize
	// //*[text()='OPEN TASKS']/following::*[@class='projectName' and text()='Flight
	// operations']/..//div[@class='icon']

	String addNewTaskBtn = "//*[@class='rightContainer']//*[text()='Add Task']";

	public String addNewTaskBtn() {
		return addNewTaskBtn;
	}

	String addingNewTaskTextBox = "/html/body/div[45]/div[1]/div/div[1]/div/div[1]/div/div[2]/div/div[2]/input";

	public String addingNewTaskTextBox() {
		return addingNewTaskTextBox;
	}

	String addNewBtnAfterEneteringTask = "(//div[@class='buttonsBox']//*[@class='components_button_label'][text()='Add Task'])[2]";

	public String addNewBtnAfterEneteringTask() {
		return addNewBtnAfterEneteringTask;
	}

}
