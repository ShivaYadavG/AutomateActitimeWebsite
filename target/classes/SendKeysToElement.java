package resources;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendKeysToElement {
	
	static WebDriver driver = null;
	public SendKeysToElement(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public static void sendKeysToElementByXpath(String xpath, String keys) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).sendKeys(keys);
	}
	
	public static void snedKeysToElementById(String id, String keys) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		driver.findElement(By.id(id)).sendKeys(keys);
	}
	
	public static void snedKeysToElementByLinkText(String linkText, String keys) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
		driver.findElement(By.id(linkText)).sendKeys(keys);
	}

}
