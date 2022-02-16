package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utills.Constants;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void setUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	
	@Test
	public void productHeaderTest() {
		resultsPage = accountPage.doSearch("MacBook");
		prodInfoPage = resultsPage.selectProduct("MacBook Pro");
		
		Assert.assertEquals(prodInfoPage.getProductHeader(), "MacBook Pro");
	}

	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", Constants.MACBOOKPRO_IMAGES_COUNT},
			{"MacBook", "MacBook Air", Constants.MACBOOKAIR_IMAGES_COUNT},
			{"iMac", "iMac", Constants.IMAC_IMAGES_COUNT}
		};
	}

	@Test (dataProvider="productData")
	public void productImageCountTest(String searcProd, String selectProd, int imageCount) {
		resultsPage = accountPage.doSearch(searcProd);
		prodInfoPage = resultsPage.selectProduct(selectProd);
		int count = prodInfoPage.getProductImageCount();
		System.out.println("total images " + count);
		Assert.assertEquals(count, imageCount);
	}
	
	
	//HashMap - order not maintained
//	Brand:Apple
//	Availability:Out Of Stock
//	ExTaxPrice:Ex Tax: $2,000.00
//	totalimages:4
//	price:$2,000.00
//	name:MacBook Pro
//	Product Code:Product 18
//	Reward Points:800
	
	//LinkedHashMap - use it if you want to maintain the order
//	name:MacBook Pro
//	totalimages:4
//	Brand:Apple
//	Product Code:Product 18
//	Reward Points:800
//	Availability:Out Of Stock
//	price:$2,000.00
//	ExTaxPrice:Ex Tax: $2,000.00
	
	//TreeMap - It stores it in alphabetical order. Use ut if you want the key in sorted order
//	Availability:Out Of Stock
//	Brand:Apple
//	ExTaxPrice:Ex Tax: $2,000.00
//	Product Code:Product 18
//	Reward Points:800
//	name:MacBook Pro
//	price:$2,000.00
//	totalimages:4
	@Test
	public void productDataTest() {
		resultsPage = accountPage.doSearch("Macbook");
		prodInfoPage = resultsPage.selectProduct("MacBook Pro");
		
		Map<String, String> actProductInfoMap = prodInfoPage.getProductInfo();
		
		actProductInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}
	

}

