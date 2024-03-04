package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters; 


public class BaseClass {

	public static WebDriver driver;
	public static Logger logger;
	public static Properties prop;
	
	
	@BeforeTest(groups= {"sanity","regression","master"})
	@Parameters({ "browser", "os" })
	public void setup(String browser, String os) throws IOException 
	
	{
		
		// setting properties
				prop = new Properties();
				FileReader file = new FileReader(System.getProperty("user.dir") + ".\\src\\test\\resources\\configuration.properties");
				prop.load(file);
				ChromeOptions chromeoption=new ChromeOptions();
				chromeoption.addArguments("--disable-notifications");
				EdgeOptions edgeoption=new EdgeOptions();
				edgeoption.addArguments("--disable-notifications");
				// Loading log4j file
				logger = LogManager.getLogger(this.getClass());
				if(prop.getProperty("execution_env").equalsIgnoreCase("remote")){	
					DesiredCapabilities capabilities=new DesiredCapabilities();
					//os
					if(os.equalsIgnoreCase("windows")){
						capabilities.setPlatform(Platform.WINDOWS);
					}else if(os.equalsIgnoreCase("mac")){
						capabilities.setPlatform(Platform.MAC);
					}else{
						System.out.println("No matching os..");
				return;
					}
					//browser
					if(browser.equalsIgnoreCase("chrome")) {
						capabilities.setBrowserName("chrome");
					}
					else if(browser.equalsIgnoreCase("edge")) {
						capabilities.setBrowserName("MicrosoftEdge");
					}
					else {
						System.out.println("no matching browser .....");
						return ;
					}
				//driver = new RemoteWebDriver(new URL("http://192.168.218.204:4444"), capabilities);
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			    }
				//If execution_env is local then run in local system
				else if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
				{
					//launching browser based on condition - locally
					if(browser.equalsIgnoreCase("chrome")) {
//						driver = new ChromeDriver(chromeoption);
						driver = new ChromeDriver();
						logger.info("Chrome browser opened successfully");
					}
					else if(browser.equalsIgnoreCase("edge")){
//						driver = new EdgeDriver(edgeoption);
						driver = new EdgeDriver();
						logger.info("Edge browser opened successfully");
					}
					else {
						System.out.println("no matching browser......");
						logger.info("no matching browser......");
						return ;
					}
				}
				driver.get(prop.getProperty("appURL"));
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				
	}
	
	@AfterTest(groups= {"sanity","regression","master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	public static Properties getProperties() throws IOException{
		return prop;
	}
	
	 public static String captureScreen(String tname)  {
			//String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\" + tname + "_" + ".png";
			File targetFile=new File(targetFilePath);
			sourceFile.renameTo(targetFile);
			return targetFilePath;
	    }
}
