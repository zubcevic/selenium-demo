package org.owasp.webgoat;

public class SeleniumRun {
	static {
		if (null == System.getProperty("webdriver.gecko.driver")) {
			System.setProperty("webdriver.gecko.driver","/Applications/Firefox.app/Contents/MacOS/geckodriver");
		}
	}

    public static void main(String[] args) {
    	
    	WebGoatSQLIntro sqlRun = new WebGoatSQLIntro(false);

    	sqlRun.login("tester1", "password");
    	sqlRun.exercise();
    	sqlRun.closeWindow();
    }
}
