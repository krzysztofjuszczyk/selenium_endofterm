package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
private Properties properties;

    public Config(Properties properties) {
        this.properties = getProperties();
    }

    public Config() {

    }

    private Properties getProperties(){
        Properties p = new Properties();
        InputStream inputStream =getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            p.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not find config.properties file" + e);
        }
        return p;
    }

    public String getURL(){
        return properties.getProperty("application.url");
    }

    public String getAppUsername(){
        return properties.getProperty("application.user");
    };
    public String getPassword(){
        return properties.getProperty("application.password");
    }

}
