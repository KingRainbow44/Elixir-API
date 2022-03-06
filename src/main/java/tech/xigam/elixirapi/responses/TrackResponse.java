package tech.xigam.elixirapi.responses;

import tech.xigam.elixirapi.Response;
import tech.xigam.elixirapi.interfaces.PlayerResponse;
import tech.xigam.elixirapi.objects.TrackObject;

public abstract class TrackResponse extends Response implements PlayerResponse {
    public TrackResponse(String response, int responseCode) {
        super(response, responseCode);
    }

    public abstract TrackObject getAsTrack();
}
