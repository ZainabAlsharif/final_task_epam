package hooks;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browserName = ThreadLocal.withInitial(() -> "chrome");

    public static void setBrowser(String browser) {
        browserName.set(browser == null ? "chrome" : browser);
    }

    public static void clearBrowser() {
        browserName.remove();
    }

    @Before
    public void setUp() {
        String browser = browserName.get();
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            //ignores Change your password popup
            options.addArguments("--disable-features=PasswordLeakDetection");
            driver.set(new ChromeDriver(options));
        } else {
            driver.set(new FirefoxDriver());
        }

        getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @After
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}