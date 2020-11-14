package org.owasp.webgoat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SeleniumTest {
	
	static {
		if (null == System.getProperty("webdriver.gecko.driver")) {
			System.setProperty("webdriver.gecko.driver","/Applications/Firefox.app/Contents/MacOS/geckodriver");
		}
	}
	
	private static WebGoatSQLIntro sqlRun;

    @BeforeAll
    public static void setUp() {
    	sqlRun = new WebGoatSQLIntro(true);
    	sqlRun.login("tester1", "password");
    }
    
    @AfterAll
    public static void tearDown() {
    	sqlRun.closeWindow();
    }
    
    @Test
    public void checkTitle() {
        assertEquals("WebGoat", sqlRun.getTitle());
    }
    
    @Test
    public void checkExercise() {
    	sqlRun.exercise();
    }

}
