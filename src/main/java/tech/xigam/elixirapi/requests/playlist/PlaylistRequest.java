package tech.xigam.elixirapi.requests.playlist;

import tech.xigam.elixirapi.Bot;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.responses.PlaylistResponse;

import java.util.function.Consumer;

public abstract class PlaylistRequest {
    protected final ElixirAPI api;
    protected final Bot bot;

    public PlaylistRequest(ElixirAPI api, Bot bot) {
        this.api = api;
        this.bot = bot;
    }

    public abstract void execute(Consumer<PlaylistResponse> response);

    public static class Builder {
        protected final ElixirAPI api;

        protected String guild = "";
        protected String playlist = "";
        protected Bot bot = null;

        public Builder(ElixirAPI api) {
            this.api = api;
        }

        public Builder guild(String guild) {
            this.guild = guild;
            return this;
        }

        public Builder playlist(String playlist) {
            this.playlist = playlist;
            return this;
        }

        public Builder bot(Bot bot) {
            this.bot = bot;
            return this;
        }

        public PlaylistRequest build() throws RequestBuildException {
            throw new RequestBuildException();
        }
    }
}
