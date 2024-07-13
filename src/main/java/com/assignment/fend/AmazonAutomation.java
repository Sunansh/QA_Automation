package com.assignment.fend;
public class AmazonAutomation
  {
    public static void main(String[] args)
    {
       System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to Amazon.in
            driver.get("https://www.amazon.in");

            // Search for the product
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("Titan watch");
            searchBox.submit();

            // Wait for results to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-price-whole")));

            // Capture product details
            WebElement firstProduct = driver.findElement(By.cssSelector("div.s-main-slot div.s-result-item"));
            String productName = firstProduct.findElement(By.cssSelector("h2 a span")).getText();
            String productPrice = firstProduct.findElement(By.cssSelector("span.a-price-whole")).getText();
            String productLink = firstProduct.findElement(By.cssSelector("h2 a")).getAttribute("href");

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
