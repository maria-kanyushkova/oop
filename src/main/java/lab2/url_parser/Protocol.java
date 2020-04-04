package lab2.url_parser;

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

    String getProtocol() {
        return text;
    }

    int getPort() {
        return port;
    }
}