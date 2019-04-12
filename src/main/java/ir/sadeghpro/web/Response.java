package ir.sadeghpro.web;

import com.google.gson.Gson;

public class Response {

    private int statusCode;
    private String body;

    public Response(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
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

    public <T> T deserializeJsonBody(Class<T> type){
        return new Gson().fromJson(body,type);
    }
}
