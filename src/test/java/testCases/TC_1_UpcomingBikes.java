package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;
import utilities.WriteToExcel;
import POM.UpcomingBikes_1;

public class TC_1_UpcomingBikes extends BaseClass{
	UpcomingBikes_1 up;
	
	
	
	@Test(priority=1,groups= {"sanity","master"})
	public void verifyHomeTitle() {
		logger.info("***Starting TC_1_UpcomingBikes");
		up = new UpcomingBikes_1(driver);
		logger.info("*********verifing Home Title ***********");
		String actual = up.homeTitle();
		logger.info("got the page title");
		String expected = prop.getProperty("HomePageTitle");
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test(priority=2,groups= {"sanity","master"})
	public void newBikesDropdown() throws InterruptedException, ParseException{
		
		up.new_Bikes_Dropdown(); 
		logger.info("Move to New Bikes Dropdown");
		}
	
	@Test (priority=3,groups= {"sanity","master"})
	public void verifyUpcomingBikeOption() {
		logger.info("***********verifing Upcoming Bike Option ***********");
		boolean actual = up.verify_upcomingBikes();
		logger.info("Storing boolean value in actual");
		assertEquals(actual, true);
		logger.info("Verified upcoming bikes options");
	}
	
	@Test(priority=4,groups= {"regression","master"})
	public void upcomingBikes() {
		up.selecting_Upcoming_Bikes();
		logger.info("Click on Upcoming Bikes");
	}
      
	
	@Test (priority=5,groups= {"sanity","master"})
	public void verifyUpcomingBikePageUrl() {
		
		logger.info("***********verifing Upcoming Bike Page url ***********");
		String actual = up.upcomingBikePageUrl();
		String expected = prop.getProperty("UpcomingBikePageUrl");
		Assert.assertEquals(actual, expected);
		logger.info("Verified Upcoming Bike Page url");
		
	}
	@Test(priority=6,groups= {"regression","master"})
	public void manufacturersDropdown() {
		up.manufacturers_Dropdown();
		logger.info("From manufacturers dropdown select 'Honda'");
	}
	
	@Test (priority=7,groups= {"sanity","master"})
	public void verifyHondaSelected() {
		logger.info("***********verify Honda title ***********");
		
		String actual = up.verify_hondaTitle();
		String expected = prop.getProperty("HondaTitle");
		Assert.assertEquals(actual, expected);
		logger.info("Verified Honda page title");
		
		
	}
	
	@Test(priority=8,groups= {"regression","master"})
    public void viewMoreBikesclick() throws InterruptedException {
    	//up.read_More_Click();
    	up.view_More_Bikes();
    	logger.info("Click on ViewMore");
    }
    
	@Test(priority=9,groups= {"regression","master"})
    public void ModelsLessThan4lakh() throws IOException{
    	 
    	Map<String,String[]>x = up.getdetails();
    	logger.info("Get bike models, prices, and expected launch date for manufacturer 'Honda' & Bike price less than 4Lac");
    	WriteToExcel.writeToExcel(x);
	
    	logger.info("***Finished TC_1_UpcomingBikes");
    	
    }

}
