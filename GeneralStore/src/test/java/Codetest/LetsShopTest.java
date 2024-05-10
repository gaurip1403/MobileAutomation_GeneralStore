package Codetest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LetsShopTest extends BaseTest {

	@Test
	public void FirstPage() throws Exception {
	
		ShopTest();
		
		screenshot("Formpage");
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		String pageTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		
		Assert.assertEquals(pageTitle, "Products");
		
	}
	
}
