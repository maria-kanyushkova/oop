package lab2.html_decode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlDecoderTest {
    @Test
    public void shouldDecodesSampleLine() {
        final String input = "Cat &lt;says&gt; &quot;Meow&quot;. M&amp;M&apos;s";
        final String expected = "Cat <says> \"Meow\". M&M's";
        final String output = HtmlDecoder.decode(input);
        assertEquals(expected, output);
    }

    @Test
    public void shouldDecodeComplexLine() {
        final String input = "&quot;&quot;&quot;Hello, &apos;World&apos;!&quot;&quot;&quot;&quot;";
        final String expected = "\"\"\"Hello, 'World'!\"\"\"\"";
        final String output = HtmlDecoder.decode(input);
        assertEquals(expected, output);
    }

    @Test
    public void shouldDecodeSpecialSymbols() {
        final String input = "&quot;&apos;&lt;&gt;&amp;&lt;&gt;&apos;&quot;";
        final String expected = "\"'<>&<>'\"";
        final String output = HtmlDecoder.decode(input);
        assertEquals(expected, output);
    }

    @Test
    public void shouldDecodeWithoutAnotherSpecialSymbol() {
        final String input = "&quot;&apos;&lt;&gt;&amp;&lt;&gt;&apos;&quot;&#43;";
        final String expected = "\"'<>&<>'\"&#43;";
        final String output = HtmlDecoder.decode(input);
        assertEquals(expected, output);
    }
}
