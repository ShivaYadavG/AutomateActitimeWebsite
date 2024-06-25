package stepsMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.UsersPageObjects;
import resources.BaseActi;
import resources.ElementSelector;
import resources.SendKeysToElement;

public class userPageMethods extends BaseActi{
	static UsersPageObjects UsersPageObjects = new UsersPageObjects(driver);

	
	public static String creatingdepartment(String formattedDateTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UsersPageObjects.departmentBtnXpath())));
		ElementSelector.clickByxpath(UsersPageObjects.departmentBtnXpath());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersPageObjects.enterDepartmentInputBox())));
		String departmentName = prop.getProperty("departmetnName")+ formattedDateTime;
		SendKeysToElement.sendKeysToElementByXpath(UsersPageObjects.enterDepartmentInputBox(), prop.getProperty("departmetnName")+ formattedDateTime);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UsersPageObjects.addBtnAterEnteringDepartmentName())));
		ElementSelector.clickByxpath(UsersPageObjects.addBtnAterEnteringDepartmentName());
		return departmentName;
	}
	
	public static String editingDepartmentName(String departmentName, String formattedDateTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersPageObjects.createdDepatmentXpath(departmentName))));
		ElementSelector.clickByxpath(UsersPageObjects.createdDepatmentXpath(departmentName));
		driver.findElement(By.xpath(UsersPageObjects.inputBoxToEditDepartment(departmentName))).clear();
		String newDepartmentName = prop.getProperty("editedDepartmentName")+ formattedDateTime;
		SendKeysToElement.sendKeysToElementByXpath(UsersPageObjects.inputBoxToEditDepartment(departmentName), newDepartmentName);
		ElementSelector.clickByxpath(UsersPageObjects.tickSignAfterEditingDepartmentNameToSave(departmentName));
		return newDepartmentName;
	}
	
	public static void deleteDepartment(String departmentName) {
		ElementSelector.clickByxpath(UsersPageObjects.crossSymbolToDeleteDepartment(departmentName));
		ElementSelector.clickByxpath(UsersPageObjects.yesDeletedBtn(departmentName));
	}
}

