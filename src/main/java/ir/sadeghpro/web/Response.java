package ir.sadeghpro.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    private int statusCode;
    private String body;
    private Map<String, List<String>> headers;
    private Map<String, String> cookies = new HashMap<>();

    public Response(int statusCode, String body, Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
        cookies.putAll(JWeb.parseCookie(headers));
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public <T> T deserializeJsonObject(Class<T> type) {
        return new Gson().fromJson(body, type);
    }

    public <T> List<T> deserializeJsonArray(Class<T> type) {
        Type arrayType = TypeToken.getParameterized(List.class, type).getType();
        return new Gson().fromJson(body, arrayType);
    }
}
