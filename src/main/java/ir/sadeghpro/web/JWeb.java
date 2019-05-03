package ir.sadeghpro.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class JWeb {

    private static Map<String, String> defaultHeaders = new HashMap<>();
    private static String defaultUrl = "";
    private static int timeout = 0;


    public static Map<String, String> getDefaultHeaders() {
        return defaultHeaders;
    }

    public static void setDefaultHeaders(Map<String, String> defaultHeaders) {
        JWeb.defaultHeaders = defaultHeaders;
    }

    public static String getDefaultUrl() {
        return defaultUrl;
    }

    public static void setDefaultUrl(String defaultUrl) {
        JWeb.defaultUrl = defaultUrl;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        JWeb.timeout = timeout;
    }

    public static Connection connect(String url) throws MalformedURLException {
        return new Connection(defaultUrl + url);
    }

    public static Connection connect(URL url) {
        return new Connection(url);
    }
}
