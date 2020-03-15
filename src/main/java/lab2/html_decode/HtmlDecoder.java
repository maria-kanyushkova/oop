package lab2.html_decode;

public class HtmlDecoder {
    public static String decode(String value) {
        final HtmlDictionary dictionary = new HtmlDictionary();
        return value.replaceAll(HtmlEntities.QUOT.toString(), dictionary.getDecodedQuot())
                .replaceAll(HtmlEntities.APOS.toString(), dictionary.getDecodedApos())
                .replaceAll(HtmlEntities.LT.toString(), dictionary.getDecodedLt())
                .replaceAll(HtmlEntities.GT.toString(), dictionary.getDecodedGt())
                .replaceAll(HtmlEntities.AMP.toString(), dictionary.getDecodedAmp());
    }
}
