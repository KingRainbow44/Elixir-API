package tech.xigam.elixirapi.objects;

import java.util.List;

public final class PlaylistObject {
    public Info info;
    public List<CustomPlaylistTrack> tracks;
    public Options options;

    public static class Info {
        public String id, name, description, playlistCoverUrl, author;
        public int volume;
    }

    public static class CustomPlaylistTrack {
        public String title, url, artist, coverArt;
        public long duration;
        public String isrc;
    }

    public static class Options {
        public boolean shuffle = false, repeat = false;
    }
}
