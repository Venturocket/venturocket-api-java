package com.venturocket.api.client.test;

import org.junit.Before;

import java.io.IOException;
import java.util.Properties;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 6:15 PM
 */
public class BaseTest {
    private Properties properties;

    @Before
    public void setUpBefore(){
        properties = new Properties();
        try {
            properties.load(BaseTest.class.getResourceAsStream("/junit.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getApiKey(){
        return properties.getProperty("apiKey", "");
    }

    public String getApiSecret(){
        return properties.getProperty("apiSecret", "");
    }
}
