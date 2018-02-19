package app;

import app.exceptions.LanguageNotFoundException;

import java.io.*;
import java.util.Properties;

public class PropertiesReader {
    private String language;
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;


    private Properties properties;
    private InputStream input = null;

    public PropertiesReader() {
        properties = new Properties();
        try {
            input = new FileInputStream("data/properties");
            properties.load(input);
            language = properties.getProperty("language");
            consumerKey = properties.getProperty("consumerKey");
            consumerSecret = properties.getProperty("consumerSecret");
            accessToken = properties.getProperty("accessToken");
            accessTokenSecret = properties.getProperty("accessTokenSecret");


        } catch (FileNotFoundException e) {
            GUI.log.error("File not found exception!", e);
        } catch (IOException e) {
            GUI.log.error("IO Exception", e);
        }
    }


    public String getLanguage() throws LanguageNotFoundException {
        if(!language.equals("pl")) throw new LanguageNotFoundException();
        return language;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }
}
