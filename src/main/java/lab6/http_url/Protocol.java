package lab6.http_url;

public enum Protocol {
    HTTP("http"),
    HTTPS("https");

    private String text;

    Protocol(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}
