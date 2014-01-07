package com.venturocket.api.client.test;

import com.venturocket.api.client.Venturocket;
import com.venturocket.api.client.keyword.KeywordClient;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Joe Linn
 * Date: 1/6/14
 * Time: 4:55 PM
 */
public class VenturocketTest extends BaseTest{
    protected Venturocket client;

    @Before
    public void setUp(){
        client = new Venturocket(getApiKey(), getApiSecret());
    }

    @Test
    public void testGetClient(){
        TestCase.assertEquals(KeywordClient.class, client.keyword().getClass());
    }
}
