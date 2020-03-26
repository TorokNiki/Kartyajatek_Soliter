package backEnd.services.factory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static Properties properties = new Properties();
    public static String tempBack = "blue";
    public static String tempFront = "pakli4";
    public static String tempBackground = "image";
    public static String tempBackgroundcolor = "lightblue";
    public static String tempBackgroundimage = "gb03.jpg";


    public static void writeProp() {
        try {
            FileOutputStream fileInputStream = new FileOutputStream("config.properties");
            properties.store(fileInputStream, "configs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDefaultProp() {
        try {
            properties.setProperty("front", "pakli4");
            properties.setProperty("back", "blue");
            properties.setProperty("bacground", "image");
            properties.setProperty("bacgroundimage", "gb03.jpg");
            properties.setProperty("bacgroundcolor", "lightblue");
            FileOutputStream fileInputStream = new FileOutputStream("config.properties");
            properties.store(fileInputStream, "configs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProp() {
        try {
            FileInputStream fileInputStream = new FileInputStream("config.properties");
            properties.load(fileInputStream);
            tempBack = properties.getProperty("back");
            tempFront = properties.getProperty("front");
            tempBackground = properties.getProperty("bacground");
            tempBackgroundcolor = properties.getProperty("bacgroundcolor");
            tempBackgroundimage = properties.getProperty("bacgroundimage");

        } catch (IOException e) {
            e.printStackTrace();
            writeDefaultProp();
        }
    }
}
