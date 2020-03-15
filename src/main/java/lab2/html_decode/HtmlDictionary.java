package lab2.html_decode;

import java.util.HashMap;
import java.util.Map;

public class HtmlDictionary {
    private static final Map<String, String> dictionary = new HashMap<>();

    HtmlDictionary() {
        dictionary.put(HtmlEntities.QUOT.toString(), "\"");
        dictionary.put(HtmlEntities.APOS.toString(), "\'");
        dictionary.put(HtmlEntities.LT.toString(), "<");
        dictionary.put(HtmlEntities.GT.toString(), ">");
        dictionary.put(HtmlEntities.AMP.toString(), "&");
    }

    public String getDecodedQuot() {
        return dictionary.get(HtmlEntities.QUOT.toString());
    }

    public String getDecodedApos() {
        return dictionary.get(HtmlEntities.APOS.toString());
    }

    public String getDecodedLt() {
        return dictionary.get(HtmlEntities.LT.toString());
    }

    public String getDecodedGt() {
        return dictionary.get(HtmlEntities.GT.toString());
    }

    public String getDecodedAmp() {
        return dictionary.get(HtmlEntities.AMP.toString());
    }
}
