package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utills.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	
	@Test
	public void accPageTitleTest() {
		String title = accountPage.getAccountsPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	
	@Test
	public void accPageUrlTest() {
		String url = accountPage.getAccountsPageUrl();
		System.out.println(url);
		Assert.assertTrue(url.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void accPageHeaderTest() {
		String header = accountPage.getAccountsPageHeader();
		System.out.println(header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
	}
	
	
	@Test
	public void logoutLinkTest() {
		Assert.assertTrue(accountPage.isLogoutLinkExists());
	}
	
	@Test
	public void searchExistsTest() {
		Assert.assertTrue(accountPage.searchExists());
	}
	
	@Test
	public void accPageSectionsTest() {
		List<String> list = accountPage.getAccountsPageSections();
		System.out.println(list);
		Assert.assertEquals(list, Constants.ACCOUNTS_PAGE_SECTION_LIST);
	}
	
	@DataProvider
	public Object[] productData() {
		return new Object[] { "Apple","iMac","MacBook"};	
	}
	
	@Test (dataProvider = "productData")
	public void searchProductTest(String prodName) {
		resultsPage = accountPage.doSearch(prodName);
		//Assert.assertNotEquals(resultsPage.getProductListCount(), 0);
		Assert.assertTrue(resultsPage.getProductListCount() > 0);
	}
	
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test (dataProvider = "productSelectData")
	public void selectProduct(String productName, String productHeader) {
		resultsPage = accountPage.doSearch(productName);
		Assert.assertTrue(resultsPage.getProductListCount() > 0);
		
		prodInfoPage = resultsPage.selectProduct(productHeader);
		Assert.assertEquals(prodInfoPage.getProductHeader(), productHeader);
	}
}
