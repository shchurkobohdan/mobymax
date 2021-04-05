package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static java.lang.Boolean.parseBoolean;
import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
public class WebDriverFactory {
	private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
	private static String browser = System.getProperty("browser");
	private static String remoteExecution = System.getProperty("remote");

	public static WebDriver getDriver() {
		if (!Optional.ofNullable(DRIVER_THREAD_LOCAL.get()).isPresent()) {
			DRIVER_THREAD_LOCAL.set(createWebDriverInstance(browser, remoteExecution));
		}
		return DRIVER_THREAD_LOCAL.get();
	}

	public static WebDriver createWebDriverInstance(String browser, @org.testng.annotations.Optional("false") String remote) {
		log.info("Create {} webdriver", browser.toUpperCase());
		WebDriver driver;
		boolean isRemote = parseBoolean(remote);

		if (!isRemote) {
			switch (browser) {
				case "chrome": {
					WebDriverManager.chromedriver().setup();
					ChromeOptions chromeOptions = new ChromeOptions();
					driver = new ChromeDriver(chromeOptions);
					configureDriver(driver);
					break;
				}
				case "firefox": {
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					driver = new FirefoxDriver(firefoxOptions);
					configureDriver(driver);
					break;
				}
				default:
					throw new IllegalArgumentException("incorrect browser name");
			}
		} else {
			driver = getRemoteDriver();
			configureDriver(driver);
		}
		return driver;
	}

	private static RemoteWebDriver getRemoteDriver() {
		log.debug("Create Remote webdriver");
		RemoteWebDriver driver;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(browser);
		try {
			driver = new RemoteWebDriver(new URL("http://172.30.0.2:4444/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			throw new IllegalStateException("Failed to instantiate the remote driver");
		}
		return driver;
	}

	public static void closeWebDriverInstance() {
		log.info("Close webdriver instance");
		WebDriver driver = DRIVER_THREAD_LOCAL.get();
		if (driver != null) {
			driver.quit();
			DRIVER_THREAD_LOCAL.remove();
		}
	}

	private static void configureDriver(WebDriver webDriver) {
		webDriver.manage().timeouts().implicitlyWait(5, SECONDS);
		webDriver.manage().window().maximize();
	}
}
