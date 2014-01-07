package com.venturocket.api.client;

import com.venturocket.api.client.keyword.KeywordClient;
import com.venturocket.api.client.listing.ListingClient;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Joe Linn
 * Date: 1/6/14
 * Time: 4:51 PM
 */
public class Venturocket {
    protected String apiKey;
    protected String apiSecret;

    protected Map<Class<? extends AbstractClient>, AbstractClient> clients = new HashMap<>();

    public Venturocket(String apiKey, String apiSecret){
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getClient(Class<? extends AbstractClient> clazz){
        if(!clients.containsKey(clazz)){
            try {
                clients.put(clazz, clazz.getConstructor(String.class, String.class).newInstance(apiKey, apiSecret));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return (T) clients.get(clazz);
    }

    public KeywordClient keyword(){
        return getClient(KeywordClient.class);
    }

    public ListingClient listing(){
        return getClient(ListingClient.class);
    }
}
