package lab2.html_decode;

enum HtmlEntities {
    QUOT("&quot;"),
    APOS("&apos;"),
    LT("&lt;"),
    GT("&gt;"),
    AMP("&amp;");
    private final String text;

    HtmlEntities(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}