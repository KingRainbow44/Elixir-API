package tech.xigam.elixirapi.requests;

import com.google.gson.Gson;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.Response;
import tech.xigam.elixirapi.objects.TrackObject;

import java.util.Base64;
import java.util.function.Consumer;

public final class PlayerRequest {
    private final ElixirAPI api;
    private final String guildId, track;
    private final Action action;
    
    private PlayerRequest(
            ElixirAPI api, String guildId, Action action,
            String track
    ) {
        this.api = api; this.guildId = guildId; this.action = action;
        this.track = track;
    }
    
    public void execute(Consumer<PlayerResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.GET)
                .endpoint("player")
                .argument("action", this.action.toString().toLowerCase())
                .argument("guildId", this.guildId);
        if(!this.track.isEmpty())
            request.argument("query", this.track);
        request.build().execute(res -> response.accept(new PlayerResponse(res.getResponse(), res.getResponseCode())));
    }
    
    public void execute() {
        this.execute(response -> { });
    }
    
    public static class Builder {
        private final ElixirAPI api;
        
        private String guildId = "";
        private Action action = Action.NOWPLAYING;
        private String query = "";
        
        public Builder(ElixirAPI api) {
            this.api = api;
        }
        
        public Builder guild(String guildId) {
            this.guildId = guildId; return this;
        }
        
        public Builder action(Action action) {
            this.action = action; return this;
        }

        /**
         * This automatically Base64 encodes the query.
         * @param query The query to search for.
         * @return The search request.
         */
        public Builder track(String query) {
            this.query = Base64.getUrlEncoder().encodeToString(query.getBytes());
            return this;
        }
        
        public PlayerRequest build() {
            return new PlayerRequest(this.api, this.guildId, this.action, this.query);
        }
    }
    
    public static class PlayerResponse extends Response {
        public PlayerResponse(String response, int responseCode) {
            super(response, responseCode);
        }
        
        public TrackObject getAsTrack() {
            return new Gson().fromJson(this.getResponse(), TrackObject.class);
        }

        /**
         * For some objects, the response is Base64 encoded.
         * This accounts for that.
         * @return The decoded response.
         */
        public TrackObject decodeTrackObject() {
            return new Gson().fromJson(
                    new String(Base64.getUrlDecoder().decode(this.getResponse())),
                    TrackObject.class
            );
        }
        
        public TrackObject[] getAllTracks() {
            return new Gson().fromJson(
                    new String(Base64.getUrlDecoder().decode(this.getResponse())), 
                    TrackObject[].class
            );
        }
    }
    
    public enum Action {
        NOWPLAYING,
        PAUSE, RESUME, SKIP, PLAY
    }
}
