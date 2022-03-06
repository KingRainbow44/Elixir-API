package tech.xigam.elixirapi;

public enum Bot {
    ELIXIR_MUSIC("838118537276031006"),
    ELIXIR_PREMIUM("937394940009414696"),
    ELIXIR_TWO("946122707844603974"),
    ELIXIR_BLUE("946443578849243156");

    final String botId;

    Bot(String botId) {
        this.botId = botId;
    }

    public String getBotId() {
        return botId;
    }
}
