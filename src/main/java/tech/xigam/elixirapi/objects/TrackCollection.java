package tech.xigam.elixirapi.objects;

import java.util.ArrayList;
import java.util.List;

public final class TrackCollection {
    public List<TrackObject> tracks = new ArrayList<>();

    public static TrackCollection from(List<TrackObject> tracks) {
        TrackCollection collection = new TrackCollection();
        collection.tracks = tracks;
        return collection;
    }
}
