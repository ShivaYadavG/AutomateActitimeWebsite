package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import resources.ElementSelector;

public class UsersPageObjects {

	WebDriver driver;

	public UsersPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String usersTabXpath = "//td[contains(@class,'navItem')]//div[@class='label'][text()='Users']";

	public String usersTabXpath() {
		return usersTabXpath;
	}

	private String departmentBtnXpath = "//div[@class='userList_manageButtons_component_manageGroupsButton']//div[contains(@class,'userList_manageButtons_component_groupItem department')][normalize-space()='Departments']";

	public String departmentBtnXpath() {
		return departmentBtnXpath;
	}

	private String enterDepartmentInputBox = "//input[@id='groupManagementLightBox_newGroupInput']";

	public String enterDepartmentInputBox() {
		return enterDepartmentInputBox;
	}

	private String addBtnAterEnteringDepartmentName = "//button[@id='groupManagementLightBox_addGroupButton']";

	public String addBtnAterEnteringDepartmentName() {
		return addBtnAterEnteringDepartmentName;

	}
	
	public String createdDepatmentXpath(String departMentName) {
		String createdDepatmentXpath= "//div[@title='"+ departMentName + "']";
		return createdDepatmentXpath;
	}
	
	public String inputBoxToEditDepartment(String departMentName) {
	 String inputBoxToEditDepartment = "//div[@title='"+departMentName +"']/..//div[@class='editGroupTableCell']//input";
	return inputBoxToEditDepartment;
	}
	
	public String tickSignAfterEditingDepartmentNameToSave(String editedDepartmentName) {
		String tickSignAfterEditingDepartmentNameToSave  = "//div[@title='" + editedDepartmentName + "']/..//div[@class='editGroupTableCell']//td[contains(@id,'groupManagementLightBox_editUserGroupAcceptButton')]//div//img";
		return tickSignAfterEditingDepartmentNameToSave;
	}
	
	public String crossSymbolToDeleteDepartment(String DepartmentName) {
		String crossSymbolToDeleteDepartment  = "//div[@title='"+ DepartmentName + "']/../../..//td[@class='deleteGroupCell']//span";
		return crossSymbolToDeleteDepartment;
	}
	
	public String yesDeletedBtn(String DepartmentName) {
		String yesDeletedBtn  = "//div[@title='"+DepartmentName+"']/../../..//td[@class='confirmDeleteGroupCell']//td//button[@class='confirmDeleteGroupButton']";
		return yesDeletedBtn;
	}
}
