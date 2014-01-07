package com.venturocket.api.client.keyword;

import com.google.common.base.Joiner;
import com.sun.jersey.api.client.GenericType;
import com.venturocket.api.client.AbstractClient;
import com.venturocket.api.client.keyword.object.Keyword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 6:02 PM
 * @see <a href="http://venturocket.com/api/v1#keywords">http://venturocket.com/api/v1#keywords</a>
 */
public class KeywordClient extends AbstractClient{
    public KeywordClient(String apiKey, String apiSecret) {
        super(apiKey, apiSecret);
    }

    /**
     * Retrieve validity and synonym information regarding the requested keyword
     * @param word the word for which to retrieve keyword information
     * @return a Keyword object
     * @see <a href="http://venturocket.com/api/v1#keywords_get">http://venturocket.com/api/v1#keywords_get</a>
     */
    public Keyword getKeyword(String word){
        return get(String.format("keyword/%s", word)).getEntity(Keyword.class);
    }

    /**
     * Parse valid VR keywords from raw text
     * @param text raw text from which to parse keywords
     * @return an array of keywords parsed from the text in order of descending popularity
     */
    public String[] parseKeywords(String text){
        Map<String, String> postData = new HashMap<>();
        postData.put("text", text);
        @SuppressWarnings("unchecked")
        List<String> keywords = (ArrayList) post("keyword-parser", postData)
                .getEntity(new GenericType<Map<String, Object>>(){}).get("keywords");
        return keywords.toArray(new String[keywords.size()]);
    }

    /**
     * Given one or more keywords, this method will return a list of keywords ordered by descending popularity which are commonly used with the given keyword(s).
     * @param words a comma-delimited list of keywords
     * @return a list of keywords commonly associated with the keywords given in the words parameter
     * @see <a href="http://venturocket.com/api/v1#keywords_suggestions">http://venturocket.com/api/v1#keywords_suggestions</a>
     */
    public String[] getSuggestions(String words){
        @SuppressWarnings("unchecked")
        List<String> suggestions = (ArrayList) get(String.format("keyword-suggestions/%s", words))
                .getEntity(new GenericType<Map<String, Object>>() {}).get("suggestions");
        return suggestions.toArray(new String[suggestions.size()]);
    }

    /**
     * Given one or more keywords, this method will return a list of keywords ordered by descending popularity which are commonly used with the given keyword(s).
     * @param words one or more keywords
     * @return a list of keywords commonly associated with the keywords given in the words parameter
     * @see <a href="http://venturocket.com/api/v1#keywords_suggestions">http://venturocket.com/api/v1#keywords_suggestions</a>
     */
    public String[] getSuggestions(String... words){
        return getSuggestions(Joiner.on(',').skipNulls().join(words));
    }
}
