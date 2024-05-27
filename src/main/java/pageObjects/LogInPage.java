package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage{
	WebDriver driver;
	public LogInPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	private String CreateCustomer = "//*[@id=\"customerLightBox_content\"]/div[3]/div[2]/div[1]/div/div[1]";
	public String CreateCustomer() {
		return CreateCustomer;
	}
	
	private String userName = "username";
	public String userName() {
		return userName;
	}
	
	private String password = "//*[@id=\"loginFormContainer\"]/tbody/tr[1]/td/table/tbody/tr[2]/td/input";
	public String password() {
		return password;
	}
	
	private String logInButton = "loginButton";
	public String logInButton() {
		return logInButton;
	}
	

}


