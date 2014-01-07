package com.venturocket.api.client.listing.object;

import com.google.common.base.CaseFormat;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * User: Joe Linn
 * Date: 1/5/14
 * Time: 5:31 PM
 */
public class ListingTypeSerializer extends JsonSerializer<ListingType.Type>{
    @Override
    public void serialize(ListingType.Type value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, value.name()));
    }
}
