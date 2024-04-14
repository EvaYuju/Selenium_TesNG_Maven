package runner;

import org.openqa.selenium.WebDriver;
import utils.WebDriverConfig;

import java.io.IOException;

// Clase main
public class RunnerTest {
    public static void main(String[] args) throws IOException {

        // Devuelve navegador vacio
        WebDriver driver = WebDriverConfig.createBrowser();
        // Llama a WebDriverConfig.openUrl que obtiene la dirección para darsela al driver
        WebDriverConfig.openUrl(driver);
        System.out.println("Hello Selenium");

        // xxxxxxxxxx Aquí van los tests xxxxxxx


        // Llamada a Webdriverconfig.tearDown para cerrar el navegador
        WebDriverConfig.tearDown(driver);
    }
}
