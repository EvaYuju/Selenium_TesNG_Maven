package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Clase main
public class RunnerTest {
    private static WebDriver driver;
    @BeforeMethod
    public static void setDriver(){
        // Devuelve navegador vacio
        driver = WebDriverConfig.createBrowser();
        // Llama a WebDriverConfig.openUrl que obtiene la dirección para darsela al driver
        WebDriverConfig.openUrl(driver);
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

        // Validación: si es True que existe el elemento por id + maneja la excepción
        Assert.assertTrue(driver.findElement(By.id("btn-book-appointment")).isDisplayed());

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

        // Cogemos valores del atributo
        String user = driver.findElement(By.xpath("//input[@aria-describedby='demo_username_label']")).getAttribute("value");
        String password = driver.findElement(By.xpath("//input[@aria-describedby='demo_password_label']")).getAttribute("value");
        // Comprobamos
        Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txt-username']")).getText(),"Username");
        driver.findElement(By.xpath("//label[@for='txt-username']"));

        driver.findElement(By.id("txt-username")).clear();
        driver.findElement(By.id("txt-username")).sendKeys(user);

        driver.findElement(By.id("txt-password")).clear();
        driver.findElement(By.id("txt-password")).sendKeys(password);

        driver.findElement(By.id("btn-login")).click();
        Thread.sleep(3000);
    }
    @Test(priority = 1, description = "Test 3 - Book Appointment")
    public static void testBookAppintment() throws InterruptedException {
        testLoginOK();
        // Codigo para rellenar el formulario
        // video_MakeApp min 18:50
    }

    @Test(priority = 1, description = "Test 4 - Book Appointment")
    public static void testBookAppintmentWaits() throws InterruptedException {
        testLoginOK();

        // * AÑADIENDO ESPERAS
        // Opción 1
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("")));
        driver.findElement((By.id("xxx"))).click();
        //Opción 2
        WebElement element = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("xx"))));
        element.click();

        //Opción 3
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("xx")))).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("xx")).click();

        driver.findElement(By.xpath("(//option[@value])[1]")).click();

        int options = driver.findElements(By.xpath("//option[@value]")).size();
        // funcion random que devuelve valor entre 0-2
        int random = 2;
        driver.findElement(By.xpath("(//option[@value])"+"["+Integer.toString(random)+"]")).click();


        Select select = new Select(driver.findElement(By.id("combo_facility")));
        select.selectByIndex(1);
        select.getOptions().size();
        select.selectByIndex(random);
        select.selectByValue("Tokyo CURA Healthcare Center");
        select.selectByVisibleText("Seoul CURA Healthcare Center");
        driver.findElement(By.xpath("//select[@id='combo_facility']")).sendKeys("Hongkong CURA Healthcare Center");

        if (!driver.findElement(By.xpath("//*[@id='chk_hospotal_readmission']")).isSelected())
        {
            driver.findElement(By.xpath("//*[@id='chk_hospotal_readmission']")).click();
        }

        Date fechaActual = new Date();
        // Define el formato de fecha deseado
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // Formatea la fecha de hoy según el formato especificado
        String fechaFormateada = formato.format(fechaActual);
        driver.findElement(By.id("txt_visit_date")).sendKeys(fechaFormateada);

        driver.findElement(By.id("txt_comment")).click();
        driver.findElement(By.id("txt_comment")).sendKeys("Automation test");

        driver.findElement(By.id("btn-book-appointment")).click();

        String expectedConfirmationTxT = "Appointment Confirmation";
        String actualConfirmationTxT = driver.findElement(By.xpath("//*[@id='summary']//h2")).getText();
        Assert.assertEquals(actualConfirmationTxT,expectedConfirmationTxT);

        System.out.println();

    }

}
