package POM;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsedCars_2 extends basepage{

public Actions action;
public JavascriptExecutor executor;
public String filePath = null;

public UsedCars_2(WebDriver driver){

	super(driver);
	}


@FindBy(xpath = "//a[@href='/used-car']")

WebElement usedCarsDropdown;
@FindBy(xpath = "//span[@onclick=\"goToUrl('/used-car/Chennai')\"]")

WebElement usedCarsChennai;
@FindBy(xpath = "//div[contains(text(),'Popular Models')]")
WebElement popularModel;
@FindBy(xpath = "//ul[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']/li")
List<WebElement> modelList;


public void used_Cars_Dropdown_Visible() {
	executor = (JavascriptExecutor) driver;
	executor.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	
	WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
    waits.until(ExpectedConditions.visibilityOf(usedCarsDropdown));
	
}
public void move_To_Dropdown() {
	
	action = new Actions(driver);
	action.moveToElement(usedCarsDropdown).perform();
	
}

public void select_Chennai_Used_Cars() {
    
	usedCarsChennai.click();
}

public void popular_Model(){
	executor = (JavascriptExecutor) driver;
	executor.executeScript("arguments[0].scrollIntoView();",popularModel);
	
	WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
    waits.until(ExpectedConditions.visibilityOfAllElements(modelList));
    
    System.out.println("Following is the list of Popular models:");
    for (int i = 0; i < modelList.size(); i++) {
        System.out.println(modelList.get(i).getText());
    }
}

}
