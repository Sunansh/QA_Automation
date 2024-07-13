package com.assignment.fend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonAutomation
  {
    public static void main(String[] args)
    {
       System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();
      //Navigate to Amazon
        driver.get("https://www.amazon.in/");

            // Search for the product
            WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
            search.sendKeys("Puma Shoes");
            search.submit();

            // Wait for results to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/span/div/div/div[2]/div/span/a/div/img")));

            // Capture product details
            WebElement firstProduct = driver.findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/span/div/div/div[3]/div[2]/div/h2/span"));
            String productName = firstProduct.findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/span/div/div/div[3]/div[2]/h2/a/span")).getText();
            String productPrice = firstProduct.findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/span/div/div/div[3]/div[4]/div[2]/div[1]/a/span/span[2]/span[2]")).getText();
            String productLink = firstProduct.findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/span/div/div/div[3]/div[2]/h2/a/span")).getAttribute("href");

            System.out.println("Product Name: " + productName);
            System.out.println("Product Price: " + productPrice);
            System.out.println("Product Link: " + productLink);

            // Click on the first product
            firstProduct.findElement(By.cssSelector("h2 a")).click();

            // Navigate to Add to Cart
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();

            // Navigate to the Buy Now Screen
            wait.until(ExpectedConditions.elementToBeClickable(By.id("hlb-view-cart-announce"))).click();

            // Optionally proceed to payment (not performing the actual purchase)
            // The following line would typically take you to the payment gateway if the cart is open
            // driver.findElement(By.id("checkout-button")).click(); // Uncomment if needed

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
