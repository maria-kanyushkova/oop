package lab2.url_parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Parser {
    private static final int MAX_PORT = 65535;

    public static String parse(final String text) throws IOException, URISyntaxException {
        URL url = new URL(text.trim());
        URI uri = new URI(text.trim());
        String host = url.getHost();
        int port = getPortByProtocol(url.getProtocol(), url.getPort());
        String doc = url.getFile().replaceFirst("/", "");
        String hash = uri.getFragment();

        return text + '\n' +
                "HOST: " + host + '\n' +
                "PORT: " + port +
                concatString("\nDOC: ", doc, concatString("#", hash, ""));
    }

    private static int getPortByProtocol(String protocol, int portFromUrl) throws IOException {
        if (portFromUrl != -1) {
            if (portFromUrl > MAX_PORT) {
                throw new IOException("Unsupported port \"" + portFromUrl + "\"");
            }
            return portFromUrl;
        }
        if (protocol.equals(Protocol.HTTP.getProtocol())) {
            return Protocol.HTTP.getPort();
        }
        if (protocol.equals(Protocol.HTTPS.getProtocol())) {
            return Protocol.HTTPS.getPort();
        }
        if (protocol.equals(Protocol.FTP.getProtocol())) {
            return Protocol.FTP.getPort();
        }
        throw new IOException("Unknown protocol \"" + protocol + "\"");
    }

    private static String concatString(String prefix, String string, String postfix) {
        if (string != null && !string.isEmpty()) {
            return prefix + string + postfix;
        } else {
            return "";
        }
    }
}
