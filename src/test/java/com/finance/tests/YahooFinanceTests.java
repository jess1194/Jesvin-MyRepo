package com.finance.tests;

import com.finance.pages.HomePage;
import com.finance.pages.StockPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class YahooFinanceTests {
	public static WebDriver driver;
	HomePage homePage;
	StockPage stockPage;
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\jesvi\\eclipse-workspace\\YahooFinanceCodeTest\\src\\test\\resources\\Browser Drivers");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://finance.yahoo.com/");
		homePage = new HomePage(driver);
	}

	@Test(priority = 1)
	public void testSearchTesla() {
		homePage.searchStock("TSLA");
		softAssert.assertEquals(homePage.verifyAutoSuggest(),"Tesla, Inc.", "Autosuggest verification failed!");
		stockPage = homePage.selectFirstSuggestion();
	}

	@Test(priority = 2, dependsOnMethods = "testSearchTesla")
	public void testStockPrice() {
		double stockPrice = stockPage.getStockPrice();
		System.out.println("Stock Price: " + stockPrice);
		softAssert.assertTrue(stockPrice > 200, "Stock price is below $200!");
	}

	@Test(priority = 3, dependsOnMethods = "testStockPrice")
	public void testCaptureAdditionalData() {
		System.out.println("Previous Close: " + stockPage.getPreviousClose());
		System.out.println("Volume: " + stockPage.getVolume());
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}

