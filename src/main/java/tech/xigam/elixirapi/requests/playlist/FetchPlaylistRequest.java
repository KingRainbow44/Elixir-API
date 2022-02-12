package tech.xigam.elixirapi.requests.playlist;

import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.responses.PlaylistResponse;

import java.util.function.Consumer;

public final class FetchPlaylistRequest extends PlaylistRequest {
    private final String guild, playlist;

    public FetchPlaylistRequest(
            ElixirAPI api, String guild, String playlist
    ) {
        super(api); // Set the Elixir API.
        this.guild = guild; // Set the guild.
        this.playlist = playlist; // Set the playlist.
    }

    @Override
    public void execute(Consumer<PlaylistResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.GET)
                .endpoint("queue")
                .argument("guild", this.guild)
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
            return new FetchPlaylistRequest(this.api, this.guild, this.playlist);
        }
    }
}