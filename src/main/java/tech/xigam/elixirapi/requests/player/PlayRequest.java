package tech.xigam.elixirapi.requests.player;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.interfaces.PlayerResponse;
import tech.xigam.elixirapi.objects.TrackObject;
import tech.xigam.elixirapi.responses.GenericPlayerResponse;
import tech.xigam.elixirapi.responses.TrackResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Consumer;

public final class PlayRequest extends PlayerRequest {
    private final String guild, query;
    
    public PlayRequest(
            ElixirAPI api, String guild, String query
    ) {
        super(api); // Set the Elixir API.
        this.guild = guild; // Set the guild.
        this.query = query; // Set the query.
    }

    @Override
    public void execute(Consumer<PlayerResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.POST)
                .endpoint("play")
                .argument("guild", this.guild)
                .argument("query", this.query)
                .build();
        request.execute(res -> new GenericPlayerResponse(res.getResponse(), res.getResponseCode()));
    }
    
    public static class Builder extends PlayerRequest.Builder {
        public Builder(ElixirAPI api) {
            super(api);
        }

        /**
         * This automatically Base64 encodes the query.
         * @param query The query to search for.
         * @return The search request.
         */
        public Builder track(String query) {
            if(this.api.shouldUseBase64()) {
                this.query = Base64.getUrlEncoder().encodeToString(query.getBytes());
            } else {
                this.query = URLEncoder.encode(query, StandardCharsets.UTF_8);
            }
            return this;
        }
        
        @Override
        public PlayerRequest build() throws RequestBuildException {
            return new PlayRequest(this.api, this.guild, this.query);
        }
    }
    
    public static class Response extends TrackResponse {
        public Response(String response, int responseCode) {
            super(response, responseCode);
        }

        @Override
        public TrackObject getAsTrack() {
            var jsonObject = JsonParser.parseString(this.getResponse()).getAsJsonObject();
            return new Gson().fromJson(
                    jsonObject.get("data"),
                    TrackObject.class
            );
        }
    }
}
