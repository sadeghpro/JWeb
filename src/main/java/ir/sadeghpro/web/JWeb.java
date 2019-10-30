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

    public JWeb() {

    }

    public JWeb(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public JWeb(Proxy defaultProxy) {
        this.defaultProxy = defaultProxy;
    }

    public JWeb(String defaultUrl, Proxy defaultProxy) {
        this.defaultUrl = defaultUrl;
        this.defaultProxy = defaultProxy;
    }

    public JWeb(int timeout) {
        this.timeout = timeout;
    }

    public JWeb(String defaultUrl, Proxy defaultProxy, int timeout) {
        this.defaultUrl = defaultUrl;
        this.defaultProxy = defaultProxy;
        this.timeout = timeout;
    }

    public JWeb(boolean autoCookie) {
        this.autoCookie = autoCookie;
    }

    public JWeb(String defaultUrl, Proxy defaultProxy, Boolean autoCookie, int timeout) {
        this.defaultUrl = defaultUrl;
        this.defaultProxy = defaultProxy;
        this.autoCookie = autoCookie;
        this.timeout = timeout;
    }

    public Map<String, String> getDefaultHeaders() {
        return defaultHeaders;
    }

    public JWeb setDefaultHeaders(Map<String, String> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
        return this;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public JWeb setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public JWeb setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public Proxy getDefaultProxy() {
        return defaultProxy;
    }

    public JWeb setDefaultProxy(Proxy defaultProxy) {
        this.defaultProxy = defaultProxy;
        return this;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public JWeb setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
        return this;
    }

    public JWeb addCookie(String key, String value) {
        cookies.put(key, value);
        return this;
    }

    public JWeb addAllCookies(Map<String, String> cookies) {
        this.cookies.putAll(cookies);
        return this;
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
