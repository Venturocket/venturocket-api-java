package com.venturocket.api.client.listing.object;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * User: Joe Linn
 * Date: 1/5/14
 * Time: 1:26 AM
 */
@JsonSerialize(using = EnumSerializer.class)
public enum Telecommute implements ListingEnum{
    YES,
    NO,
    ONLY;

    @JsonCreator
    public static Telecommute create(String name){
        return Telecommute.valueOf(name.toUpperCase());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
