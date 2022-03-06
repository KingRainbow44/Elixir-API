package tech.xigam.elixirapi.responses;

import com.google.gson.Gson;
import tech.xigam.elixirapi.Response;
import tech.xigam.elixirapi.interfaces.PlayerResponse;
import tech.xigam.elixirapi.objects.ResponseTimestamp;
import tech.xigam.elixirapi.objects.TrackCollection;
import tech.xigam.elixirapi.objects.TrackObject;

import java.util.List;

public final class QueueResponse extends Response implements PlayerResponse {
    public QueueResponse(String response, int responseCode) {
        super(response, responseCode);
    }

    public TrackCollection getAsTrackCollection() {
        return TrackCollection.from(new Gson().fromJson(
                this.getResponse(),
                JsonResponse.class
        ).queue);
    }

    public static class JsonResponse {
        public int status;
        public List<TrackObject> queue;
        public ResponseTimestamp timestamps;
    }
}
