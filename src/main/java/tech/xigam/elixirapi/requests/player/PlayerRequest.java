package tech.xigam.elixirapi.requests.player;

import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.interfaces.PlayerResponse;

import java.util.function.Consumer;

public abstract class PlayerRequest {
    protected final ElixirAPI api;
    
    public PlayerRequest(ElixirAPI api) {
        this.api = api;
    }
    
    public abstract void execute(Consumer<PlayerResponse> response);
    
    public static class Builder {
        protected final ElixirAPI api;

        protected String guild = "";
        protected String query = "";

        public Builder(ElixirAPI api) {
            this.api = api;
        }

        public Builder guild(String guild) {
            this.guild = guild; return this;
        }

        public PlayerRequest build() throws RequestBuildException {
            throw new RequestBuildException();
        }
    }
}
