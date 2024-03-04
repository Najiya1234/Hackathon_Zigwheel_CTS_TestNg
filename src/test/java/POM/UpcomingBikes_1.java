package POM;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UpcomingBikes_1 extends basepage{

public Actions action; 
public JavascriptExecutor executor;
public String filePath = null;
public static Properties prop;

	
	public UpcomingBikes_1(WebDriver driver)
	{    
		super(driver);
        
	}
	
    
	@FindBy(xpath="//a[contains(text(),'New Bikes')]") 
	WebElement newBikesDropdown;
	
	@FindBy(xpath="//span[contains(text(),'Upcoming Bikes')]")
	WebElement upcomingBikes;
	
	@FindBy(id="makeId")
	WebElement manufacturers; 
	
	@FindBy(css="span[class='fnt-12 b rmec jr-m c-p']")
	WebElement readMore;
	
	@FindBy(xpath="//span[contains(text(),'View More Bikes')]")
	
	WebElement viewMoreBikes;
	
	@FindBy(xpath= "//table[@class='tbl bdr fnt-12 pl-15 pr-15 mb-15']/tbody/tr/td[1]")
	 List<WebElement> modelElements;
	
	@FindBy(xpath= "//table[@class='tbl bdr fnt-12 pl-15 pr-15 mb-15']/tbody/tr/td[2]")
	 List<WebElement> expectedPriceElements;
	
	@FindBy(xpath= "//table[@class='tbl bdr fnt-12 pl-15 pr-15 mb-15']/tbody/tr/td[3]")
	 List<WebElement> expectedLaunchDateElements;

	@FindBy(xpath=" //*[@class='lnk-hvr block of-hid h-height']") List<WebElement> BikeName;
	@FindBy(xpath="//div[@class='b fnt-15']") List<WebElement> BikePrice;
	@FindBy(xpath="//*[@class='clr-try fnt-14']") List<WebElement> BikeLaunchDate;
	
	List<String> bikeNamesTxt = new ArrayList<String>();
	List<String> bikePricesTxt = new ArrayList<String>();
	List<String> bikeLaunchDatesTxt = new ArrayList<String>();
	Map<String,String[]> bikeUnder4Lac = new LinkedHashMap<String,String[]>();
	
	 @FindBy(xpath = "//*[@id='modelList']")
	  WebElement hondaBikeModels;
	 
	public String homeTitle() {
				String actual = driver.getTitle();
				return actual;
			}
			
	public void new_Bikes_Dropdown() {
		
		action = new Actions(driver);
		action.moveToElement(newBikesDropdown).perform();
		
		
	}
	
	public boolean verify_upcomingBikes() {
				boolean actual=upcomingBikes.isDisplayed();
				return actual;
			}

	public void selecting_Upcoming_Bikes()  {
	
	upcomingBikes.click();
	}
	
	public String upcomingBikePageUrl() {
		String actual = driver.getCurrentUrl();
		return actual;
	}
	
   public void manufacturers_Dropdown() {
	   
	   WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
       waits.until(ExpectedConditions.elementToBeClickable(manufacturers));
	   
	   Select sc = new Select(manufacturers);
	   sc.selectByVisibleText("Honda");
	   
   }
   
   public String verify_hondaTitle() {
		String actual = driver.getTitle();
		return actual;
	}
 

  public void view_More_Bikes() throws InterruptedException {
		
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", viewMoreBikes);
		
		//viewMoreBikes.click();
		
		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
	    waits.until(ExpectedConditions.elementToBeClickable(viewMoreBikes));
		
		try{
			  executor =(JavascriptExecutor) driver;
			  executor.executeScript("arguments[0].click();",	viewMoreBikes);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		
	}
  public List<WebElement> displayNameList() {

  		return BikeName;

  	}


  	public List<WebElement> displayPriceList(){

  		return BikePrice;

  	}


  	public List<WebElement> displayLaunchList(){

  		return BikeLaunchDate;

  	}
  
  List<WebElement> bikeNames = displayNameList();
	List<WebElement> bikePrices = displayPriceList();
	List<WebElement> bikeLaunchDates = displayLaunchList();

	
	public Map<String,String[]>getdetails() {
	for(int i=0 ; i< bikeNames.size(); i++) {
		String Name = bikeNames.get(i).getText();
		bikeNamesTxt.add(Name);

		String Price = bikePrices.get(i).getText();
		bikePricesTxt.add(Price);
		String Date = bikeLaunchDates.get(i).getText();
		bikeLaunchDatesTxt.add(Date);
		//Split the string where we find space
		String[] bikePrice_ = Price.split(" ");

		double bikePriceD = 0;  // use for more precision than float
		int bikePrice1 = 0;
		//check if in bikePrice array at first index contains . or not if present then go
		if(bikePrice_[1].contains(".")) {
			bikePriceD = Double.parseDouble(bikePrice_[1]);      //convert string to double
		}
		else {
			String a = bikePrice_[1].replace(",", "");
			bikePrice1 = Integer.parseInt(a);
		}
		if(bikePriceD<4.0) {
			bikeUnder4Lac.put(Name, new String[] {Price,Date});
		}
	}


	for(Map.Entry<String, String[]> e :bikeUnder4Lac.entrySet()) {
		System.out.println(e.getKey()+":"+e.getValue()[0]+" "+e.getValue()[1]);
	}


	return bikeUnder4Lac;
}
	

}
