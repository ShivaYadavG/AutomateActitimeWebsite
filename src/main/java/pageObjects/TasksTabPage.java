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
	
	private String tasksTab = "//div[@id='container_tasks']/following-sibling::div[text()='Tasks']";

	public String tasksTab() {
		return tasksTab;
	}
	
	
	private String addNewBtn = "//div[@class='addNewContainer']/descendant::div[@class='title ellipsis']/following-sibling::div";

	public String addNewBtn() {
		return addNewBtn;
	}

	private String newCustomerBtn = "//div[@class='item createNewCustomer']";  // Selector hub
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

	private String newProject = "//div[@class='item createNewProject']";
	public String newProject() {
		return newProject;
	}

	private String enterProjectName = "//div[@class='projectSection']/descendant::input[contains(@class,'projectNameField')]";

	public String enterProjectName() {
		return enterProjectName;
	}

	private String taskName = "//div[@class='projectSection']/descendant::td[contains(@class,'nameCell first')][1]";

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
	
	private String collapseAllProjectsBtn = "//div[@class='collapseAllButton']//div[@class='collapseAllButtonLabel']";
	public String collapseAllProjectsBtn() {
		return collapseAllProjectsBtn;
	}	

	public String viewCustomerProjects(String createdCustomeName) {
		String viewCustomerProjects = "//div[@class='filteredContainer']//div[@class='itemsContainer']//*[text()='"+createdCustomeName+"']/../../../following-sibling::div//div[text()='view customer projects']";
		return viewCustomerProjects;
	}
	
	public String customerProjectName(String createdCustomeName) {
		String customerProjectName ="//div[@class='filteredContainer']//div[@class='itemsContainer']//*[text()='"+createdCustomeName+"']/../../../following-sibling::div[contains(@class,'projectNode notSelected')]//div[@class='text']";
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

	String actiondDropDown = "(//div[@class='actionButtonWrapper']/div[@class='actionButton'])[1]//div[text()='ACTIONS']";

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

	public String firstCustomerNameNotSelected(String createdCustomeName) {
		String createdCustomerXpath = "//div[@class='filteredContainer']//div[@class='itemsContainer']//*[text()='" + createdCustomeName + "']";
		return createdCustomerXpath;
	}
	
	String ListOfCustomersAfterSearchXpath = "//div[@class='filteredContainer']//div[@class='itemsContainer']//div[contains(@class,'customerNode')]//div[@class='title']//div[@class='text']";
	public String ListOfCustomersAfterSearchXpath() {
		return ListOfCustomersAfterSearchXpath;
	}
	
	String firstCustomerNameNotSelectedXpath = "(//div[@class='filteredContainer']//div[@class='itemsContainer']//div[contains(@class,'customerNode')]//div[@class='title']//div[@class='text'])[1]";

	public String firstCustomerNameNotSelectedXpath() {
		return firstCustomerNameNotSelectedXpath;
	}

	String firstProjectName = "//div[@class='filteredContainer']//div[@class='itemsContainer']//div[contains(@class,'node projectNode notSelected editable')]//div[@class='title']//div[@class='text']";

	public String firstProjectName() {
		return firstProjectName;
	}

	String firstProjectNameAfterAddingSubTask = "//div[@class='filteredContainer']//div[@class='itemsContainer']//div[contains(@class,'node projectNode editable selected')]//div[@class='title']";

	public String firstProjectNameAfterAddingSubTask() {
		return firstProjectNameAfterAddingSubTask;
	}

	String addNewTaskBtn = "//*[@class='rightContainer']//*[text()='Add Task']";

	public String addNewTaskBtn() {
		return addNewTaskBtn;
	}

	String addingNewTaskTextBox = "//div[contains(@class,'nameInfo editable')]//div[@class='nameInput']//input[@placeholder='Enter Task Name']";

	public String addingNewTaskTextBox() {
		return addingNewTaskTextBox;
	}

	String addTaskBtnAfterEneteringTask = "(//div[@class='buttonsBox']//*[@class='components_button_label'][text()='Add Task'])[2]";

	public String addTaskBtnAfterEneteringTask() {
		return addTaskBtnAfterEneteringTask;
	}

}
