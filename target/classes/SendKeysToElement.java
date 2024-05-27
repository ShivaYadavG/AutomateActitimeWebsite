package resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SendKeysToElement {
	
	static WebDriver driver = null;
	public SendKeysToElement(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public static void sendKeysToElementByXpath(String xpath, String keys) {
		driver.findElement(By.xpath(xpath)).sendKeys(keys);
	}
	
	public static void snedKeysToElementById(String id, String keys) {
		driver.findElement(By.id(id)).sendKeys(keys);
	}
	
	public static void snedKeysToElementByLinkText(String linkText, String keys) {
		driver.findElement(By.id(linkText)).sendKeys(keys);
	}

}
