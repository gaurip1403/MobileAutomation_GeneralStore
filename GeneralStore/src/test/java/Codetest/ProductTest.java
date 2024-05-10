package Codetest;

import org.testng.annotations.Test;

public class ProductTest extends BaseTest{
	
	@Test
	public void AddToCart() throws Exception {
		
		AddProducts();
		screenshot("Products");
		
	}
}


