package lab6.http_url;

import java.util.Scanner;

public class EventLoop {
    private static String getMenuInfo() {
        return "0. help - выводится информация о командах\n" +
                "1. url\n" +
                "2. domain protocol\n" +
                "3. domain document protocol\n" +
                "4. domain document protocol port\n" +
                "5. exit - выход с приложения";
    }

    private static String[] parseCommandLine(String line) {
        return line.split(" ");
    }

    public static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static boolean isStringNumber(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int convertToNumber(String number) throws UrlParsingError {
        if (!isStringNumber(number)) {
            throw new UrlParsingError("Illegal value");
        }
        return Integer.parseInt(number);
    }

    public static int getPort(String port) throws UrlParsingError {
        return convertToNumber(port);
    }

    public static Protocol getProtocol(String protocol) {
        return protocol.equals(Protocol.HTTP.toString()) ? Protocol.HTTP : Protocol.HTTPS;
    }

    public static String getUrlInfo(HttpUrl url) {
        return "url: " + url.getUrl() + "\n" +
                "protocol: " + url.getProtocol().toString() + "\n" +
                "domain: " + url.getDomain() + "\n" +
                "port: " + url.getPort() + "\n" +
                "document: " + url.getDocument();
    }

    public void run() {
        System.out.println(getMenuInfo());
        while (true) {
            try {
                final String consoleLine = readFromConsole();
                final String[] args = parseCommandLine(consoleLine);
                String result = this.runCommand(args);
                if (result.isEmpty()) {
                    return;
                }
                System.out.println(result);
            } catch (UrlParsingError error) {
                System.out.println(error.getLocalizedMessage());
            }
        }
    }

    private String runCommand(String[] args) throws UrlParsingError {
        if (args[0].equals("help")) {
            return getMenuInfo();
        } else if (args[0].equals("exit")) {
            return "";
        } else if (args.length == 1) {
            String url = args[0];
            return getUrlInfo(new HttpUrl(url));
        } else if (args.length == 2) {
            String domain = args[0];
            Protocol protocol = getProtocol(args[1]);
            return getUrlInfo(new HttpUrl(domain, protocol));
        } else if (args.length == 3) {
            String domain = args[0];
            String document = args[1];
            Protocol protocol = getProtocol(args[2]);
            return getUrlInfo(new HttpUrl(domain, document, protocol));
        } else if (args.length == 4) {
            String domain = args[0];
            String document = args[1];
            Protocol protocol = getProtocol(args[2]);
            int port = getPort(args[3]);
            return getUrlInfo(new HttpUrl(domain, document, protocol, port));
        } else {
            throw new UrlParsingError("Invalid arguments count");
        }
    }
}
