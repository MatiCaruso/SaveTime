package nordic.testing.days;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SharedDriver {

    protected static ThreadLocal<RemoteWebDriver> REAL_DRIVER = null;

    public static ThreadLocal<RemoteWebDriver> createDriver() {
        try {
            REAL_DRIVER = new ThreadLocal<>();
            ChromeOptions capabilities = new ChromeOptions();
            capabilities.setAcceptInsecureCerts(true);

            /**
             *  Set your grid ip:port
             *  take in consideration that the ip could be virtualize by docker
             */

            String local_grid = "http://localhost:4444/wd/hub";
            REAL_DRIVER.set(new RemoteWebDriver(new URL(local_grid), capabilities));
            REAL_DRIVER.get().manage().window().maximize();
        } catch ( MalformedURLException e) {
            e.printStackTrace();
        }
        return  REAL_DRIVER;
    }

    public static void setDriver(RemoteWebDriver driver){
        REAL_DRIVER.set(driver);
    }

    public static void closeDriver(ThreadLocal<RemoteWebDriver> driver) {
        if (driver.get() != null) {
            driver.get().close();
        }
    }

    public static void removeThread(ThreadLocal<RemoteWebDriver> driver) {
        driver.remove();
    }

    public static RemoteWebDriver getDriver() {
        return REAL_DRIVER.get();
    }

}
