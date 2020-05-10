package lab6.http_url;

import java.time.temporal.ValueRange;

public class HttpUrl {
    private String domain = "";
    private String document = "";
    private Protocol protocol = null;
    private int port = 0;

    public HttpUrl(String url) throws UrlParsingError {
        Protocol protocol = parseProtocol(url);
        String domain = verifyDomain(parseDomain(url));
        int port = verifyPort(parsePort(url));
        String document = verifyDocument(parseDocument(url));

        this.protocol = protocol;
        this.domain = domain;
        this.document = document;
        this.port = port;
    }

    public HttpUrl(String domain, Protocol protocol) throws UrlParsingError {
        this.protocol = verifyProtocol(protocol);
        this.domain = verifyDomain(domain);
        this.port = verifyPort(port);
    }

    public HttpUrl(String domain, String document, Protocol protocol) throws UrlParsingError {
        this.protocol = verifyProtocol(protocol);
        this.domain = verifyDomain(domain);
        this.document = verifyDocument(document);
        this.port = verifyPort(port);
    }

    public HttpUrl(String domain, String document, Protocol protocol, int port) throws UrlParsingError {
        this.protocol = verifyProtocol(protocol);
        this.domain = verifyDomain(domain);
        this.document = verifyDocument(document);
        this.port = verifyPort(port);
    }

    public String getUrl() {
        boolean isStandardPort = (port == 80 || port == 443);
        return protocol.toString() + "://" + domain + (isStandardPort ? "" : ":" + port) + document;
    }

    public String getDomain() {
        return domain;
    }

    public String getDocument() {
        return document;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public int getPort() {
        return port;
    }

    private Protocol stringToProtocol(String protocol) throws UrlParsingError {
        switch (protocol) {
            case "http":
                return Protocol.HTTP;
            case "https":
                return Protocol.HTTPS;
            default:
                throw new UrlParsingError("Invalid protocol");
        }
    }

    private Protocol parseProtocol(String url) throws UrlParsingError {
        final String schemeDelimiter = "://";
        if (!url.contains(schemeDelimiter)) {
            throw new UrlParsingError("Protocol parsing error");
        }
        int endPosition = url.indexOf(schemeDelimiter);
        String protocol = url.substring(0, endPosition); // todo: проверить что это точно так
        url = url.substring(endPosition + schemeDelimiter.length(), url.length() - 1); // todo: проверить что это точно так
        return stringToProtocol(protocol);
    }

    private String parseDomain(String url) {
        int domainPos = url.indexOf(":");
        if (domainPos == -1) { // todo: проверить != или ==
            domainPos = url.indexOf("/");
            domainPos = domainPos == -1 ? url.length() : domainPos;
        }
        String domain = url.substring(0, domainPos);
        url = url.substring(domainPos); // todo: проверить что это точно так OR url = url.substring(domainPos, url.length() - 1);
        return domain;
    }

    private int parsePort(String url) throws UrlParsingError {
        ValueRange accessPort = ValueRange.of(0, 65535);
        if (url.charAt(0) == ':') {
            int portPos = url.indexOf('/');
            String portString;
            if (portPos == -1) {
                portString = url.substring(1);
            } else {
                portString = url.substring(1, portPos - 1);
            }
            url = url.substring(portString.length() + 1);
            boolean portOk = !portString.isEmpty();
            int port = Integer.parseInt(portString);
            if (portOk && accessPort.isValidValue(port)) {
                return port;
            }
            throw new UrlParsingError("Port parsing error");
        }
        return 0;
    }

    private String parseDocument(String url) {
        return url;
    }

    private String verifyDomain(String domain) throws UrlParsingError {
        if (domain.isEmpty()) {
            throw new UrlParsingError("Domain name is empty");
        } else if (domain.contains(" ") || domain.contains("/") || domain.contains("'")) {
            throw new UrlParsingError("Domain mustn't contain spaces, tabulation or slash");
        }
        return domain;
    }

    private String verifyDocument(String document) throws UrlParsingError {
        if (document.contains(" ")) {
            throw new UrlParsingError("Document mustn't contain spaces or tabulation");
        }
        if (document.charAt(0) != '/') {
            return '/' + document;
        }
        return document;
    }

    private Protocol verifyProtocol(Protocol protocol) throws UrlParsingError {
        if (protocol == Protocol.HTTP || protocol == Protocol.HTTPS) {
            return protocol;
        }
        throw new UrlParsingError("Invalid protocol");
    }

    private int verifyPort(int port) {
        if (port == 0) {
            port = (protocol == Protocol.HTTP ? 80 : 443);
        }
        return port;
    }
}
