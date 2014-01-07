package com.venturocket.api.client.test.keyword;

import com.venturocket.api.client.keyword.KeywordClient;
import com.venturocket.api.client.keyword.object.Keyword;
import com.venturocket.api.client.test.BaseTest;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 6:17 PM
 */
public class KeywordClientTest extends BaseTest{
    protected KeywordClient client;

    @Before
    public void setUp(){
        client = new KeywordClient(getApiKey(), getApiSecret());
    }

    @Test
    public void testGetKeyword(){
        String word = "electrical engineer";

        Keyword keyword = client.getKeyword(word);

        // Ensure that the correct Keyword was returned
        TestCase.assertEquals(word, keyword.word);

        // This Keyword should be valid
        TestCase.assertTrue(keyword.valid);

        // This Keyword should have at least one synonym
        TestCase.assertTrue(keyword.synonyms.length > 0);
    }

    @Test
    public void testGetSuggestions(){
        String[] suggestions = client.getSuggestions("php", "python", "java");

        TestCase.assertTrue(suggestions.length > 1);
    }

    @Test
    public void testParseKeywords(){
        String text = "We are looking for rock star Web Developer with expertise in Javascript and PHP. This is a great opportunity to define and implement interactive technologies that will have a positive social impact on millions of users. You will be part of a passionate and high energy team that is creating compelling, new and innovative online technologies. The ideal candidate will have experience building and working with ecommerce and dynamic image generation. This position requires someone who thrives on finding innovative solutions to real problems.\n" +
                "Job Responsibilities:\n" +
                "Work with 3rd party shopping cart solutions by enabling users around the globe to make payments\n" +
                "Work on innovative email security platform to enable users communicate privately\n" +
                "Design, develop and test new software products while also supporting existing commercial products\n" +
                "Develop related applications/utilities, widgets, and help build out our platform\n" +
                "Work with web proxies, enhancing content delivery performance\n" +
                "Provide creativity and new, fresh ideas\n" +
                "Job Qualifications:\n" +
                "Proven experience in developing frameworks and UI for web-based payments: authentication, account management, integration with payment gateways, transaction history, credits and refunds.\n" +
                "Recent hands-on with LAMP (Linux, Apache, MySql, PHP) and open source development\n" +
                "Expertise in HTML, CSS, DOM, AJAX; deep understanding of cookies, client-side storage.\n" +
                "Experience with cross-platform, cross-browser development\n" +
                "A track record of delivering quality bug-free code on schedule\n" +
                "Preferable Job Qualifications:\n" +
                "Experience with web proxies\n" +
                "JavaScript experience\n" +
                "Web Design Skills (Photoshop, Illustrator)\n" +
                "Open Source contribution is a plus\n" +
                "BS in Computer Science or equivalent job experience";

        String[] keywords = client.parseKeywords(text);

        List<String> keywordsList = Arrays.asList(keywords);

        TestCase.assertTrue(keywordsList.contains("php"));
    }
}
