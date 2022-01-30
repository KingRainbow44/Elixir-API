package tech.xigam.elixirapi.requests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.Response;
import tech.xigam.elixirapi.objects.TrackCollection;
import tech.xigam.elixirapi.objects.TrackObject;

import java.util.Base64;
import java.util.function.Consumer;

public final class QueueRequest {
    private final ElixirAPI api;
    private final String guildId;
    private final Action action;
    
    private QueueRequest(
            ElixirAPI api, String guildId, Action action
    ) {
        this.api = api; this.guildId = guildId; this.action = action;
    }
    
    public void execute(Consumer<QueueResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.GET)
                .endpoint("queue")
                .argument("action", this.action.toString().toLowerCase())
                .argument("guildId", this.guildId)
                .build();
        request.execute(res -> response.accept(new QueueResponse(res.getResponse(), res.getResponseCode())));
    }
    
    public static class Builder {
        private final ElixirAPI api;

        private String guildId = "";
        private Action action = Action.QUEUE;
        
        public Builder(ElixirAPI api) {
            this.api = api;
        }
        
        public Builder guild(String guildId) {
            this.guildId = guildId; return this;
        }
        
        public Builder action(Action action) {
            this.action = action; return this;
        }
        
        public QueueRequest build() {
            return new QueueRequest(this.api, this.guildId, this.action);
        }
    }
    
    public static class QueueResponse extends Response {
        public QueueResponse(String response, int responseCode) {
            super(response, responseCode);
        }
        
        public TrackCollection getAsCollection() {
            var collection = new TrackCollection();
            var array = new Gson().fromJson(
                    new String(Base64.getUrlDecoder().decode(this.getResponse())),
                    JsonArray.class
            ); array.forEach(element -> collection.tracks.add(
                    new Gson().fromJson(element, TrackObject.class)
            ));
            
            return collection;
        }
    }
    
    public enum Action {
        QUEUE
    }
}
