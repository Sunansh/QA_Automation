package com.assignment.fend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class MyntraAutomation
  {
    public static void main(String[] args)
    {
       System.setProperty("webdriver.chrome.driver", "C:\\Users\\sunan\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();
      //Navigate to Amazon
        driver.get("https://www.myntra.com/");
	  driver.manage().window().maximize();

            // Search for the product
            WebElement search = driver.findElement(By.cssSelector("#desktop-header-cnt > div.desktop-bound > div.desktop-query > input"));
            search.sendKeys("Puma Shoes");
            search.submit();

            // Wait for results to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='17663246']/a/div[1]/div/div/div/picture/img")));

            // Capture product details
            WebElement firstProduct = driver.findElement(By.xpath("//*[@id='17663246']/a/div[2]"));
            String productName = firstProduct.findElement(By.xpath("//*[@id='17663246']/a/div[2]/h4[1]")).getText();
            String productPrice = firstProduct.findElement(By.xpath("//*[@id='17663246']/a/div[2]/div/span[1]/span[1]")).getText();
            String productLink = firstProduct.findElement(By.xpath("//*[@id='17663246']/a")).getAttribute("href");

            System.out.println("Product Name: " + productName);
            System.out.println("Product Price: " + productPrice);
            System.out.println("Product Link: " + productLink);

            // Click on the first product
            firstProduct.findElement(By.xpath("//*[@id='17663246']/a")).click();

            // Navigate to Add to Cart

wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mountRoot']/div/div[1]/main/div[2]/div[2]/div[2]/div[3]/div/div[1]"))).click();

            // Navigate to the Buy Now Screen
            wait.until(ExpectedConditions.elementToBeClickable(By.id("proceedToRetailCheckout"))).click();

            // Optionally proceed to payment (not performing the actual purchase)
            // The following line would typically take you to the payment gateway if the cart is open
            // driver.findElement(By.id("checkout-button")).click(); // Uncomment if needed

                    // Close the browser
            driver.quit();
        }
    }
