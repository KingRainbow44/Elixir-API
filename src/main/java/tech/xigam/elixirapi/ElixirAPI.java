package tech.xigam.elixirapi;

public final class ElixirAPI {
    public static String ENDPOINT_URL = "https://app.ponjo.club/v1/elixir/";

    /**
     * Creates a basic instance of the Elixir API.
     * Additional settings can be manually set.
     * @param apiKey The Ponjo API key used to make requests.
     * @return An instance of the Elixir API.
     */
    public static ElixirAPI create(String apiKey) {
        return new ElixirAPI(apiKey);
    }
    
    private final String apiKey;
    private boolean useBase64 = false;
    
    private ElixirAPI(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getApiKey() {
        return this.apiKey;
    }
    
    public ElixirAPI setBase64Usage(boolean useBase64) {
        this.useBase64 = useBase64; return this;
    }
    
    public boolean shouldUseBase64() {
        return this.useBase64;
    }
}
