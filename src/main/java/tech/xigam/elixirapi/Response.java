package tech.xigam.elixirapi;

import tech.xigam.elixirapi.interfaces.StandardResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Response implements StandardResponse {
    private String response;
    private final int responseCode;
    
    public Response(InputStream inputStream, int responseCode) {
        var reader = new BufferedReader(
                new InputStreamReader(inputStream)
        ); this.read(reader);
        
        this.responseCode = responseCode;
    }
    
    public Response(String response, int responseCode) {
        this.response = response;
        this.responseCode = responseCode;
    }
    
    private void read(BufferedReader reader) {
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.response = builder.toString();
    }
    
    @Override
    public String getResponse() {
        return this.response;
    }
    
    @Override
    public int getResponseCode() {
        return this.responseCode;
    }
}
