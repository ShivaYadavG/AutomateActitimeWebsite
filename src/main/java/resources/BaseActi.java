package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
//import test.CommonTest;
import pageObjects.TasksTabPage;

public class BaseActi {
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;

	public static WebDriver initializeActiBrowser() throws IOException {
		
		prop = new Properties();
		String propertiesPath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
		System.out.println(propertiesPath);
		FileInputStream fis = new FileInputStream(propertiesPath);
		prop.load(fis);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		Actions actions = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}

	public String takeScreenshot(String testName, WebDriver driver) throws IOException {

		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + ".png";
		FileUtils.copyFile(SourceFile, new File(destinationFilePath));
		return destinationFilePath;
	}
	
	
	@BeforeSuite
	public void launching() throws IOException {
		log = LogManager.getLogger(BaseActi.class.getName());
		driver = initializeActiBrowser();
		log.debug("----------------------------------------------------");
		log.debug("Browser initialized");
		driver.get(prop.getProperty("URL"));
		log.debug("Web Applicaton  launched");
	}

	@AfterSuite
	public void closure() {
		driver.close();
		log.debug("Web Application closed");
		log.debug("----------------------------------------------------");

	}
		
	
}
