package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.util.concurrent.TimeUnit;

public class WebDriverConfig {
    // Método con driverManager de boni Garcia
    public static ChromeDriver createChromeDriverBoniGarcia() {

        // Limpiamos caché para evitar errores
        WebDriverManager.chromedriver().clearDriverCache();
        // Instala el driver correspondiente a nuestra version de chrome con la libreria de B.Garcia
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        //options.addArguments("--headless");
        // options.addArguments("window-size=500,600");

        ChromeDriver driver = new ChromeDriver(options);

        // Waits config - poner siempre - tiempo que tarda el driver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
    // Método driver local chrome
    // Eliminado sustituido por createChromeDriverBoniGarcia

    // Método driver local Edge
    public static EdgeDriver createEdgeDriver() {
        String driverPath = (System.getProperty("user.dir") + "/driver/msedgedriver.exe");
        System.setProperty("webdriver.edge.driver", driverPath);

        //EdgeDriver driver = new EdgeDriver();
        EdgeOptions optionsEdge = new EdgeOptions();
        //optionsEdge.(...)
        EdgeDriver driver = new EdgeDriver(optionsEdge);

        return driver;
    }

    // Función que lee un archivo de propiedades y elige el navegador determinado en él
    public static WebDriver createBrowser() {

        // Leemos la propiedad "browser" e inicializamos el driver vacio
        String browser = Util.readProperty("browser");
        WebDriver driver = null;

        // Según que browser tengamos en properties llamará a la función correspondiente
        if (browser.equalsIgnoreCase("chrome")) { // * Ignoramos Case para evitar errores
            driver = createChromeDriverBoniGarcia();
        }
        else if (browser.equalsIgnoreCase("edge")) {
            driver = createEdgeDriver();
        } else {
        // Manejar el caso en el que el navegador no sea ni "chrome" ni "edge"
            System.out.println("Navegador no compatible. Iniciando Chrome por defecto...");
            driver = createChromeDriverBoniGarcia(); // Inicia Chrome por defecto o puedes lanzar una excepción
        }

        return driver;
    }

    // Función para abrir url de archivo properties
    public static WebDriver openUrl(WebDriver driver) {
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
