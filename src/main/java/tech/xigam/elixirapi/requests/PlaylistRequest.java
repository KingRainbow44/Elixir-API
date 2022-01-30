package tech.xigam.elixirapi.requests;

import com.google.gson.Gson;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.Response;
import tech.xigam.elixirapi.objects.PlaylistObject;

import java.util.Base64;
import java.util.function.Consumer;

public final class PlaylistRequest {
    private final ElixirAPI api;
    private final String playlistId, track, guildId, channelId;
    private final Action action;

    private PlaylistRequest(
            ElixirAPI api, String playlistId, Action action,
            String track, String guildId, String channelId
    ) {
        this.api = api; this.playlistId = playlistId; this.action = action;
        this.track = track; this.guildId = guildId; this.channelId = channelId;
    }

    public void execute(Consumer<QueueResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.GET)
                .endpoint("playlist")
                .argument("action", this.action.toString().toLowerCase())
                .argument("playlistId", this.playlistId);
        switch (this.action) {
            case ADDTRACK -> request.argument("track", this.track);
            case QUEUE -> request.argument("guildId", this.guildId)
                    .argument("channelId", this.channelId);
        }
        request.build().execute(res -> response.accept(new QueueResponse(res.getResponse(), res.getResponseCode())));
    }

    public static class Builder {
        private final ElixirAPI api;

        private String playlistId = "";
        private Action action = Action.QUEUE;
        private String track = "", guildId = "", channelId = "";

        public Builder(ElixirAPI api) {
            this.api = api;
        }

        public Builder playlist(String playlistId) {
            this.playlistId = playlistId; return this;
        }

        public Builder action(Action action) {
            this.action = action; return this;
        }
        
        public Builder track(String track) {
            this.track = track; return this;
        }
        
        public Builder guild(String guildId) {
            this.guildId = guildId; return this;
        }
        
        public Builder channel(String channelId) {
            this.channelId = channelId; return this;
        }

        public PlaylistRequest build() {
            return new PlaylistRequest(
                    this.api, this.playlistId, this.action,
                    this.track, this.guildId, this.channelId
            );
        }
    }

    public static class QueueResponse extends Response {
        public QueueResponse(String response, int responseCode) {
            super(response, responseCode);
        }

        public PlaylistObject getAsPlaylist() {
            return new Gson().fromJson(
                    new String(Base64.getUrlDecoder().decode(this.getResponse())),
                    PlaylistObject.class
            );
        }
    }

    public enum Action {
        FETCH, ADDTRACK, QUEUE
    }
}
