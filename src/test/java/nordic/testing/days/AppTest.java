package nordic.testing.days;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class AppTest 
{
    ThreadLocal<RemoteWebDriver> driver = null;

    @BeforeMethod
    public void before() throws MalformedURLException {
        driver = SharedDriver.createDriver();
    }

    @AfterMethod
    public void after() {
        SharedDriver.setDriver(null);

    }

    @AfterClass
    public void tearDown() {
        SharedDriver.removeThread(driver);
    }

    @Test()
    public void test01() {
        driver.get().get("https://www.google.com");
        driver.get().get("https://www.wikipedia.com");
        SharedDriver.closeDriver(driver);
    }

    @Test()
    public void test02() {
        driver.get().get("https://www.facebook.com");
        driver.get().get("https://www.twitter.com");
        SharedDriver.closeDriver(driver);
    }

    @Test
    public void test03() {
        driver.get().get("https://www.github.com");
        SharedDriver.closeDriver(driver);
    }
}
