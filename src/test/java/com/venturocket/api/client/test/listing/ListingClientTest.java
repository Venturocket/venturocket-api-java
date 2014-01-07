package com.venturocket.api.client.test.listing;

import com.venturocket.api.client.listing.ListingBuilder;
import com.venturocket.api.client.listing.ListingClient;
import com.venturocket.api.client.listing.object.*;
import com.venturocket.api.client.test.BaseTest;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 7:30 PM
 */
public class ListingClientTest extends BaseTest{
    protected ListingClient client;

    @Before
    public void setUp(){
        client = new ListingClient(getApiKey(), getApiSecret());
    }

    @Test
    public void testStorage(){
        // Build a Listing
        ListingBuilder listingBuilder = new ListingBuilder("test_user", UserType.PROVIDER, "testing the java api client");
        listingBuilder.addKeyword("php", 400, 102);
        listingBuilder.addLocation("94105");
        listingBuilder.addListingType(ListingType.Type.FULL_TIME);

        // Create the listing
        String listingId = client.createListing(listingBuilder);

        try {
            Thread.sleep(1000); // Allow VR to store the new listing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Retrieve the new listing
        Listing listing = client.getListing(listingId);

        // Ensure that the proper listing has been retrieved
        TestCase.assertEquals(listingId, listing.listingId);

        // Modify the Listing
        listingBuilder = new ListingBuilder(listing);
        listingBuilder.userType(UserType.SEEKER);

        // Update the Listing
        listingId = client.updateListing(listingBuilder);

        try {
            Thread.sleep(1000); // Allow VR to store the updates
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Retrieve the updated listing
        Listing updatedListing = client.getListing(listingId);

        // Ensure that the modifications were stored
        TestCase.assertEquals(UserType.SEEKER, updatedListing.userType);

        // Disable the Listing
        listingId = client.disableListing(listingId);

        TestCase.assertEquals(updatedListing.listingId, listingId);

        try {
            Thread.sleep(1000); // Allow VR to store the updates
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure that the Listing has been disabled
        updatedListing = client.getListing(listingId);
        TestCase.assertFalse(updatedListing.enabled);
    }

    @Test
    public void testGetMatches(){
        // Create and store two listings which should match each other
        ListingBuilder listingBuilder1 = new ListingBuilder("test_match_1", UserType.PROVIDER, "testing matching -- provider");
        listingBuilder1.addKeyword("python", 400, 500);
        listingBuilder1.addListingType(ListingType.Type.FULL_TIME);
        listingBuilder1.addLocation("92109");

        ListingBuilder listingBuilder2 = new ListingBuilder("test_match_2", UserType.SEEKER, "testing matching -- seeker");
        listingBuilder2.addKeyword("python", 500);  // This should result in a 100% match
        listingBuilder2.addListingType(ListingType.Type.FULL_TIME);
        listingBuilder2.addLocation("92109", 5, false);

        String listingId1 = client.createListing(listingBuilder1);
        String listingId2 = client.createListing(listingBuilder2);

        try {
            Thread.sleep(1000); // Allow VR to store the listings
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure that the two listings are a match for each other
        Matches listing1Matches = client.getMatches(listingId1);
        Matches listing2Matches = client.getMatches(listingId2);

        boolean matchFound = false;
        float score = 0;
        for(Match match : listing1Matches.matches){
            if(match.listing.listingId.equals(listingId2)){
                matchFound = true;
                score = match.score;
            }
        }

        TestCase.assertTrue(matchFound);
        TestCase.assertEquals(1.0, score, 0.00001);

        matchFound = false;
        for(Match match : listing2Matches.matches){
            if(match.listing.listingId.equals(listingId1)){
                matchFound = true;
                TestCase.assertEquals(score, match.score);
            }
        }

        TestCase.assertTrue(matchFound);

        // Disable both test listings
        client.disableListing(listingId1);
        client.disableListing(listingId2);

        try {
            Thread.sleep(1000); // Allow VR to disable the listings
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listing1Matches = client.getMatches(listingId1);
        matchFound = false;
        for(Match match : listing1Matches.matches){
            if(match.listing.listingId.equals(listingId2)){
                matchFound = true;
            }
        }

        // Ensure that the disabled listing is no longer among the matches
        TestCase.assertFalse(matchFound);
    }
}
