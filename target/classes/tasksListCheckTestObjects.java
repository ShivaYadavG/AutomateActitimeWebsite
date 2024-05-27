package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class tasksListCheckTestObjects {
	
	WebDriver driver;
	public tasksListCheckTestObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private String BigBangCompanyCust="//div[text()='Big Bang Company']";
	public String BigBangCompanyCust() {
		return BigBangCompanyCust;
	}
	
	private String newTab= "//div[@class='kanbanContent']//div[@class='projectTasksBlock']//div[@class='taskColumns']//div[@class='tasksColumn statusColor_yellow statusType_open']//div[@class='name gradientEllipsis']";
	public String newTab() {
		return newTab;
	}

}
