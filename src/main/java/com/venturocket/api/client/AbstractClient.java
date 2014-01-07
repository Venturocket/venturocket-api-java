package com.venturocket.api.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 5:49 PM
 */
public abstract class AbstractClient {
    protected static final String BASE_URL = "https://api.venturocket.com/v1/";

    protected String apiKey;

    protected String apiSecret;

    protected WebResource service;

    public AbstractClient(String apiKey, String apiSecret){
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;

        ClientConfig config = new DefaultClientConfig();
        ObjectMapper mapper = new ObjectMapper();
        JacksonJsonProvider provider = new JacksonJsonProvider(mapper);
        config.getSingletons().add(provider);
        Client client = Client.create(config);
        client.addFilter(new HTTPBasicAuthFilter(apiKey, apiSecret));
        service = client.resource(UriBuilder.fromUri(BASE_URL).build());
    }

    protected ClientResponse get(String url){
        return get(url, null);
    }

    protected ClientResponse get(String url, MultivaluedMapImpl queryParams){
        return request("GET", url, queryParams);
    }

    protected ClientResponse post(String url, Object data){
        return request("POST", url, null, data);
    }

    protected ClientResponse put(String url){
        return put(url, null);
    }

    protected ClientResponse put(String url, Object data){
        return request("PUT", url, null, data);
    }

    protected ClientResponse delete(String url){
        return request("DELETE", url);
    }

    protected ClientResponse request(String method, String url){
        return request(method, url, null);
    }

    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams){
        return request(method, url, queryParams, null);
    }

    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams, Object data){
        ClientResponse clientResponse = getResourceBuilder(url, queryParams).method(method, ClientResponse.class, data);
        checkForErrors(clientResponse);
        return clientResponse;
    }

    private void checkForErrors(ClientResponse clientResponse){
        if(clientResponse.getClientResponseStatus().getStatusCode() != ClientResponse.Status.OK.getStatusCode()
                && clientResponse.getClientResponseStatus().getStatusCode() != ClientResponse.Status.CREATED.getStatusCode()){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                throw new ApiException(clientResponse.getClientResponseStatus(),
                        objectMapper.readValue(clientResponse.getEntity(String.class), Error.class).error);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private WebResource.Builder getResourceBuilder(String url, MultivaluedMapImpl queryParams){
        WebResource webResource = service.path(url);
        if(queryParams != null){
            webResource = webResource.queryParams(queryParams);
        }
        return webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
    }
}
