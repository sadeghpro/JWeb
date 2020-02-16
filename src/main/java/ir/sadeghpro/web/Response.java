package ir.sadeghpro.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    private int statusCode;
    private InputStream body;
    private Map<String, List<String>> headers;
    private Map<String, String> cookies = new HashMap<>();

    public Response(int statusCode, InputStream body, Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
        if (headers.get("Set-Cookie") != null) {
            cookies.putAll(JWeb.parseCookie(headers));
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        StringBuilder response = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(body, "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public InputStream getBodyInputStream() {
        return body;
    }

    public void setBody(InputStream body) {
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
        return new Gson().fromJson(getBody(), type);
    }

    public <T> List<T> deserializeJsonArray(Class<T> type) {
        Type arrayType = TypeToken.getParameterized(List.class, type).getType();
        return new Gson().fromJson(getBody(), arrayType);
    }
}
