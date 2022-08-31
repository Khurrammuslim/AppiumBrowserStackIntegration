package ParallelTesting;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.net.MalformedURLException;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;	
import org.openqa.selenium.remote.DesiredCapabilities;

class TestClass1 implements Runnable {
	public void run() {
		DesiredCapabilities caps = new DesiredCapabilities();
		// Set URL of the application under test
		caps.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");
		// Specify device and os_version for testing
		caps.setCapability("device", "Google Pixel 3");
		caps.setCapability("os_version", "9.0");
		// Set other BrowserStack capabilities
		caps.setCapability("project", "First Java Project");
		caps.setCapability("build", "Java Android");
		caps.setCapability("name", "first_test");
		ParallelTestingAndroid r1 = new ParallelTestingAndroid();
		try {
			r1.executeTest(caps);
		} catch (InterruptedException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
class TestClass2 implements Runnable {
	public void run() {
		DesiredCapabilities caps = new DesiredCapabilities();
		// Set URL of the application under test
		caps.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");
		// Specify device and os_version for testing
		caps.setCapability("device", "Samsung Galaxy S10e");
		caps.setCapability("os_version", "9.0");
		// Set other BrowserStack capabilities
		caps.setCapability("project", "First Java Project");
		caps.setCapability("build", "Java Android");
		caps.setCapability("name", "first_test");
		ParallelTestingAndroid r1 = new ParallelTestingAndroid();
		try {
			r1.executeTest(caps);
		} catch (InterruptedException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
public class ParallelTestingAndroid {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		Thread object1 = new Thread(new TestClass1());
		object1.start();
		Thread object2 = new Thread(new TestClass2());
		object2.start();
	}
	public void executeTest(DesiredCapabilities caps) throws InterruptedException, MalformedURLException {
		// Set your access credentials
		caps.setCapability("browserstack.user", "khurrammuslim_91zkl5");
		caps.setCapability("browserstack.key", "9ev4xKTzT1J5nyBBHPhv");
		// Initialise the remote Webdriver using BrowserStack remote URL
		// and desired capabilities defined above
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
				new URL("http://hub.browserstack.com/wd/hub"), caps);
		// Test case for the BrowserStack sample Android app.
		// If you have uploaded your app, update the test case here.
		AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
				ExpectedConditions.elementToBeClickable(
						MobileBy.AccessibilityId("Search Wikipedia")));
		searchElement.click();
		AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
				ExpectedConditions.elementToBeClickable(
						MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
		insertTextElement.sendKeys("BrowserStack");
		Thread.sleep(5000);
		List<AndroidElement> allProductsName = driver.findElementsByClassName(
				"android.widget.TextView");
		assert(allProductsName.size() > 0);
		// Invoke driver.quit() after the test is done to indicate that the test is completed.
		driver.quit();
	}
}