package lab2.url_parser;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void shouldParseWorksCorrectlyMediumSize() throws IOException, URISyntaxException {
        final String input = "http://www.mysite.com/docs/document1.html?page=30&lang=en#title";
        final Parser parser = new Parser(input);
        final String expected = "http://www.mysite.com/docs/document1.html?page=30&lang=en#title\n" +
                "HOST: www.mysite.com\n" +
                "PORT: 80\n" +
                "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void shouldParseWorksCorrectlyLongSize() throws IOException, URISyntaxException {
        final String input = "https://raw.githubusercontent.com/IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML";
        final Parser parser = new Parser(input);
        final String expected = "https://raw.githubusercontent.com/IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML\n" +
                "HOST: raw.githubusercontent.com\n" +
                "PORT: 443\n" +
                "DOC: IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void shouldParseWorksCorrectlyShortSize() throws IOException, URISyntaxException {
        final String input = "https://habr.com";
        final Parser parser = new Parser(input);
        final String expected = "https://habr.com\n" +
                "HOST: habr.com\n" +
                "PORT: 443";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void shouldParseWorksCorrectlyWithQueries() throws IOException, URISyntaxException {
        final String input = "ftp://www.mysite.com/docs/document1.html?page=30&lang=en#title";
        final Parser parser = new Parser(input);
        final String expected = "ftp://www.mysite.com/docs/document1.html?page=30&lang=en#title\n" +
                "HOST: www.mysite.com\n" +
                "PORT: 21\n" +
                "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void shouldParseWorksCorrectlyWithQueriesAndCustomPort() throws IOException, URISyntaxException {
        final String input = "ftp://www.mysite.com:1337/docs/document1.html?page=30&lang=en#title";
        final Parser parser = new Parser(input);
        final String expected = "ftp://www.mysite.com:1337/docs/document1.html?page=30&lang=en#title\n" +
                "HOST: www.mysite.com\n" +
                "PORT: 1337\n" +
                "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void shouldParseThrowsIfInputIsIncorrect() {
        final String input = "habr";
        final Parser parser = new Parser(input);
        assertThrows(MalformedURLException.class, parser::parse);
    }

    @Test
    public void shouldParseThrowsOnUnknownProtocol() {
        final String input = "ws://unknown.protocol.com";
        final Parser parser = new Parser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void shouldParseThrowsOnIncorrectUrl() {
        final String input = "http://";
        final Parser parser = new Parser(input);
        assertThrows(URISyntaxException.class, parser::parse);
    }

    @Test
    public void shouldParseThrowsIfNoProtocol() {
        final String input = "habr.com";
        final Parser parser = new Parser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void shouldParseThrowsIfPortIsMore65535() {
        final String input = "http://habr.com:70000";
        final Parser parser = new Parser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void shouldParseThrowsIfPortIsNegative() {
        final String input = "http://habr.com:-2/test.pdf";
        final Parser parser = new Parser(input);
        assertThrows(MalformedURLException.class, parser::parse);
    }
}