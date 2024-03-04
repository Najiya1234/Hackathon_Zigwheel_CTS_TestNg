package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;
import POM.GoogleSignin_3;

public class TC_3_GoogleSignin extends BaseClass{
	GoogleSignin_3 signin;
	
	@Test(priority=14,groups= {"sanity","master"})
	 public void clickForum() {
		 
		 signin = new GoogleSignin_3(driver);
		 logger.info("***Starting TC_3_GoogleSignin***");
		 
		 signin.click_Forum();
		 logger.info("Click on Forum");
	 }
	
	
	@Test (priority=15,groups= {"sanity","master"})
	public void verifyForumPageTitle() {
		
		logger.info("***********verifing Forum Page Title ***********");
		String actual = signin.Forum_Page_Title();
		String expected = prop.getProperty("ForumPageTitle");
		Assert.assertEquals(actual, expected);
		logger.info("Verified Forum Page title");
		
		
	}
	 
	 @Test(priority=16,groups= {"sanity","master"})
	 public void clickSignup() {
		 signin.click_Signup();
		 logger.info("Click on signup");
	 }
	
	 @Test(priority=17,groups= {"sanity","master"})
	 public void  clickGoogle() {
		 signin.click_Google();
		 logger.info("click on Google");
	 }
	
	 @Test(priority=18,groups= {"regression","master"})
	public void signinWithEmail() {
		signin.signin_With_Email();
		logger.info("Signin with wrong Email account deatils");
		 
	}
	
	 @Test(priority=19,groups= {"regression","master"})
	public void emailNextButton() {
		 signin.email_NextButton_Click();
		 logger.info("Click on Next button");
	} 
	
	 @Test(priority=20,groups= {"regression","master"})
	public void ErrorMessage() {
		signin.get_Error_Message();
		logger.info("Capture the error message");
		logger.info("***Finished TC_3_GoogleSignin***");
	} 	

}
