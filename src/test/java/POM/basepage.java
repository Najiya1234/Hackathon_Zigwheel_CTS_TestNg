package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class basepage {
	public WebDriver driver;
	 public basepage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }

}
