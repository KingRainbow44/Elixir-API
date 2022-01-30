package tech.xigam.elixirapi.interfaces;

import tech.xigam.elixirapi.Response;

import java.io.InputStream;

public interface StandardResponse {
    static StandardResponse create(InputStream inputStream, int responseCode) {
        return new Response(inputStream, responseCode);
    }
    
    String getResponse();
    
    int getResponseCode();
}
