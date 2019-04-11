package ir.sadeghpro.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class JWeb {

    private static Map<String, String> defaultHeaders = new HashMap<>();


    public static Map<String, String> getDefaultHeaders() {
        return defaultHeaders;
    }

    public static void setDefaultHeaders(Map<String, String> defaultHeaders) {
        JWeb.defaultHeaders = defaultHeaders;
    }

    public static Connection connect(String url) throws MalformedURLException {
        return new Connection(url);
    }

    public static Connection connect(URL url) {
        return new Connection(url);
    }
}
