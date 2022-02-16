package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By header = By.cssSelector("div#logo a");
	private By logout = By.linkText("Logout");
	private By sections = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String getAccountsPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}
	
	public String getAccountsPageUrl() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}
	
	public String getAccountsPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public boolean isLogoutLinkExists() {
		return eleUtil.doIsDisplayed(logout);
	}
	
	
	public boolean logout() {
		if (isLogoutLinkExists()) {
			eleUtil.doClick(logout);
			return true;
		}
		return false;
	}
	
	public List<String> getAccountsPageSections() {
		List<WebElement> secList = eleUtil.waitForElementsVisible(sections, Constants.DEFAULT_TIMEOUT);
		List<String> secValList = new ArrayList<String>();
		
		for(WebElement e: secList) {
			secValList.add(e.getText());
		}
		
		return secValList;
	}
	
	public boolean searchExists() {
		return eleUtil.doIsDisplayed(search);
	}
	
	
	public ResultsPage doSearch(String prodName) {
		if (searchExists()) {
			eleUtil.doSendKeys(search, prodName);
			eleUtil.doClick(searchIcon);
			
		}
		return new ResultsPage(driver);
	}
}
