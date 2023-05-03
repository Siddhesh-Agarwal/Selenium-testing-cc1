package com.q1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

class Main {
    public static void main(String[] args) throws InterruptedException {
        // Setup the driver
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        // Open the website
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        
        // get elements by id 'user-name' and password and fill values
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000); // wait for 2 seconds
        
        // Verify that user is able to add a product to the cart Add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click(); // add to cart
        driver.findElement(By.id("shopping_cart_container")).click(); // click on cart
        if (driver.findElement(By.className("cart_item")) != null) {
            System.out.println("-> Product is added to cart");
        } else {
            System.out.println("-> Product is not added to cart");
            
        }
        Thread.sleep(2000); // wait for 2 seconds
        
        // Verify that user can view the contents in the cart
        if (driver.findElement(By.className("cart_item")).isDisplayed()) {
            System.out.println("-> Product is displayed in cart");
        } else {
            System.out.println("-> Product is not displayed in cart");
        }
        Thread.sleep(2000); // wait for 2 seconds
        
        // Verify that once user clicks on checkout button "Your information" form
        // should be displayed
        driver.findElement(By.id("checkout")).click();
        if (driver.findElement(By.className("checkout_info")).isDisplayed()) {
            System.out.println("-> Checkout form is displayed");
        } else {
            System.out.println("-> Checkout form is not displayed");
        }
        Thread.sleep(2000); // wait for 2 seconds
        
        // Verify that once user fills the form and clicks on continue button entire
        // product overview should be displayed
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("123456");
        driver.findElement(By.id("continue")).click();
        if (driver.findElement(By.className("cart_item")).isDisplayed()) {
            System.out.println("-> Product overview is displayed");
        } else {
            System.out.println("-> Product overview is not displayed");
        }
        Thread.sleep(2000); // wait for 2 seconds
        
        // Print the name of the product and the price of the product
        System.out.println("-> Product name: " + driver.findElement(By.className("inventory_item_name")).getText());
        System.out.println("-> Product price: " + driver.findElement(By.className("inventory_item_price")).getText());
        Thread.sleep(2000); // wait for 2 seconds
        
        // Assert the title of the page is 'Swag Labs'
        if (driver.getTitle().equals("Swag Labs")) {
            System.out.println("-> Title is correct");
        } else {
            System.out.println("-> Title is incorrect");
        }
        Thread.sleep(2000); // wait for 2 seconds

        // Assert the URL of the page is 'https://www.saucedemo.com/checkout-step-two.html'
        if (driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html")) {
            System.out.println("-> URL is correct");
        } else {
            System.out.println("-> URL is incorrect");
        }
        
        driver.close();
    }
}
