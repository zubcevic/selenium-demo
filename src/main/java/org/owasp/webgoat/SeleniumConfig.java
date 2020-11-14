package org.owasp.webgoat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeleniumConfig {

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriver driver;
	
	public SeleniumConfig(boolean headless) {
	    
	    
	    FirefoxBinary firefoxBinary = new FirefoxBinary();
        if (headless) {
        	firefoxBinary.addCommandLineOptions("--headless");
        }

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        driver = new FirefoxDriver(firefoxOptions);
	    
	}
	
}
