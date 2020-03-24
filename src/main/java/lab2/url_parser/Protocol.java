package lab2.url_parser;

import java.util.Objects;

enum Protocol {
    HTTP("http", 80),
    HTTPS("https", 443),
    FTP("ftp", 21);
    private final String text;
    private final int port;

    Protocol(String text, int port) {
        this.text = text;
        this.port = port;
    }

    int getPort() {
        return port;
    }

    boolean is(String expectedProtocol) {
        return Objects.equals(text, expectedProtocol);
    }
}