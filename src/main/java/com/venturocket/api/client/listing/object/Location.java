package com.venturocket.api.client.listing.object;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 7:21 PM
 */
public class Location {
    public String zip;
    public int commute;
    public String state;
    public boolean relocate;
    public Coordinates coordinates;
    public String city;

    public static class Coordinates{
        public double lat;
        public double lon;
        public String geohash;
    }
}
