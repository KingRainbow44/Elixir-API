package tech.xigam.elixirapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class Request {
    private final ElixirAPI api;
    private final String url;
    private final Method method;
    private final Map<String, String> arguments;
    
    private Request(ElixirAPI api, String endpoint, Method method, Map<String, String> arguments) throws MalformedURLException {
        this.api = api;
        this.url = ElixirAPI.ENDPOINT_URL + endpoint;
        this.method = method;
        this.arguments = arguments;
    }
    
    private URL buildUrl() {
        try {
            StringBuilder builder = new StringBuilder(this.url + "?apiKey=" + this.api.getApiKey());
            for(var entry : this.arguments.entrySet()) {
                builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            } return new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } return null;
    }
    
    public void execute(Consumer<Response> callback) {
        try {
            var url = this.buildUrl(); if(url == null) {
                callback.accept(null); return;
            }
            var connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setRequestMethod(this.method.toString());
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            // Set headers.
            connection.setRequestProperty("Authorization", "Bearer " + this.api.getApiKey());

            // Get response & execute callback.
            var response = new Response(connection.getInputStream(), connection.getResponseCode());
            callback.accept(response); connection.disconnect();
        } catch (IOException exception) {
            exception.printStackTrace();
            callback.accept(new Response((InputStream) null, 404));
        }
    }
    
    public static class Builder {
        private final ElixirAPI api;
        private final Map<String, String> arguments = 
                new HashMap<>();
        
        private String endpoint;
        private Method method;
        
        public Builder(ElixirAPI apiInstance) {
            this.api = apiInstance;
        }
        
        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint; return this;
        }
        
        public Builder method(Method method) {
            this.method = method; return this;
        }
        
        public Builder argument(String key, String value) {
            this.arguments.put(key, value); return this;
        }
        
        public Request build() {
            try {
                return new Request(this.api, this.endpoint, this.method, this.arguments);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } return null;
        }
    }
    
    public enum Method {
        GET, POST, PUT, DELETE
    }
}
