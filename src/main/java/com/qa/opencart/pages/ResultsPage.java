package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utills.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchHeader = By.cssSelector("div#content h1");
	//private By searchResults = By.xpath("//div[@class='product-thumb']");
	private By searchResults = By.cssSelector("div.caption a");
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	
	public int getProductListCount() {
		int total = eleUtil.waitForElementsVisible(searchResults, 10).size();
		System.out.println("Total number of products " + total);
		return total;
	}
	
	
	public ProductInfoPage selectProduct(String prodname) {
		List<WebElement> prodList = eleUtil.waitForElementsVisible(searchResults, 10);
		for(WebElement e: prodList) {
			String text = e.getText();
			if (text.equals(prodname)) {
				e.click(); 	 
				break;
			}	
		}
		return new ProductInfoPage(driver);
	}
}
