package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ElementUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By passwordConfirm = By.id("input-confirm");
	private By privacyCheckbox = By.xpath("//input[@name=\"agree\"]");
	private By continueBtn = By.xpath("//input[@value=\"Continue\"]"); 
	private By header = By.cssSelector("div#content h1");
	
	private By subscribeYes = By.xpath("//label[@class=\"radio-inline\"][position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[@class=\"radio-inline\"][position()=2]/input[@type='radio']");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	private By successMsg = By.cssSelector("div#content h1");
	
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public boolean accountRegistration(String firstname, String lastname, String inEmail, String inTelephone, String inPassword,
					String subscribe) {
		eleUtil.doSendKeys(firstName, firstname);
		eleUtil.doSendKeys(lastName, lastname);
		eleUtil.doSendKeys(email, inEmail);
		eleUtil.doSendKeys(telephone, inTelephone);
		eleUtil.doSendKeys(password, inPassword);
		eleUtil.doSendKeys(passwordConfirm, inPassword);
		eleUtil.doClick(privacyCheckbox);
		
		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);	
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(continueBtn);
		
		String msg = eleUtil.doGetText(successMsg);
		
		if (msg.contains(Constants.REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
	
	
	public String getRegPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.REGISTRATION_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}
	
	public String getRegPageUrl() {
		return eleUtil.waitForUrlContains(Constants.REGISTRATION_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}
	
	
	public String getRegPageHeader() {
		return eleUtil.doGetText(header);
	}
	
}
