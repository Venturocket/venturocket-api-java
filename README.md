venturocket-api-java
====================

The official Java client library for [Venturocket's API](https://venturocket.com/api/v1).

## Usage
### Maven dependency
```xml
<dependency>
  <groupId>com.venturocket</groupId>
  <artifactId>api-client</artifactId>
  <version>1.0.1</version>
</dependency>
```

### Making API calls
#### Initialize the client object
```java
Venturocket venturocket = new Venturocket("your-api-key", "your-api-secret");
```

#### Keyword calls
```java
// Retrieve validity and synonym data for a specific keyword
Keyword keyword = venturocket.keyword().getKeyword("php");

// Retrieve keyword suggestions based on one or more provided keywords
String[] suggestions = venturocket.keyword().getSuggestions("php", "python", "java");

// Parse valid keywords from raw text
String text = "We are looking for rock star web developer with expertise in Javascript and PHP.";
String[] keywords = venturocket.keyword().parseKeywords(text);
```

#### Listing calls
```java
// Create a listing
ListingBuilder listingBuilder = new ListingBuilder("a_user_id", UserType.PROVIDER, "Your headline here!");
listingBuilder.addKeyword("php", 400, 102);
listingBuilder.addLocation("94105");
listingBuilder.addListingType(ListingType.Type.FULL_TIME);
String listingId = venturocket.listing().createListing(listingBuilder);

// Retrieve a listing
Listing listing = venturocket.listing().getListing(listingId);

// Update a listing
listing.userType = UserType.SEEKER;
venturocket.listing().updateListing(listing);

// Disable a listing
venturocket.listing().disableListing(listingId);

// Enable a listing
venturocket.listing().enableListing(listingId);

// Retrieve matches for a listing
Matches matches = venturocket.listing().getMatches(listingId);
```