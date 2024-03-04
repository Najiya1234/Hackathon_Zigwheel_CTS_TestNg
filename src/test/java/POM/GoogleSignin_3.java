package POM;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSignin_3 extends basepage{

	public JavascriptExecutor executor;
	public String filePath = null;
	
	public GoogleSignin_3(WebDriver driver){

		super(driver);
		}

	 @FindBy(xpath= "//a[contains(text(),'Forum')]")
	 WebElement forum;
	 
	 @FindBy(xpath= "//div[@id='forum_login_title_lg']")
	 WebElement signup;
	 
	 @FindBy(css="div[class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']")
	 WebElement google;
	 
	 @FindBy(id = "identifierId")
     WebElement email;
	 @FindBy(xpath = "//span[contains(text(),'Next')]")
     WebElement emailNextButton;
	 
	 @FindBy(xpath="//div[@class='o6cuMc Jj6Lae' and contains(text(),'Enter a valid email or phone number')]")
     WebElement errorMessage;
	 
	 public void click_Forum() {
//		 forum.click();
		 executor =(JavascriptExecutor) driver;
		  executor.executeScript("arguments[0].click();",forum);
	 }
	 public String Forum_Page_Title() {
			String actual = driver.getTitle();
			return actual;
		}
	 public void click_Signup() {
		 
		 signup.click();
//		 executor =(JavascriptExecutor) driver;
//		  executor.executeScript("arguments[0].click();",signup);
	 }
	 public void click_Google() {
		 

		 executor =(JavascriptExecutor) driver;
		  executor.executeScript("arguments[0].click();",google);
	 }
	 public void signin_With_Email() {
		 try {
		        // Get all window handles
		        Set<String> windows = driver.getWindowHandles();
		        List <String> windowslist = new ArrayList(windows);
		        
		        String parent = windowslist.get(0);
		       
		        String child = windowslist.get(1);
		       
		        driver.switchTo().window(child);
		        email.sendKeys("abd@abc");
		       
		       } 
		 catch (Exception e ) {
		 e.printStackTrace();
		         
		    }
	 }
	 
	 public void  email_NextButton_Click() {
		 emailNextButton.click();
		 
	 }
	 
	 public void get_Error_Message() {
		 WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
	       waits.until(ExpectedConditions.visibilityOfAllElements(errorMessage));
		 String message= errorMessage.getText();
		 System.out.println(message);
	 }
	 

}
