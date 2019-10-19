package ir.sadeghpro.web;

import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWeb {

    private Map<String, String> defaultHeaders = new HashMap<>();
    private String defaultUrl = "";
    private Proxy defaultProxy;
    private Boolean autoCookie = false;
    private Map<String, String> cookies = new HashMap<>();
    private int timeout = 0;


    public Map<String, String> getDefaultHeaders() {
        return defaultHeaders;
    }

    public void setDefaultHeaders(Map<String, String> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Proxy getDefaultProxy() {
        return defaultProxy;
    }

    public void setDefaultProxy(Proxy defaultProxy) {
        this.defaultProxy = defaultProxy;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public void addCookie(String key, String value) {
        cookies.put(key, value);
    }

    public void addAllCookies(Map<String, String> cookies) {
        this.cookies.putAll(cookies);
    }

    public Boolean isAutoCookie() {
        return autoCookie;
    }

    public JWeb setAutoCookie(Boolean autoCookie) {
        this.autoCookie = autoCookie;
        return this;
    }

    public Connection connect(String url) throws MalformedURLException {
        return new Connection(defaultUrl + url).setJWeb(this);
    }

    public Connection connect(URL url) {
        return new Connection(url).setJsonBody(this);
    }

    static Map<String, String> parseCookie(Map<String, List<String>> headers) {
        Map<String, String> cookies = new HashMap<>();
        for (String cookie : headers.get("Set-Cookie")) {
            String[] split = cookie.split(";")[0].split("=");
            cookies.put(split[0], split[1]);
        }
        return cookies;
    }
}
