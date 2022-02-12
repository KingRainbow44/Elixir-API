package tech.xigam.elixirapi.responses;

import com.google.gson.Gson;
import tech.xigam.elixirapi.Response;
import tech.xigam.elixirapi.interfaces.StandardResponse;
import tech.xigam.elixirapi.objects.ResponseTimestamp;
import tech.xigam.elixirapi.objects.TrackCollection;
import tech.xigam.elixirapi.objects.TrackObject;

import java.util.List;

public final class PlaylistResponse extends Response implements StandardResponse {
    public PlaylistResponse(String response, int responseCode) {
        super(response, responseCode);
    }
    
    public TrackCollection getAsTrackCollection() {
        return TrackCollection.from(new Gson().fromJson(
                this.getResponse(),
                JsonResponse.class
        ).data.tracks);
    }
    
    public PlaylistData getAsPlaylistData() {
        return new Gson().fromJson(
                this.getResponse(),
                JsonResponse.class
        ).data;
    }
    
    public static class JsonResponse {
        public int status;
        public String messages;
        public ResponseTimestamp timestamps;
        public PlaylistData data;
    }
    
    public static class PlaylistData {
        public Info info;
        public List<TrackObject> tracks;
        
        public static class Info {
            public String id, name, description, playlistCoverUrl, author;
            public int volume;
        }
    }
}
