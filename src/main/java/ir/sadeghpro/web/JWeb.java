package ir.sadeghpro.web;

import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class JWeb {

    private Map<String, String> defaultHeaders = new HashMap<>();
    private String defaultUrl = "";
    private Proxy defaultProxy;
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

    public Connection connect(String url) throws MalformedURLException {
        return new Connection(defaultUrl + url).setJWeb(this);
    }

    public Connection connect(URL url) {
        return new Connection(url).setJsonBody(this);
    }
}
