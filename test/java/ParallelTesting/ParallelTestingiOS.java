package ParallelTesting;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.net.MalformedURLException;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

class TestClass3 implements Runnable {
    public void run() {
        DesiredCapabilities caps = new DesiredCapabilities();
        // Set URL of the application under test
        caps.setCapability("app", "bs://444bd0308813ae0dc236f8cd461c02d3afa7901d");
        // Specify device and os_version for testing
        caps.setCapability("device", "iPhone 11 Pro");
        caps.setCapability("os_version", "13");
        // Set other BrowserStack capabilities
        caps.setCapability("project", "Parallel Testing iOS");
        caps.setCapability("build", "Java iOS");
        caps.setCapability("name", "first_test");
        ParallelTestingiOS r1 = new ParallelTestingiOS();
        try {
            r1.executeTest(caps);
        } catch (InterruptedException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
class TestClass4 implements Runnable {
    public void run() {
        DesiredCapabilities caps = new DesiredCapabilities();
        // Set URL of the application under test
        caps.setCapability("app", "bs://444bd0308813ae0dc236f8cd461c02d3afa7901d");
        // Specify device and os_version for testing
        caps.setCapability("device", "iPhone 13 Pro Max");
        caps.setCapability("os_version", "15");
        // Set other BrowserStack capabilities
        caps.setCapability("project", "Parallel Testing iOS");
        caps.setCapability("build", "Java iOS");
        caps.setCapability("name", "second_test");
        ParallelTestingiOS r1 = new ParallelTestingiOS();
        try {
            r1.executeTest(caps);
        } catch (InterruptedException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

public class ParallelTestingiOS {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        Thread object3 = new Thread(new TestClass3());
        object3.start();
        Thread object4 = new Thread(new TestClass4());
        object4.start();
    }
    public void executeTest(DesiredCapabilities caps) throws InterruptedException, MalformedURLException {
        // Set your access credentials
        caps.setCapability("browserstack.user", "khurrammuslim_91zkl5");
        caps.setCapability("browserstack.key", "9ev4xKTzT1J5nyBBHPhv");
        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(
                new URL("http://hub-cloud.browserstack.com/wd/hub"), caps);
        // Test case for the BrowserStack sample iOS app.
        // If you have uploaded your app, update the test case here.
        IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
        textButton.click();
        IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
        textInput.sendKeys("hello@browserstack.com");
        Thread.sleep(5000);
        IOSElement textOutput = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Output")));
        if(textOutput != null && textOutput.getText().equals("hello@browserstack.com"))
            assert(true);
        else
            assert(false);
        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
    }
}