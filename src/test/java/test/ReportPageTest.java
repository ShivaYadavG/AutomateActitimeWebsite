package test;

import java.time.Duration;
import java.util.List;

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
		String widgetName = reportPageMethods.creteWidget();
		reportPageMethods.deleteWidget(widgetName);
		String createdWidgetXpath = "//*[text()='" + widgetName + "']";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(createdWidgetXpath),0));
		List<WebElement> elements = driver.findElements(By.xpath(createdWidgetXpath));
		Assert.assertEquals(0, elements.size());
	}
}
