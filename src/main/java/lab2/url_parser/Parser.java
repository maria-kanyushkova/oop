package lab2.url_parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Parser {
    private static final int MAX_PORT = 65535;

    private final String text;

    private String host;
    private int port;
    private String doc;
    private String hash;

    Parser(final String text) {
        this.text = text;
    }

    Parser parse() throws IOException, URISyntaxException {
        final URL url = new URL(text.trim());
        final URI uri = new URI(text.trim());
        host = url.getHost();
        port = getPortByProtocol(url.getProtocol(), url.getPort());
        doc = url.getFile().replaceFirst("/", "");
        hash = uri.getFragment();
        return this;
    }

    String print() {
        return text + '\n' +
                "HOST: " + host + '\n' +
                "PORT: " + port +
                printIfNotNull("\nDOC: ", doc, printIfNotNull("#", hash, ""));
    }

    private int getPortByProtocol(String protocol, int portFromUrl) throws IOException {
        if (portFromUrl != -1) {
            if (portFromUrl > MAX_PORT) {
                throw new IOException("Unsupported port \"" + portFromUrl + "\"");
            }
            return portFromUrl;
        }
        if (Protocol.HTTP.is(protocol)) {
            return Protocol.HTTP.getPort();
        } else if (Protocol.HTTPS.is(protocol)) {
            return Protocol.HTTPS.getPort();
        } else if (Protocol.FTP.is(protocol)) {
            return Protocol.FTP.getPort();
        } else {
            throw new IOException("Unknown protocol \"" + protocol + "\"");
        }
    }

    private String printIfNotNull(String prefix, String string, String postfix) {
        if (string != null && !string.isEmpty()) {
            return prefix + string + postfix;
        } else {
            return "";
        }
    }
}
