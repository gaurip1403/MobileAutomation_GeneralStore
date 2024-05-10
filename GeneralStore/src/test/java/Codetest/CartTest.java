package Codetest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CartTest extends BaseTest{
	
	@Test
	public void AddToCart() throws Exception {
		
		AddProducts();
		
		List<WebElement> productPrice = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
		int count = productPrice.size();
		double total =0;
		for (int i=0;i<count;i++)
		{
			String amount = productPrice.get(i).getText();
			Double price = Double.parseDouble(amount.substring(1));
		
			total = total + price;
		}
		
		String cartTotal = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		Double cartAmount = Double.parseDouble(cartTotal.substring(1));
		Assert.assertEquals(total, cartAmount);
		
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		screenshot("Finalpage");
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		WebElement search = driver.findElement(By.className("android.widget.EditText"));
		search.click();
		search.sendKeys("General Store");
		
		 driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
		 driver.navigate().back();
	}

}
