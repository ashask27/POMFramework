package com.qa.opencart.tests;

import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ExcelUtil;

public class RegistrationPageTest extends BaseTest{

	public String getRandomNumber() {
		Random randomGen = new Random();
		String email = "testautomation-feb"+randomGen.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@BeforeClass
	public void regPageSetUp() {
		registerPage = loginPage.doRegister();
	}
	
	@Test
	public void regPageTitleTest() {
		String title = registerPage.getRegPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.REGISTRATION_PAGE_TITLE);
	}
	
	@Test
	public void regPageHeaderTest() {
		String header = registerPage.getRegPageHeader();
		System.out.println(header);
		Assert.assertEquals(header, Constants.REGISTRATION_PAGE_HEADER);
	}
	
	
	@Test
	public void regPageLinkTest() {
		String link = registerPage.getRegPageUrl();
		System.out.println(link);	
		Assert.assertTrue(link.contains(Constants.REGISTRATION_PAGE_URL_FRACTION));
	}


	@Test (dataProvider="getRegisterDataFromExcel")
	public void registerAccountTest(String firstname, String lastname, String telephone, String password, String subscribe) {
		boolean accounRegistered = registerPage.accountRegistration(firstname, lastname,getRandomNumber(),telephone,password, subscribe);
		
		Assert.assertTrue(accounRegistered);
	}

	
	
	@DataProvider
	public Object[][] getRegisterData() {
		return new Object[][] {
				{"Sudha", "Murty", "23467656", "Sudha1234", "yes"}
		};
	}

	
	@DataProvider
	public Object[][] getRegisterDataFromExcel() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}

}
