package com.venturocket.api.client.listing.object;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * User: Joe Linn
 * Date: 1/5/14
 * Time: 2:51 AM
 */
public class EnumSerializer extends JsonSerializer<ListingEnum> {
    @Override
    public void serialize(ListingEnum value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(value.toString());
    }
}
