package lab6.http_url;

import java.time.temporal.ValueRange;

public class HttpUrl {
    private String url = "";
    private String domain = "";
    private String document = "";
    private Protocol protocol;
    private int port = 0;

    public HttpUrl(String url) throws UrlParsingError {
        this.url = url;
        this.protocol = convertToProtocol();
        this.domain = verifyDomain(parseDomain());
        this.port = verifyPort(parsePort());
        this.document = verifyDocument(parseDocument());
    }

    public HttpUrl(String domain, Protocol protocol) throws UrlParsingError {
        this.protocol = verifyProtocol(protocol);
        this.domain = verifyDomain(domain);
        this.port = verifyPort(port);
    }

    public HttpUrl(String domain, String document) throws UrlParsingError {
        this.protocol = verifyProtocol(Protocol.HTTP);
        this.domain = verifyDomain(domain);
        this.document = verifyDocument(document);
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

    private Protocol convertToProtocol(String protocol) throws UrlParsingError {
        switch (protocol) {
            case "http":
                return Protocol.HTTP;
            case "https":
                return Protocol.HTTPS;
            default:
                throw new UrlParsingError("Invalid protocol");
        }
    }

    private Protocol convertToProtocol() throws UrlParsingError {
        final String schemeDelimiter = "://";
        if (!url.contains(schemeDelimiter)) {
            throw new UrlParsingError("Protocol parsing error");
        }
        int endPosition = url.indexOf(schemeDelimiter);
        String protocol = url.substring(0, endPosition);
        url = url.substring(endPosition + schemeDelimiter.length());
        return convertToProtocol(protocol);
    }

    private String parseDomain() {
        int domainPos = url.indexOf(":");
        if (domainPos == -1) {
            domainPos = url.indexOf("/");
            domainPos = domainPos == -1 ? url.length() : domainPos;
        }
        String domain = url.substring(0, domainPos);
        url = url.substring(domainPos);
        return domain;
    }

    private int parsePort() throws UrlParsingError {
        ValueRange accessPort = ValueRange.of(0, 65535);
        if (url.length() == 0) {
            return 0;
        }
        if (url.charAt(0) == ':') {
            int portPos = url.indexOf('/');
            String portString = portPos == -1 ? url.substring(1) : url.substring(1, portPos);
            url = url.substring(portString.length() + 1);
            boolean portOk = !portString.isEmpty();
            if (portOk) {
                int port = EventLoop.convertToNumber(portString);
                if (accessPort.isValidValue(port)) {
                    return port;
                }
            }
            throw new UrlParsingError("Port parsing error");
        }
        return 0;
    }

    private String parseDocument() {
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
        if (document.length() == 0) {
            return "/";
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
