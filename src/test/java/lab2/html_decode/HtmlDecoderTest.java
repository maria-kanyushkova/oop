package lab2.html_decode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlDecoderTest {
    @Test
    public void shouldDecodesLine() {
        final String input = "Cat &lt;says&gt; &quot;Meow&quot;. M&amp;M&apos;s";
        final String expected = "Cat <says> \"Meow\". M&M's";
        final String output = HtmlDecoder.decodeString(input);
        assertEquals(expected, output);
    }

    @Test
    public void shouldDecodeWithoutAnotherSpecialSymbol() {
        final String input = "&#43;&#56;";
        final String expected = "&#43;&#56;";
        final String output = HtmlDecoder.decodeString(input);
        assertEquals(expected, output);
    }
}
