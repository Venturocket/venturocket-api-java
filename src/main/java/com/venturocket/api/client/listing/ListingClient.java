package com.venturocket.api.client.listing;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.venturocket.api.client.AbstractClient;
import com.venturocket.api.client.listing.object.Listing;
import com.venturocket.api.client.listing.object.Matches;

import java.util.Map;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 7:15 PM
 * @see <a href="http://venturocket.com/api/v1#listings">http://venturocket.com/api/v1#listings</a>
 */
public class ListingClient extends AbstractClient{
    public ListingClient(String apiKey, String apiSecret) {
        super(apiKey, apiSecret);
    }

    /**
     * Create a listing
     * @param listing a Listing object
     * @return the id of the new listing
     * @see <a href="http://venturocket.com/api/v1#listings_create">http://venturocket.com/api/v1#listings_create</a>
     */
    public String createListing(Listing listing){
        return getListingIdFromResponse(post("listing", listing));
    }

    /**
     * Create a listing
     * @param listingBuilder a ListingBuilder object
     * @return the id of the newly created listing
     * @see <a href="http://venturocket.com/api/v1#listings_create">http://venturocket.com/api/v1#listings_create</a>
     */
    public String createListing(ListingBuilder listingBuilder){
        return createListing(listingBuilder.build());
    }

    /**
     * Updates the specified listing by replacing currently stored values with the provided values. The data provided must constitute a valid listing object.
     * @param listing a Listing object with the listingId property set
     * @return the id of the updated listing
     * @see <a href="http://venturocket.com/api/v1#listings_update">http://venturocket.com/api/v1#listings_update</a>
     */
    public String updateListing(Listing listing){
        if(listing.listingId == null){
            throw new IllegalArgumentException("The given Listing object must have the listingId property set in order to perform an update operation.");
        }
        return getListingIdFromResponse(put(String.format("listing/%s", listing.listingId), listing));
    }

    /**
     * Updates the specified listing by replacing currently stored values with the provided values. The data provided must constitute a valid listing object.
     * @param listingBuilder a ListingBuilder object with the listingId set
     * @return the id of the updated listing
     * @see <a href="http://venturocket.com/api/v1#listings_update">http://venturocket.com/api/v1#listings_update</a>
     */
    public String updateListing(ListingBuilder listingBuilder){
        return updateListing(listingBuilder.build());
    }

    /**
     * Retrieve a previously stored listing
     * @param listingId the id of the desired listing
     * @return a Listing object
     * @see <a href="http://venturocket.com/api/v1#listings_get">http://venturocket.com/api/v1#listings_get</a>
     */
    public Listing getListing(String listingId){
        return get(String.format("listing/%s", listingId)).getEntity(Listing.class);
    }

    /**
     * Disable a listing
     * @param listingId the id of the listing to be disabled
     * @return the id of the newly-disabled listing
     * @see <a href="http://venturocket.com/api/v1#listings_disable">http://venturocket.com/api/v1#listings_disable</a>
     */
    public String disableListing(String listingId){
        return getListingIdFromResponse(put(String.format("listing/%s/disable", listingId)));
    }

    /**
     * Enable a listing
     * @param listingId the id of the listing to be enabled
     * @return the id of the newly-enabled listing
     * @see <a href="http://venturocket.com/api/v1#listings_enable">http://venturocket.com/api/v1#listings_enable</a>
     */
    public String enableListing(String listingId){
        return getListingIdFromResponse(put(String.format("listing/%s/enable", listingId)));
    }

    /**
     * Get matches for a specific Listing.
     * @param listingId the id of the Listing for which to find matches
     * @return matches for the given Listing. If no matches are found, the "matches" property of the returned Matches object will be an empty array.
     * @see <a href="http://venturocket.com/api/v1#matches_listing_stored">http://venturocket.com/api/v1#matches_listing_stored</a>
     */
    public Matches getMatches(String listingId){
        return get(String.format("listing/%s/matches", listingId)).getEntity(Matches.class);
    }

    /**
     * @param response a ClientResponse object.
     * @return the listingId
     */
    protected String getListingIdFromResponse(ClientResponse response){
        Map<String, String> map = response.getEntity(new GenericType<Map<String, String>>() {});
        if(!map.containsKey("listingId")){
            throw new IllegalArgumentException("The body of the ClientResponse object must contain a listingId field.");
        }
        return map.get("listingId");
    }
}
