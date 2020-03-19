package backEnd.services.factory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public  class  Config {
    public static Properties properties = new Properties();;
    public static String tempBack="blue";
    public static String tempFront="pakli4";
    public static String tempBackground="lightblue";


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
            properties.setProperty("bacground","lightblue");
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
            tempBack=properties.getProperty("back");
            tempFront=properties.getProperty("front");
            tempBackground=properties.getProperty("bacground");

        } catch (IOException e) {
            e.printStackTrace();
            writeDefaultProp();
        }
    }
}
