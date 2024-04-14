package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utils.WebDriverConfig;

import java.io.IOException;

// Clase main
public class RunnerTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        //*** PRIMER TEST LOGIN OK ***
        // Devuelve navegador vacio
        WebDriver driver = WebDriverConfig.createBrowser();
        // Llama a WebDriverConfig.openUrl que obtiene la dirección para darsela al driver
        WebDriverConfig.openUrl(driver);
        System.out.println("test login ok");

        // xxxxxxxxxx Aquí van los tests xxxxxxx
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

        // Llamada a Webdriverconfig.tearDown para cerrar el navegador
        WebDriverConfig.tearDown(driver);

        //*** SEGUNDO TEST LOGIN KO ***
        // Devuelve navegador vacio
        driver = WebDriverConfig.createBrowser();
        // Llama a WebDriverConfig.openUrl que obtiene la dirección para darsela al driver
        WebDriverConfig.openUrl(driver);
        System.out.println("Test Login KO");

        // xxxxxxxxxx Aquí van los tests xxxxxxx
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

        // Llamada a Webdriverconfig.tearDown para cerrar el navegador
        WebDriverConfig.tearDown(driver);
    }
}
