package com.venturocket.api.client.listing.object;

import org.codehaus.jackson.annotate.JsonCreator;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 7:18 PM
 */
public enum UserType implements ListingEnum{
    PROVIDER,
    SEEKER;

    @JsonCreator
    public static UserType create(String name){
        return UserType.valueOf(name.toUpperCase());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
