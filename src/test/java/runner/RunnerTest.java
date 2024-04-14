package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverConfig;

// Clase main
public class RunnerTest {
    private static WebDriver driver;
    @BeforeMethod
    public static void setDriver(){
        // Devuelve navegador vacio
        driver = WebDriverConfig.createBrowser();
        // Llama a WebDriverConfig.openUrl que obtiene la dirección para darsela al driver
        driver = WebDriverConfig.openUrl(driver);
    }
    @AfterMethod
    public static void tearDown(){
        // Llamada a Webdriverconfig.tearDown para cerrar el navegador
        WebDriverConfig.tearDown(driver);
    }

    @Test(priority = 1, description = "Test 1 - login OK")
    public static void testLoginOK() throws InterruptedException {
        //*** PRIMER TEST LOGIN OK ***
        driver.findElement(By.id("btn-make-appointment")).click();
        Thread.sleep(3000);
        String user = driver.findElement(By.xpath("//input[@aria-describedby='demo_username_label']")).getAttribute("value");
        String password = driver.findElement(By.xpath("//input[@aria-describedby='demo_password_label']")).getAttribute("value");

        driver.findElement(By.id("btn-make-appointment")).click();

        driver.findElement(By.id("txt-username")).clear();
        driver.findElement(By.id("txt-username")).sendKeys(user);

        driver.findElement(By.id("txt-password")).clear();
        driver.findElement(By.id("txt-password")).sendKeys(password);

        driver.findElement(By.id("btn-login")).click();
        Thread.sleep(3000);

        try {
            driver.findElement(By.id("btn-book-appointment")).isDisplayed();
            System.out.println("LOGIN OK");
        } catch (NoSuchElementException e) {
            System.out.println("ERROR EN LOGIN");
        }

    }
    @Test(priority = 2, description = "Test 2 - login KO")
    public static void testLoginKO() throws InterruptedException {
        //*** SEGUNDO TEST LOGIN KO ***
        // Devuelve navegador vacio
        WebDriver driver = WebDriverConfig.createBrowser();
        // Llama a WebDriverConfig.openUrl que obtiene la dirección para darsela al driver
        WebDriverConfig.openUrl(driver);

        driver.findElement(By.id("btn-make-appointment")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("btn-make-appointment")).click();

        driver.findElement(By.id("txt-username")).clear();
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");

        driver.findElement(By.id("txt-password")).clear();
        driver.findElement(By.id("txt-password")).sendKeys("FAILPASSWORD");

        driver.findElement(By.id("btn-login")).click();
        Thread.sleep(3000);

        try {
            driver.findElement(By.id("btn-book-appointment")).isDisplayed();
            System.out.println("LOGIN OK");
        } catch (NoSuchElementException e) {
            System.out.println("ERROR EN LOGIN");
        }
    }

}
