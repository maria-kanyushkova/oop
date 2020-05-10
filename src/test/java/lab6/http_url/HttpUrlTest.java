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
            @DisplayName("can_pass_url_without_exceptions")
            public void test1() throws UrlParsingError {
                new HttpUrl("http://github.com/document.txt");
            }

            @Test
            @DisplayName("if_document_is_empty_class_do_not_throw_exception")
            public void test8() throws UrlParsingError {
                new HttpUrl("http://github.com/");
                new HttpUrl("http://github.com");
            }

            @Test
            @DisplayName("if_url_contains_port_then_port_will_be_different_from_standard")
            public void test12() throws UrlParsingError {
                new HttpUrl("http://github.com:14/document.txt");
            }

            @Test
            @DisplayName("if_the_URL_contains_the_correct_port_class_do_not_throw_exception")
            public void test9() throws UrlParsingError {
                new HttpUrl("http://github.com:443/document.txt");
            }

            @Test
            @DisplayName("if_scheme_incorrect_then_class_throw_exception")
            public void test2() {
                String expected = "Invalid protocol";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("htts://github.com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("if_scheme_incorrect_then_class_throw_exception")
            public void test3() {
                String expected = "Protocol parsing error";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http:/github.com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("if_scheme_incorrect_then_class_throw_exception")
            public void test4() {
                String expected = "Domain name is empty";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http:///github.com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("if_scheme_correct_but_protocol_type_not_supported_class_throw_exception")
            public void test6() {
                String expected = "Invalid protocol";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("ftp://github.com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("if_the_domain_contains_spaces_and_tabs_then_class_throw_exception")
            public void test7() {
                String expected = "Domain mustn't contain spaces, tabulation or slash";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http://github .com/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("if_the_URL_port_is_out_of_range")
            public void test10() {
                String expected = "Port parsing error";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http://github.com:999999/document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("if_the_URL_contains_the_uncorrect_port_then_class_throw_exception")
            public void test11() {
                String expected = "Port parsing error";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("http://github.com:/document.txt"));
                assertEquals(expected, exception.getMessage());
            }
        }

        @Nested
        @DisplayName("constructor(domain, document, protocol, ?port)")
        class ParameterizedConstructor {
            @Test
            @DisplayName("can_not_pass_an_invalid_document")
            public void test21() throws UrlParsingError {
                new HttpUrl("github.com", "document.txt");
            }

            @Test
            @DisplayName("can_not_pass_an_invalid_document")
            public void test22() throws UrlParsingError {
                new HttpUrl("github.com", Protocol.HTTP);
            }

            @Test
            @DisplayName("can_not_pass_an_invalid_document")
            public void test23() throws UrlParsingError {
                new HttpUrl("github.com", "document.txt", Protocol.HTTP);
            }

            @Test
            @DisplayName("can_not_pass_an_invalid_document")
            public void test24() throws UrlParsingError {
                new HttpUrl("github.com", "document.txt", Protocol.HTTP, 25);
            }

            @Test
            @DisplayName("can_not_pass_an_invalid_domain_name")
            public void test25() {
                String expected = "Domain name is empty";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("", Protocol.HTTP));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("can_not_pass_an_invalid_domain_name")
            public void test27() {
                String expected = "Domain mustn't contain spaces, tabulation or slash";
                exception = assertThrows(UrlParsingError.class, () -> new HttpUrl(" ", "document.txt"));
                assertEquals(expected, exception.getMessage());
            }

            @Test
            @DisplayName("can_not_pass_an_domain_name_with_scheme_or_slash")
            public void test28() {
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
        @DisplayName("")
        public void test31() throws UrlParsingError {
            String expected = "http://github.com/document.txt";
            assertEquals(expected, new HttpUrl("http://github.com/document.txt").getUrl());
        }

        @Test
        @DisplayName("")
        public void test32() throws UrlParsingError {
            String expected = "http://github.com:458/document.txt";
            assertEquals(expected, new HttpUrl("http://github.com:458/document.txt").getUrl());
        }

        @Test
        @DisplayName("")
        public void test33() throws UrlParsingError {
            String expected = "http://github.com/document.txt";
            assertEquals(expected, new HttpUrl("http://github.com:80/document.txt").getUrl());
        }
    }

    @Nested
    @DisplayName("Method: getDomain()")
    class GetDomain {
        @Test
        @DisplayName("")
        public void test41() throws UrlParsingError {
            assertEquals("github.com", new HttpUrl("https://github.com/").getDomain());
        }
    }

    @Nested
    @DisplayName("Method: getDocument()")
    class GetDocument {
        @Test
        @DisplayName("")
        public void test51() throws UrlParsingError {
            assertEquals("/", new HttpUrl("https://github.com/").getDocument());
        }

        @Test
        @DisplayName("")
        public void test52() throws UrlParsingError {
            assertEquals("/", new HttpUrl("https://github.com").getDocument());
        }

        @Test
        @DisplayName("")
        public void test53() throws UrlParsingError {
            assertEquals("/document.txt", new HttpUrl("http://github.com/document.txt").getDocument());
        }

        @Test
        @DisplayName("")
        public void test54() {
            String expected = "Document mustn't contain spaces or tabulation";
            exception = assertThrows(UrlParsingError.class, () -> new HttpUrl("github.github.com", " "));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Method: getProtocol()")
    class GetProtocol {
        @Test
        @DisplayName("can_not_pass_an_invalid_document")
        public void test61() throws UrlParsingError {
            assertEquals(Protocol.HTTP, new HttpUrl("github.com", "document.txt").getProtocol());
        }

        @Test
        @DisplayName("can_not_pass_an_invalid_document")
        public void test62() throws UrlParsingError {
            assertEquals(Protocol.HTTPS, new HttpUrl("github.com", "document.txt", Protocol.HTTPS).getProtocol());
        }

        @Test
        @DisplayName("can_not_pass_an_invalid_document")
        public void test63() {
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
        @DisplayName("")
        public void test51() throws UrlParsingError {
            assertEquals(14, new HttpUrl("http://github.com:14/document.txt").getPort());
        }

        @Test
        @DisplayName("")
        public void test52() throws UrlParsingError {
            assertEquals(80, new HttpUrl("http://github.com").getPort());
        }

        @Test
        @DisplayName("")
        public void test53() throws UrlParsingError {
            assertEquals(443, new HttpUrl("https://github.com").getPort());
        }
    }
}
