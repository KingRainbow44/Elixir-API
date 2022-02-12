package tech.xigam.elixirapi.objects;

import java.util.Objects;

public final class TrackObject {
    public String title, author;
    public long length;
    public String identifier;
    public boolean isStream;
    public String uri, url;
    public long position;
    
    public String getUrl() {
        return Objects.requireNonNullElse(uri, url);
    }
}
