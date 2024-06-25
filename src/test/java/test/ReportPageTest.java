package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import resources.BaseActi;
import stepsMethods.CommonTestStepMethods;
import stepsMethods.reportPageMethods;
import pageObjects.reportPageObjects;
import resources.ElementSelector;
import resources.SendKeysToElement;

public class ReportPageTest extends BaseActi {

	CommonTestStepMethods CommonTestStepMethods = new CommonTestStepMethods();
	reportPageObjects reportPageObjects = new reportPageObjects(driver);
	ElementSelector ElementSelector = new ElementSelector(driver);
	SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);

	@Test(priority = 1)
	public void creatAndVerifyWidget() {
		driver.navigate().refresh();
		reportPageMethods.clickingOnReportsTab();
		String widgetName = reportPageMethods.creteWidget();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(reportPageObjects.CreatedWidget(widgetName))));
		boolean widgetIsCreted = driver.findElement(By.xpath(reportPageObjects.CreatedWidget(widgetName)))
				.isDisplayed();
		System.out.println(widgetIsCreted);
		Assert.assertEquals(true, widgetIsCreted);
	}

	@Test(priority = 2)
	public void deleteWidget() {
		driver.navigate().refresh();
		reportPageMethods.clickingOnReportsTab();
		String widgetName = reportPageMethods.creteWidget();
		reportPageMethods.deleteWidget(widgetName);
		String createdWidgetXpath = "//*[text()='" + widgetName + "']";
		try {
			driver.navigate().refresh();
			WebElement deletedWidget = driver.findElement(By.xpath(createdWidgetXpath));
			System.out.println(deletedWidget + " widget is not deleted");
		}
		catch(Exception e) {
			boolean isWidgetDeleted = true;
			Assert.assertTrue(isWidgetDeleted);
		}
	}
	
	@Test(priority = 3)
	public void editNameOfwidget() {
		driver.navigate().refresh();
		reportPageMethods.clickingOnReportsTab();
		String widgetName = reportPageMethods.creteWidget();
		System.out.println(widgetName);
		String NewWidgetName = reportPageMethods.editWidgetName(widgetName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		System.out.println(NewWidgetName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reportPageObjects.widgetName(NewWidgetName))));
		boolean isNewWidgetDisplayed = driver.findElement(By.xpath(reportPageObjects.widgetName(NewWidgetName))).isDisplayed();
		Assert.assertTrue(isNewWidgetDisplayed);
		
	}
	
}
