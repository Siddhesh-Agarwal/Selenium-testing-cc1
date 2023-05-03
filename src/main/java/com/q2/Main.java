package com.q2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
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
        
        // Verify that user is able to apply filter name wise from A-Z and Z-A
        try {
            driver.findElement(By.className("product_sort_container")).click();
            driver.findElement(By.cssSelector("option[value='za']")).click();
            driver.findElement(By.className("product_sort_container")).click();
            driver.findElement(By.cssSelector("option[value='az']")).click();
            System.out.println("-> Filter name wise from A-Z and Z-A is applied");
            Thread.sleep(2000); // wait for 2 seconds
        } catch (Exception e) {
            System.out.println("-> Filter name wise from A-Z and Z-A is not applied");
        }
        
        // Print the first product name while filtering from A-Z and print 1st product name while filtering from Z-A
        System.out.println("-> First product name while filtering from A-Z: " + driver.findElement(By.className("inventory_item_name")).getText());
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='za']")).click();
        System.out.println("-> First product name while filtering from Z-A: " + driver.findElement(By.className("inventory_item_name")).getText());
        Thread.sleep(2000); // wait for 2 seconds
        
        // Verify the user is able to apply filter from low price to high price and high price to low price
        try {
            driver.findElement(By.className("product_sort_container")).click();
            driver.findElement(By.cssSelector("option[value='lohi']")).click();
            driver.findElement(By.className("product_sort_container")).click();
            driver.findElement(By.cssSelector("option[value='hilo']")).click();
            System.out.println("-> Filter from low price to high price and high price to low price is applied");
            Thread.sleep(2000); // wait for 2 seconds
        } catch (Exception e) {
            System.out.println("-> Filter from low price to high price and high price to low price is not applied");
        }


        // Print the 1St product price while filtering from high to low and print 1St product price while filtering from low to high
        System.out.println("-> First product price while filtering from high to low: " + driver.findElement(By.className("inventory_item_price")).getText());
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='lohi']")).click();
        System.out.println("-> First product price while filtering from low to high: " + driver.findElement(By.className("inventory_item_price")).getText());
        
        // Close the driver
        driver.close();
    }
}
