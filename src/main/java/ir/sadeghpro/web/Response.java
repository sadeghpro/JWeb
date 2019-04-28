package ir.sadeghpro.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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

    public <T> T deserializeJsonObject(Class<T> type){
        return new Gson().fromJson(body,type);
    }
    public <T> List<T> deserializeJsonArray(Class<T> type){
        Type arrayType = TypeToken.getParameterized(List.class, type).getType();
        return new Gson().fromJson(body,arrayType);
    }
}
