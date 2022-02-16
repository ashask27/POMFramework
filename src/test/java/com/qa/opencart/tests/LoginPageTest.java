package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.utills.Constants;

@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {	
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageURLTest() {
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}	
	
	@Test
	public void loginPageForgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkDisplayed());
	}
	
	@Test
	public void loginTest() {
		String email = prop.getProperty("username").trim();
		String pwd = prop.getProperty("password").trim();
		accountPage = loginPage.doLogin(email, pwd);
		Assert.assertTrue(accountPage.isLogoutLinkExists());
	}
}
