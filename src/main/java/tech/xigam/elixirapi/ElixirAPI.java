package tech.xigam.elixirapi;

public final class ElixirAPI {
    public static String ENDPOINT_URL = "https://elixir.ponjo.club/";
    
    public static ElixirAPI create(String apiKey) {
        return new ElixirAPI(apiKey);
    }
    
    private final String apiKey;
    
    private ElixirAPI(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getApiKey() {
        return this.apiKey;
    }
}
