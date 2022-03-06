package tech.xigam.elixirapi.requests.playlist;

import tech.xigam.elixirapi.Bot;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.responses.PlaylistResponse;

import java.util.function.Consumer;

public final class FetchPlaylistRequest extends PlaylistRequest {
    private final String playlist;

    public FetchPlaylistRequest(
            ElixirAPI api, Bot bot, String playlist
    ) {
        super(api, bot); // Set the Elixir API.
        this.playlist = playlist; // Set the playlist.
    }

    @Override
    public void execute(Consumer<PlaylistResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.GET)
                .endpoint("playlist/fetch")
                .argument("bot", this.bot.getBotId())
                .argument("id", this.playlist)
                .build();
        request.execute(res -> response.accept(new PlaylistResponse(res.getResponse(), res.getResponseCode())));
    }

    public static class Builder extends PlaylistRequest.Builder {
        public Builder(ElixirAPI api) {
            super(api);
        }

        @Override
        public FetchPlaylistRequest build() {
            if (this.bot == null) this.bot = this.api.preferredBot();
            return new FetchPlaylistRequest(this.api, this.bot, this.playlist);
        }
    }
}
