package com.venturocket.api.client.listing.object;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 7:17 PM
 */
public class Listing {
    public String userId;
    public UserType userType;
    public String headline;
    public boolean enabled;
    public Telecommute telecommute;
    public Location[] locations;
    public long created;
    public Keyword[] keywords;
    public ListingType[] listingTypes;
    public String listingId;
    public long modified;
}
