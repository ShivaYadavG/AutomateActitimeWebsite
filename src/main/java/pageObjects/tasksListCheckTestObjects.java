package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class tasksListCheckTestObjects {

	WebDriver driver;

	public tasksListCheckTestObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String BigBangCompanyCust = "//div[text()='Big Bang Company']";

	public String BigBangCompanyCust() {
		return BigBangCompanyCust;
	}

	private String FlightOperationProject = "//div[@class='text'][text()='Flight operations']";

	public String FlightOperationProject() {
		return FlightOperationProject;
	}

	private String SpaceShipBuildingProject = "(//div[text()='Spaceship building'])[1]";

	public String SpaceShipBuildingProject() {
		return SpaceShipBuildingProject;
	}

	private String SpaceShipTestingProject = "(//div[text()='Spaceship testing'])[1]";

	public String SpaceShipTestingProject() {
		return SpaceShipTestingProject;
	}

	private String tasksListScrollBarContainer = "//div[@class='content kanbanVerticalScrollContainer']//div[@class='iScrollVerticalScrollbar iScrollLoneScrollbar']//div[@class='iScrollIndicator']";

	public String tasksListScrollBarContainer() {
		return tasksListScrollBarContainer;
	}

	private String newTabTasksList = "//div[@class='kanbanContent']//div[@class='projectTasksBlock']/descendant::div[@class='taskColumns']//div[@class='tasksColumn statusColor_yellow statusType_open']//div[@class='name gradientEllipsis']";

	public String newTabTasksList() {
		return newTabTasksList;
	}

	private String inPlanningTabTasksList = "//div[@class='kanbanContent']//div[@class='projectTasksBlock']/descendant::div[@class='taskColumns']//div[@class='tasksColumn statusColor_lightGreen statusType_open']//div[@class='name gradientEllipsis']";

	public String inPlanningTabTasksList() {
		return inPlanningTabTasksList;
	}

	private String inProgressTabTasksList = "//div[@class='kanbanContent']//div[@class='projectTasksBlock']/descendant::div[@class='taskColumns']//div[@class='tasksColumn statusColor_darkGreen statusType_open']//div[@class='name gradientEllipsis']";

	public String inProgressTabTasksList() {
		return inProgressTabTasksList;
	}

	private String inReviewTabTasksList = "//div[@class='kanbanContent']//div[@class='projectTasksBlock']/descendant::div[@class='taskColumns']//div[@class='tasksColumn statusColor_aquamarine statusType_open lastOpen']//div[@class='name gradientEllipsis']";

	public String inReviewTabTasksList() {
		return inReviewTabTasksList;
	}

	private String inDoneTabTasksList = "//div[@class='kanbanContent']//div[@class='projectTasksBlock']/descendant::div[@class='taskColumns']//div[@class='tasksColumn statusColor_violet statusType_completed']//div[@class='name gradientEllipsis']";

	public String inDoneTabTasksList() {
		return inDoneTabTasksList;
	}

	private String inWontDoTasksList = "//div[@class='kanbanContent']//div[@class='projectTasksBlock']/descendant::div[@class='taskColumns']//div[@class='tasksColumn statusColor_darkGray statusType_completed']//div[@class='name gradientEllipsis']";

	public String inWontDoTasksList() {
		return inWontDoTasksList;
	}

	private String FirstTask = "(//div[@class='name gradientEllipsis'])[1]";

	public String FirstTask() {
		return FirstTask;
	}

	private String tasksXpathCommonFirstHalf = "//div[contains(@class,'taskColumns')]/div[contains(@class,'tasksColumn')][count(//div[contains(text(),'; Review')]/ancestor::div[contains(@class,'statusHeader statusType')]/preceding-sibling::div)+1]//div[contains(@class,'name')]";

	public String tasksXpathCommon() {
		return tasksXpathCommonFirstHalf;

	}
	
	// css path scrollable div = .content.kanbanVerticalScrollContainer
	
	private String scrollableDiv = "//div[@class = 'kanbanViewContainer']";
	public String scrollableDiv() {
		return scrollableDiv;
	}
	
	private String tasksHeader = "//div[contains(@class,'statusHeader' )]//div[contains(@class,'statusHeader' )]//div[@class='name']";
	public String tasksHeader() {
		return tasksHeader;
	}
	
	private String minimizeCustomer = "//div[@class='kanbanContent']//div[@class='header']//div[@class='icon']";
	public String minimizeCustomer() {
		return minimizeCustomer;
	}
	
	private String tasksInCustomersRow = "//div[@class='projectTasksBlock']//following::div[@class='name gradientEllipsis']";
	public String tasksInCustomersRow() {
		return tasksInCustomersRow;
	}
}
