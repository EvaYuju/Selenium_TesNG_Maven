package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.IOException;

public class WebDriverConfig {
    // Método driver local chrome
    public static ChromeDriver createChromeDriver() {

        String driverPath = System.getProperty("user.dir") + "/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        // Con argumentos
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        //options.addArguments("--headless");
        //options.addArguments("window-size=500,600");

        return new ChromeDriver(options);
    }

    // Método driver local Edge
    public static EdgeDriver createEdgeDriver() {
        String driverPath = (System.getProperty("user.dir") + "/driver/msedgedriver.exe");
        System.setProperty("webdriver.edge.driver", driverPath);

        //EdgeDriver driver = new EdgeDriver();
        EdgeOptions optionsEdge = new EdgeOptions();
        //optionsEdge.(...)
        EdgeDriver driver = new EdgeDriver(optionsEdge);

        //driver.get("https://katalon-demo-cura.herokuapp.com/");
        return driver;
    }

    // Función que lee un archivo de propiedades y elige el navegador determinado en él
    public static WebDriver createBrowser() throws IOException {

        // Leemos la propiedad "browser" e inicializamos el driver vacio
        String browser = Util.readProperty("browser");
        WebDriver driver = null;

        // Según que browser tengamos en properties llamará a la función correspondiente
        if (browser.equalsIgnoreCase("chrome")) { // * Ignoramos Case para evitar errores
            driver = createChromeDriver();
        }
        if (browser.equalsIgnoreCase("edge")) {
            driver = createEdgeDriver();
        } else {
            //error o lanzar chrome
        }

        return driver;
    }

    // Función para abrir url de archivo properties
    public static WebDriver openUrl(WebDriver driver) throws IOException {
        driver.get(Util.readProperty("url"));
        return driver;
    }

    // Método para cerrar el navegador
    public static void tearDown(WebDriver driver) {
        // Cierra el navegador si está abierto y libera recursos
        if (driver != null) {
            driver.close();
            driver.quit();
            System.out.println("Browser closed");
        }
    }



}
