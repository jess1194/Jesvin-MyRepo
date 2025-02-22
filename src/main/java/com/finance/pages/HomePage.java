package com.finance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;

	By searchBox = By.id("ybar-sbq");
	By autoSuggestList = By.xpath("//ul[contains(@class, 'modules-module_list__hi5kT')]/li");
	By firstEntryName = By.xpath("//*[@id=\"ybar-sf\"]/descendant::div[contains(@class,'modules-module_quoteCompanyName__JVZCM modules-module_Ell__N1WPm')][1]");


	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void searchStock(String stockSymbol) {
		WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
		searchInput.sendKeys(stockSymbol);
	}

	public String verifyAutoSuggest() {
		String firstEntry = driver.findElement(firstEntryName).getText();
		System.out.println("First entry is "+firstEntry);
		return firstEntry;
	}

	public StockPage selectFirstSuggestion() {
		WebElement firstEntry = wait.until(ExpectedConditions.elementToBeClickable(firstEntryName));
		firstEntry.click();
		return new StockPage(driver);


	}
}
