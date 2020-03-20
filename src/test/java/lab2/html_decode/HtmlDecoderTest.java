package lab2.html_decode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlDecoderTest {
    @Test
    public void decodesForSampleLine() {
        final String input = "Cat &lt;says&gt; &quot;Meow&quot;. M&amp;M&apos;s";
        final String expected = "Cat <says> \"Meow\". M&M's";
        final String output = HtmlDecoder.decode(input);
        assertEquals(expected, output);
    }
}
