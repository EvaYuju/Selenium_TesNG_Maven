package runner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MakeAppointment;
import utils.WebDriverConfig;

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
        // Creamos el objeto LoginPage
        LoginPage loginPage = new LoginPage();
        loginPage.goToHome(driver);

        // Opción 1
        // LLamamos al objeto y le pasamos el driver
        //loginPage.doLogin(driver);
        // Opción 2
        loginPage.setUsername(driver);
        loginPage.setPassword(driver);
        loginPage.clickLogin(driver);
        // Opción 3 - Más resumido en un método en vez de en test
        // loginPage.doLogin2(driver);

        loginPage.validationOK(driver);

    }
    @Test(priority = 2, description = "Test 2 - login KO")
    public static void testLoginKO() throws InterruptedException {

        LoginPage loginPage = new LoginPage();
        loginPage.goToHome(driver);
        loginPage.setUsername(driver);
        loginPage.setPasswordKO(driver);
        loginPage.clickLogin(driver);
        loginPage.validationKO(driver);
    }

    @Test(priority = 1, description = "Test 3 - Book Appointment")
    public static void testBookAppointmentWaits() throws InterruptedException {

        testLoginOK(); // LoginPage loginPage = new LoginPage(); para separar los test por si uno falla
        MakeAppointment makeAppointment = new MakeAppointment();
        Thread.sleep(2000);
        makeAppointment.setFacility(driver);
        makeAppointment.checkHospital(driver);
        makeAppointment.setDate(driver);
        makeAppointment.setComment(driver);
        makeAppointment.bookMakeAppointment(driver);

    }
    /*  Como estaba antes el método de pasarlo a MakeApoinment.java
    @Test(priority = 1, description = "Test 3 - Book Appointment")
    public static void testBookAppointmentWaits() throws InterruptedException {

        testLoginOK(); // LoginPage loginPage = new LoginPage(); para separar los test por si uno falla

        // *** AÑADIENDO ESPERAS ***
        // Opción 1
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("")));
        driver.findElement((By.id("xxx"))).click();

        // Opción 2
        WebElement element = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("xx"))));
        element.click();
        // Opción 3
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("xx")))).click();

        Thread.sleep(2000);

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

    } */


}
