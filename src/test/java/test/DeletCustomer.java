package test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import jdk.jfr.internal.Logger;
import pageObjects.LogInPage;
import pageObjects.TasksTabPage;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;

public class DeletCustomer extends BaseActi {
	/*
	 * author: Shiva Yadv G , Email : shiva.yadav@relanto.ai - Login to Actitime
	 * application
	 */

	@Test
	public void DeleteCustomer() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		TasksTabPage TasksTabPage = new TasksTabPage(driver);
		SendKeysToElement SendKeysToElement = new SendKeysToElement(driver);
		ElementSelector ElementSelector = new ElementSelector(driver);
		LogInPage LogInPage = new LogInPage(driver);
		log = LogManager.getLogger(DeletCustomer.class.getName());

		// Log in
		SendKeysToElement.snedKeysToElementById(LogInPage.userName(), prop.getProperty("userName"));
		SendKeysToElement.sendKeysToElementByXpath(LogInPage.password(), prop.getProperty("password"));
		ElementSelector.clickById(LogInPage.logInButton());

		// opening Tasks tab
		ElementSelector.clickById(TasksTabPage.tasksTab());
		String ViewTimeTrackPageTabURL = driver.getCurrentUrl();
		Assert.assertEquals(ViewTimeTrackPageTabURL, "https://online.actitime.com/relanto/tasks/tasklist.do");

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.searchCustomerName()))));
		driver.findElement(By.xpath(TasksTabPage.searchCustomerName())).clear();
		SendKeysToElement.sendKeysToElementByXpath(TasksTabPage.searchCustomerName(),prop.getProperty("deleterCustomer"));
		log.debug("Searched customer name");

		try {
			ElementSelector.clickByxpath(TasksTabPage.customerName());
			String deltedElement = driver.findElement(By.xpath(TasksTabPage.customerName())).getText();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.editIcon()))));
			ElementSelector.clickByxpath(TasksTabPage.editIcon());
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TasksTabPage.actionDropDown()))));
			ElementSelector.clickByxpath(TasksTabPage.actionDropDown());
			ElementSelector.clickByxpath(TasksTabPage.delteBtn());
			ElementSelector.clickByxpath(TasksTabPage.deletPermanentlyBtn());
			log.debug(" customer name : " + deltedElement + " is deleted");
		} catch (NoSuchElementException e) {
			log.error("Element not found: " + e.getMessage());

		}

		finally {
			log.debug(prop.getProperty("deleterCustomer") + " : is not present");
		}
	}
}
