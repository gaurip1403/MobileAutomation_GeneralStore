package Codetest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void setupAppium() throws MalformedURLException {
		
		  AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File(
		  "C:\\Users\\pawar\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withIPAddress("127.0.0.1").usingPort(4723).build(); 
		service.start();
		 
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("adb-ZD222DLYX9-0OpKb4._adb-tls-connect._tcp");
		options.setApp("C:\\Users\\pawar\\eclipse-workspace\\GeneralStore\\src\\test\\java\\resource\\General-Store.apk");

		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		this.driver = driver;
	}
	
	
	public void screenshot(String filename) throws Exception 
	{
		File file1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file1, new File(filename+".png"));
	}
	
	
	public void ShopTest() {
		driver.findElement(AppiumBy.className("android.widget.Spinner")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")).sendKeys("Gauri");
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
	}
	
	public void AddProducts() {
		ShopTest();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(4)"));
		
		int count = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0;i<count;i++) 
		{
			String product = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			
			if(product.equalsIgnoreCase("PG 3")) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}	
			if(product.equalsIgnoreCase("Nike SFB Jungle")) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	}


}
