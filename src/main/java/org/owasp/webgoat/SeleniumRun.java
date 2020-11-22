package org.owasp.webgoat;

public class SeleniumRun {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	static {
		if (null == System.getProperty("webdriver.gecko.driver")) {
			if (OS.indexOf("win")>-1) {
				System.setProperty("webdriver.gecko.driver","c:/tools/geckodriver.exe");
			} else {
				System.setProperty("webdriver.gecko.driver","/Applications/Firefox.app/Contents/MacOS/geckodriver");
			}
		}
	}

    public static void main(String[] args) throws Exception {
    	
    	WebGoatSQLIntro sqlRun = new WebGoatSQLIntro(false);

    	sqlRun.login("tester1", "password");
    	sqlRun.exercise();
    	sqlRun.closeWindow();
    }
}
