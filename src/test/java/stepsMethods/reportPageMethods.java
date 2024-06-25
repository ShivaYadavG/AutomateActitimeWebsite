package stepsMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.reportPageObjects;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;

public class reportPageMethods extends BaseActi{
	static CommonTestStepMethods CommonTestStepMethods  = new CommonTestStepMethods();
	static reportPageObjects reportPageObjects = new reportPageObjects(driver);
	static ElementSelector ElementSelector = new ElementSelector(driver);
	static SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);
	
	// Clicking on Reports tab from nav bar
	public static void clickingOnReportsTab() {
		ElementSelector.clickByxpath(reportPageObjects.reprtsTabXpath());
	}
	
	public static String creteWidget() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	String formattedDateTime = CommonTestStepMethods.dateTime();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportPageObjects.createWidgetBtnXpath())));
	ElementSelector.clickByxpath(reportPageObjects.createWidgetBtnXpath());
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reportPageObjects.enterWidgetNameXpath())));
	String widgetName =  prop.getProperty("widgetName")+formattedDateTime;
	SendKeysToElement.sendKeysToElementByXpath(reportPageObjects.enterWidgetNameXpath(),widgetName);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportPageObjects.addWidgetXpath())));
	ElementSelector.clickByxpath(reportPageObjects.addWidgetXpath());
	return widgetName;
	
	}
	
	public static void deleteWidget(String widgetName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportPageObjects.deleteICon(widgetName))));
		ElementSelector.clickByxpath(reportPageObjects.deleteICon(widgetName));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportPageObjects.deleteWidgetXpath())));
		ElementSelector.clickByxpath(reportPageObjects.deleteWidgetXpath());		
	}
	
	public static String editWidgetName(String widgetName) {
		String formattedDateTime = CommonTestStepMethods.dateTime();
		String newWidgetName =  prop.getProperty("newWidgetName")+formattedDateTime;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportPageObjects.settingIconWidgetXpath(widgetName))));
		ElementSelector.clickByxpath(reportPageObjects.settingIconWidgetXpath(widgetName));
		driver.findElement(By.xpath(reportPageObjects.enterWidgetNameInputXpath())).clear();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(reportPageObjects.enterWidgetNameInputXpath())));
		SendKeysToElement.sendKeysToElementByXpath(reportPageObjects.enterWidgetNameInputXpath(), newWidgetName);
		ElementSelector.clickByxpath(reportPageObjects.SaveWidgetBtnXpath());
		return newWidgetName;
	}
}
