package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.UsersPageObjects;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;
import stepsMethods.CommonTestStepMethods;
import stepsMethods.userPageMethods;

public class UsersPageTest extends BaseActi{
	static CommonTestStepMethods CommonTestStepMethods  = new CommonTestStepMethods();
	static UsersPageObjects UsersPageObjects = new UsersPageObjects(driver);
	
	@Test(priority = 1)
	public static void creatingDeartmentTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String formattedDateTime = stepsMethods.CommonTestStepMethods.dateTime();
		driver.navigate().refresh();
		ElementSelector.clickByxpath(UsersPageObjects.usersTabXpath());
		String departmentName = userPageMethods.creatingdepartment(formattedDateTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersPageObjects.createdDepatmentXpath(departmentName))));
		WebElement createdDepartment = driver.findElement(By.xpath(UsersPageObjects.createdDepatmentXpath(departmentName)));
		boolean isCreatedDepartmentDisplayed = createdDepartment.isDisplayed();
		Assert.assertTrue(isCreatedDepartmentDisplayed);
	}
	
	@Test(priority = 2)
	public static void editingCreatdDepartmentTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.navigate().refresh();
		String formattedDateTime = CommonTestStepMethods.dateTime();
		ElementSelector.clickByxpath(UsersPageObjects.usersTabXpath());
		String departmentName = userPageMethods.creatingdepartment(formattedDateTime);
		String newDepartmentName = userPageMethods.editingDepartmentName(departmentName, formattedDateTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersPageObjects.createdDepatmentXpath(newDepartmentName))));
		WebElement newDepartmentNameWebElement = driver.findElement(By.xpath(UsersPageObjects.createdDepatmentXpath(newDepartmentName)));
		boolean isEditedDepartmentDisplayed = newDepartmentNameWebElement.isDisplayed();
		Assert.assertTrue(isEditedDepartmentDisplayed);
	}
	
	@Test(priority = 3)
	public static void deletingDepartment() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String formattedDateTime = CommonTestStepMethods.dateTime();
		driver.navigate().refresh();
		ElementSelector.clickByxpath(UsersPageObjects.usersTabXpath());
		String departmentName = userPageMethods.creatingdepartment(formattedDateTime);
		userPageMethods.deleteDepartment(departmentName);
		driver.navigate().refresh();
		ElementSelector.clickByxpath(UsersPageObjects.departmentBtnXpath());
		boolean isDepartmentDelted = false;
		try {
			driver.findElement(By.xpath(UsersPageObjects.createdDepatmentXpath(departmentName))).isDisplayed();
		}
		catch(Exception e) {
			isDepartmentDelted = true;
			Assert.assertTrue(isDepartmentDelted);
		}

	}

}
