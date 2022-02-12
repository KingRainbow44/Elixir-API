package tech.xigam.elixirapi.requests.playlist;

import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.responses.PlaylistResponse;

import java.util.function.Consumer;

public final class QueuePlaylistRequest extends PlaylistRequest {
    private final String guild, channel, playlist;

    public QueuePlaylistRequest(
            ElixirAPI api, String guild, String channel,
            String playlist
    ) {
        super(api); // Set the Elixir API.
        this.guild = guild; // Set the guild.
        this.channel = channel; // Set the channel.
        this.playlist = playlist; // Set the playlist.
    }

    @Override
    public void execute(Consumer<PlaylistResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.POST)
                .endpoint("queue")
                .argument("guild", this.guild)
                .argument("channel", this.channel)
                .argument("id", this.playlist)
                .build();
        request.execute(res -> response.accept(new PlaylistResponse(res.getResponse(), res.getResponseCode())));
    }

    public static class Builder extends PlaylistRequest.Builder {
        private String channel;
        
        public Builder(ElixirAPI api) {
            super(api);
        }
        
        public Builder channel(String channel) {
            this.channel = channel; return this;
        }

        @Override
        public QueuePlaylistRequest build() {
            return new QueuePlaylistRequest(this.api, this.guild, this.channel, this.playlist);
        }
    }
}
