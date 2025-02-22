package com.finance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StockPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By stockPriceLocator = By.xpath("//span[@data-testid='qsp-price']");
    private By previousCloseLocator = By.xpath("//span[contains(text(), 'Previous Close')]/following-sibling::span");
    private By volumeLocator = By.xpath("//span[text()='Volume']/following-sibling::span");

    public StockPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public double getStockPrice() {
        WebElement stockPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(stockPriceLocator));
        return Double.parseDouble(stockPriceElement.getText().replaceAll(",", ""));
    }

    public String getPreviousClose() {
        WebElement previousCloseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(previousCloseLocator));
        return previousCloseElement.getText();
    }

    public String getVolume() {
        WebElement volumeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(volumeLocator));
        return volumeElement.getText();
    }
}
