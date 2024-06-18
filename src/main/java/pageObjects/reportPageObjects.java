package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class reportPageObjects {
	
	WebDriver driver;
	
	public reportPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private String reprtsTabXpath = "//td[contains(@class,'navItem')]//div[text()='Reports']";
	public String reprtsTabXpath() {
		return reprtsTabXpath;
	}
	
	private String enterWidgetNameXpath = "//div[contains(@class,'PopupWrapper-popup')]//input[@placeholder='Enter Widget Name']";
	public String enterWidgetNameXpath() {
		return enterWidgetNameXpath;
	}
	
	private String createWidgetBtnXpath = "//button[contains(@class,'components')]//div[text()='Create Widget']";
	public String createWidgetBtnXpath() {
		return createWidgetBtnXpath;
	}
	
	private String addWidgetXpath = "//div[contains(@class,'PopupWrapper-popup')]//span[text()='Add Widget']";
	public String addWidgetXpath() {
		return addWidgetXpath;
	}
	
	public String CreatedWidget(String widgetName) {
	String NewlyCreatedWidgetXpath = "//div[contains(@class,'containers')]//span[text()='" + widgetName+"']";
	return NewlyCreatedWidgetXpath;
	}
	
	public String deleteICon(String widgetName) {
		String deleteIcon = "//div[contains(@class,'containers')]//span[text()='"+widgetName+"']/../../..//div[contains(@class,'Widget-header')]//*[name()='svg'][contains(@class,'delete')]";
		return deleteIcon;
	}
	
	private String deleteWidgetXpath ="//button[text()='Yes, Delete']";
	public String deleteWidgetXpath() {
		return deleteWidgetXpath;
	}
}
