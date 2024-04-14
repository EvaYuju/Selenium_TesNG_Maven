package utils;

import java.io.FileInputStream;
import java.util.Properties;

// Para leer propiedades en config.properties
public class Util {
    public static String readProperty(String key) {

        // Archivo a leer
        String path = System.getProperty("user.dir") +"/src/main/resources/config.properties";
        // Inicializamos el objeto properties
        Properties properties = new Properties();

        try {
            FileInputStream input = new FileInputStream(path);
            properties.load(input);
        } catch(Exception e){
            e.printStackTrace();
        }
        return properties.getProperty(key);

    }
}
