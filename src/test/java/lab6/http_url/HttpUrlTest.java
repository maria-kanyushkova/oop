package lab6.http_url;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpUrlTest {
    private UrlParsingError exception;

    @Nested
    @DisplayName("Constructor")
    class Constructor {
        @Nested
        @DisplayName("constructor(url)")
        class FullUrlConstructor {
            @Test
            @DisplayName("should be define HttpUrl")
            public void shouldBeDefineHttpUrl() throws UrlParsingError {
                new HttpUrl("http://github.com/document.txt");
            }

            @Test
            @DisplayName("should to define HttpUrl with empty document")
            public void shouldToDefineHttpUrlWithEmptyDocument() throws UrlParsingError {
                new HttpUrl("http://github.com/");
                new HttpUrl("http://github.com");
            }

            @Test
            @DisplayName("should to define HttpUrl with document and port")
            public void shouldToDefineHttpUrlWithDocumentAndPort() throws UrlParsingError {
                new HttpUrl("http://github.com:14/document.txt");
            }

            @Test
            @DisplayName("try define HttpUrl if protocol incorrect")
            public void tryDefineHttpUrlIfProtocolIncorrect() {
                String expected = "Invalid protocol";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("htts://github.com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("try define HttpUrl if protocol symbols incorrect")
            public void tryDefineHttpUrlIfProtocolSymbolsIncorrect() {
                String expected = "Protocol parsing error";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http:/github.com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("try define HttpUrl if protocol and domain symbols incorrect")
            public void tryDefineHttpUrlIfProtocolAndDomainSymbolsIncorrect() {
                String expected = "Domain name is empty";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http:///github.com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("try define HttpUrl if protocol is not existing")
            public void tryDefineHttpUrlIfProtocolIsNotExisting() {
                String expected = "Invalid protocol";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("ftp://github.com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("try define HttpUrl if domain symbols incorrect")
            public void tryDefineHttpUrlIfDomainSymbolsIncorrect() {
                String expected = "Domain mustn't contain spaces, tabulation or slash";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http://github .com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("try define HttpUrl if port symbols incorrect")
            public void tryDefineHttpUrlIfPortSymbolsIncorrect() {
                String expected = "Port parsing error";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http://github.com:999999/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("try define HttpUrl if port is not existing")
            public void tryDefineHttpUrlIfPortIsNotExisting() {
                String expected = "Port parsing error";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http://github.com:/document.txt"));
                assertEquals(expected, exception.getMessage());
            }
        }

        @Nested
        @DisplayName("constructor(domain, document, protocol, ?port)")
        class ParameterizedConstructor {
            @Test
            @DisplayName("should to define HttpUrl with exist domain and document")
            public void shouldToDefineHttpUrlWithExistDomainAndDocument() throws UrlParsingError {
                new HttpUrl("github.com", "document.txt");
            }

            @Test
            @DisplayName("should to define HttpUrl with exist domain and protocol")
            public void shouldToDefineHttpUrlWithExistDomainAndProtocol() throws UrlParsingError {
                new HttpUrl("github.com", Protocol.HTTP);
            }

            @Test
            @DisplayName("should to define HttpUrl with exist domain, document and protocol")
            public void shouldToDefineHttpUrlWithExistDomainDocumentAndProtocol() throws UrlParsingError {
                new HttpUrl("github.com", "document.txt", Protocol.HTTP);
            }

            @Test
            @DisplayName("should to define HttpUrl with exist domain, document, protocol and port")
            public void shouldToDefineHttpUrlWithExistDomainDocumentProtocolAndPort() throws UrlParsingError {
                new HttpUrl("github.com", "document.txt", Protocol.HTTP, 25);
            }

            @Test
            @DisplayName("try define HttpUrl if domain name is empty")
            public void tryDefineHttpUrlIfDomainNameIsEmpty() {
                String expected = "Domain name is empty";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("", Protocol.HTTP));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("try define HttpUrl if domain name is incorrect")
            public void tryDefineHttpUrlIfDomainNameIsIncorrect() {
                String expected = "Domain mustn't contain spaces, tabulation or slash";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl(" ", "document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("try define HttpUrl if in domain name exist protocol with exist protocol param")
            public void tryDefineHttpUrlIfInDomainNameExistProtocolWithExistProtocolParam() {
                String expected = "Domain mustn't contain spaces, tabulation or slash";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("https://github.github.com", Protocol.HTTP));
                assertEquals(expected, exception.getMessage());
            }
        }
    }

    @Nested
    @DisplayName("Method: getUrl()")
    class GetUrl {
        @Test
        @DisplayName("should get url")
        public void shouldGetUrl() throws UrlParsingError {
            String expected = "http://github.com/document.txt";
            assertEquals(expected, new HttpUrl("http://github.com/document.txt").getUrl());
        }

        @Test
        @DisplayName("should get url with port")
        public void shouldGetUrlWithPort() throws UrlParsingError {
            String expected = "http://github.com:458/document.txt";
            assertEquals(expected, new HttpUrl("http://github.com:458/document.txt").getUrl());
        }

        @Test
        @DisplayName("should get url with hidden port")
        public void shouldGetUrlWithHiddenPort() throws UrlParsingError {
            String expected = "http://github.com/document.txt";
            assertEquals(expected, new HttpUrl("http://github.com:80/document.txt").getUrl());
        }
    }

    @Nested
    @DisplayName("Method: getDomain()")
    class GetDomain {
        @Test
        @DisplayName("should get domain name")
        public void shouldGetDomainName() throws UrlParsingError {
            assertEquals("github.com", new HttpUrl("https://github.com/").getDomain());
        }
    }

    @Nested
    @DisplayName("Method: getDocument()")
    class GetDocument {
        @Test
        @DisplayName("should get document")
        public void shouldGetDocument() throws UrlParsingError {
            assertEquals("/document.txt", new HttpUrl("http://github.com/document.txt").getDocument());
        }

        @Test
        @DisplayName("should get document if exist slash symbol")
        public void shouldGetDocumentIfExistSlashSymbol() throws UrlParsingError {
            assertEquals("/", new HttpUrl("https://github.com/").getDocument());
        }

        @Test
        @DisplayName("shouldGetDocumentIfNotExistSlashSymbol")
        public void shouldGetDocumentIfNotExistSlashSymbol() throws UrlParsingError {
            assertEquals("/", new HttpUrl("https://github.com").getDocument());
        }


        @Test
        @DisplayName("try get document if document contain illegal symbols")
        public void tryGetDocumentIfDocumentContainIllegalSymbols() {
            String expected = "Document mustn't contain spaces or tabulation";
            exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("github.github.com", " "));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Method: getProtocol()")
    class GetProtocol {
        @Test
        @DisplayName("should get protocol")
        public void shouldGetProtocol() throws UrlParsingError {
            assertEquals(Protocol.HTTPS, new HttpUrl("github.com", "document.txt", Protocol.HTTPS).getProtocol());
        }

        @Test
        @DisplayName("should get default protocol")
        public void shouldGetDefaultProtocol() throws UrlParsingError {
            assertEquals(Protocol.HTTP, new HttpUrl("github.com", "document.txt").getProtocol());
        }


        @Test
        @DisplayName("try get protocol if protocol not defined")
        public void tryGetProtocolIfProtocolNotDefined() {
            Protocol protocol = null;
            String expected = "Invalid protocol";
            exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("github.github.com", protocol));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Method: getPort()")
    class GetPort {
        @Test
        @DisplayName("should get port")
        public void shouldGetPort() throws UrlParsingError {
            assertEquals(14, new HttpUrl("http://github.com:14/document.txt").getPort());
        }

        @Test
        @DisplayName("should get default port for http")
        public void shouldGetDefaultPortForHttp() throws UrlParsingError {
            assertEquals(80, new HttpUrl("http://github.com").getPort());
        }

        @Test
        @DisplayName("should get default port for https")
        public void shouldGetDefaultPortForHttps() throws UrlParsingError {
            assertEquals(443, new HttpUrl("https://github.com").getPort());
        }
    }
}
