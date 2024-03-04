package testCases;


import org.testng.annotations.Test;

import testBase.BaseClass;
import POM.UsedCars_2;

public class TC_2_UsedCars extends BaseClass{
	
	@Test(priority=10,groups= {"sanity","master"})
	public void visibilityOfUsedCarsDropdown() {
		uc = new UsedCars_2(driver);
		 logger.info("***Starting TC_2_UsedCars****");
		 uc.used_Cars_Dropdown_Visible();
		 logger.info("Checking visibility of used cars dropdown");
		
	}
	UsedCars_2 uc;
	@Test(priority=11,groups= {"sanity","master"})
	 public void usedCarsDropdown() throws InterruptedException {
		 uc.move_To_Dropdown();
		 logger.info("Move to used cars dropdown");
	 }
    
	 @Test(priority=12,groups= {"sanity","master"})
	 public void selectChennaiUsedCars() {
		 
		 uc.select_Chennai_Used_Cars();
		 logger.info("Click on Chennai city");
	 }
	 
	 @Test(priority=13,groups= {"regression","master"})
	 public void popularModel() {
		 uc.popular_Model(); 
		 logger.info("Extracting all the popular models");
		 logger.info("***Finished TC_2_UsedCars****");
	 }

}
