package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class addNewTab {

	WebDriver driver;
	public addNewTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	String addNewBtn = "//*[@id=\"taskListBlock\"]/div[1]/div[2]/div[2]/div/div[1]";
	public String addNewBtn() {
		return addNewBtn;
	}
	
	String addingNewTaskTextBox = "/html/body/div[45]/div[1]/div/div[1]/div/div[1]/div/div[2]/div/div[2]/input";
	public String addingNewTaskTextBox() {
		return addingNewTaskTextBox;
	}
	
	String addNewBtnAfterEneteringTask = "/html/body/div[45]/div[1]/div/div[1]/div/div[4]/div/div[3]/div[1]/div[1]";
	public String addNewBtnAfterEneteringTask() {
		return addNewBtnAfterEneteringTask;
	}
	
}
