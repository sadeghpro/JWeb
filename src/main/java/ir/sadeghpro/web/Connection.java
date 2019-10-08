package ir.sadeghpro.web;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class Connection {

    private Map<String, String> headers = new HashMap<>();
    private Method method = Method.GET;
    private URL url;
    private String body;
    private int timeout = 0;
    private JWeb jWeb;
    private Proxy proxy;

    public Connection(URL url) {
        this.url = url;
    }

    public Connection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Connection setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public Connection addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public Connection setMethod(Method method) {
        this.method = method;
        return this;
    }

    public URL getUrl() {
        return url;
    }

    public Connection setUrl(URL url) {
        this.url = url;
        return this;
    }

    public Connection setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
        return this;
    }

    public String getBody() {
        return body;
    }

    public Connection setBody(String body) {
        this.body = body;
        return this;
    }

    public <T> Connection setJsonBody(T object) {
        this.body = new Gson().toJson(object);
        return this;
    }

    public Connection setFormBody(Map<String, String> form) {
        StringBuilder body = new StringBuilder();
        form.forEach((k, v) -> {
            body.append(k).append("=").append(v).append("&");
        });
        this.body = body.toString();
        this.body = this.body.substring(0, this.body.length() - 1);
        return this;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public Connection setProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public Connection setProxy(String host, int port) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        return setProxy(proxy);
    }

    public int getTimeout() {
        return timeout;
    }

    public Connection setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public JWeb getJWeb() {
        return jWeb;
    }

    protected Connection setJWeb(JWeb jWeb) {
        this.jWeb = jWeb;
        return this;
    }

    public Response exec() throws IOException, TimeoutException {
        if (jWeb.getTimeout() == 0 && timeout == 0) {
            return _exec();
        } else {
            AtomicReference<Response> r = new AtomicReference<>();
            AtomicReference<IOException> ex = new AtomicReference<>();
            Thread t = new Thread(() -> {
                try {
                    Response a = _exec();
                    r.set(a);
                } catch (IOException e) {
                    ex.set(e);
                }
            });
            t.start();
            int time = timeout == 0 ? jWeb.getTimeout() : timeout;
            try {
                t.join(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ex.get() != null) {
                throw ex.get();
            }
            if (r.get() == null) {
                throw new TimeoutException();
            }
            return r.get();
        }
    }

    private Response _exec() throws IOException {
        Proxy proxy = null;
        if (this.proxy != null) {
            proxy = this.proxy;
        } else if (jWeb.getDefaultProxy() != null) {
            proxy = jWeb.getDefaultProxy();
        }

        HttpURLConnection con;
        if (proxy == null) {
            con = (HttpURLConnection) url.openConnection();
        } else {
            con = (HttpURLConnection) url.openConnection(proxy);
        }
        int time = timeout == 0 ? jWeb.getTimeout() : timeout;
        con.setConnectTimeout(time);
        con.setReadTimeout(time);
        con.setRequestMethod(method.toString());

        Map<String, String> headers = jWeb.getDefaultHeaders();
        headers.putAll(this.headers);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }

        if (body != null && !body.isEmpty()) {
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes("UTF-8"));
            wr.flush();
            wr.close();
        }

        int responseCode = con.getResponseCode();

        InputStream stream;
        if (responseCode >= HttpURLConnection.HTTP_BAD_REQUEST) {
            con.connect();
            stream = con.getErrorStream();
        } else {
            stream = con.getInputStream();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\n");
        }
        in.close();
        return new Response(responseCode, response.toString());
    }
}
