package backEnd.services.factory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public  class  Config {
    public static Properties properties;

    public Config() {
        this.properties = new Properties();
        loadProp();
    }
    public void writeProp() {
        try {
            FileOutputStream fileInputStream = new FileOutputStream("config.properties");
            properties.store(fileInputStream, "configs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDefaultProp() {
        try {
            FileOutputStream fileInputStream = new FileOutputStream("config.properties");
            properties.setProperty("front", "pakli4");
            properties.setProperty("back", "blue");
            properties.store(fileInputStream, "configs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProp() {
        try {
            FileInputStream fileInputStream = new FileInputStream("config.properties");
            this.properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            writeDefaultProp();
        }
    }
}
