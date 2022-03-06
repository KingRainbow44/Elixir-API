package tech.xigam.elixirapi.requests.queue;

import tech.xigam.elixirapi.Bot;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.responses.QueueResponse;

import java.util.function.Consumer;

public final class ShuffleRequest extends QueueRequest {
    private final String guild;

    public ShuffleRequest(
            ElixirAPI api, Bot bot, String guild
    ) {
        super(api, bot); // Set the Elixir API.
        this.guild = guild; // Set the guild.
    }

    @Override
    public void execute(Consumer<QueueResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.POST)
                .endpoint("shuffle")
                .argument("guild", this.guild)
                .build();
        request.execute(res -> response.accept(new QueueResponse(res.getResponse(), res.getResponseCode())));
    }

    public static class Builder extends QueueRequest.Builder {
        public Builder(ElixirAPI api) {
            super(api);
        }

        @Override
        public ShuffleRequest build() {
            if (this.bot == null) this.bot = this.api.preferredBot();
            return new ShuffleRequest(this.api, this.bot, this.guild);
        }
    }
}
