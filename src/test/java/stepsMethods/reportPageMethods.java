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
	
	public static String creteWidget() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	String formattedDateTime = CommonTestStepMethods.dateTime();
	CommonTestStepMethods.clickingOnReportsTab();
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
}
