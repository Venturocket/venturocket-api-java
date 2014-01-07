package com.venturocket.api.client.listing;

import com.venturocket.api.client.listing.object.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Joe Linn
 * Date: 1/5/14
 * Time: 1:23 AM
 */
public class ListingBuilder {
    protected Listing listing = new Listing();

    protected List<Location> locations = new ArrayList<>();
    protected List<Keyword> keywords = new ArrayList<>();
    protected List<ListingType> listingTypes = new ArrayList<>();

    /**
     * Use this constructor when you wish to use a ListingBuilder instance to modify a pre-existing Listing object
     * @param listing a pre-existing Listing object
     */
    public ListingBuilder(Listing listing){
        this.listing = listing;
        locations(listing.locations).keywords(listing.keywords).listingTypes(listing.listingTypes);
    }

    public ListingBuilder(String userId, UserType userType, String headline){
        userId(userId).userType(userType).headline(headline);
        telecommute(Telecommute.NO);    // telecommute defaults to "no"
    }

    public ListingBuilder listingId(String listingId){
        listing.listingId = listingId;
        return this;
    }

    public ListingBuilder userId(String userId){
        listing.userId = userId;
        return this;
    }

    public ListingBuilder userType(UserType userType){
        listing.userType = userType;
        return this;
    }

    public ListingBuilder headline(String headline){
        listing.headline = headline;
        return this;
    }

    public ListingBuilder telecommute(Telecommute telecommute){
        listing.telecommute = telecommute;
        return this;
    }

    public ListingBuilder locations(Location[] locations){
        this.locations = new ArrayList<>(Arrays.asList(locations));
        return this;
    }

    public ListingBuilder addLocation(String zip, int commute, boolean relocate){
        Location location = new Location();
        location.zip = zip;
        location.commute = commute;
        location.relocate = relocate;
        locations.add(location);
        return this;
    }

    public ListingBuilder addLocation(String zip){
        return addLocation(zip, 0, false);
    }

    public ListingBuilder keywords(Keyword[] keywords){
        this.keywords = new ArrayList<>(Arrays.asList(keywords));
        return this;
    }

    public ListingBuilder addKeyword(String word, int experience, int range){
        Keyword keyword = new Keyword();
        keyword.word = word;
        keyword.experience = experience;
        keyword.range = range;
        keywords.add(keyword);
        return this;
    }

    public ListingBuilder addKeyword(String word, int experience){
        return addKeyword(word, experience, 0);
    }

    public ListingBuilder listingTypes(ListingType[] types){
        listingTypes = new ArrayList<>(Arrays.asList(types));
        return this;
    }

    public ListingBuilder addListingType(ListingType.Type type){
        ListingType listingType = new ListingType();
        listingType.type = type;
        listingTypes.add(listingType);
        return this;
    }

    public Listing build(){
        listing.locations = locations.toArray(new Location[locations.size()]);
        listing.keywords = keywords.toArray(new Keyword[keywords.size()]);
        listing.listingTypes = listingTypes.toArray(new ListingType[listingTypes.size()]);
        return listing;
    }
}
