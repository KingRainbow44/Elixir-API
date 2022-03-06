package tech.xigam.elixirapi;

public final class ElixirAPI {
    public static String ENDPOINT_URL = "https://app.ponjo.club/v1/elixir/";
    private final String apiKey;
    private boolean useBase64 = false;
    private Bot preferredBot = Bot.ELIXIR_MUSIC;
    private ElixirAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Creates a basic instance of the Elixir API.
     * Additional settings can be manually set.
     *
     * @param apiKey The Ponjo API key used to make requests.
     * @return An instance of the Elixir API.
     */
    public static ElixirAPI create(String apiKey) {
        return new ElixirAPI(apiKey);
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public ElixirAPI setBase64Usage(boolean useBase64) {
        this.useBase64 = useBase64;
        return this;
    }

    public boolean shouldUseBase64() {
        return this.useBase64;
    }

    public ElixirAPI setPreferredBot(Bot preferredBot) {
        this.preferredBot = preferredBot;
        return this;
    }

    public Bot preferredBot() {
        return this.preferredBot;
    }
}