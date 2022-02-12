package tech.xigam.elixirapi.requests.player;

import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.interfaces.PlayerResponse;
import tech.xigam.elixirapi.responses.GenericPlayerResponse;

import java.util.function.Consumer;

public final class PauseRequest extends PlayerRequest {
    private final String guild;
    
    public PauseRequest(
            ElixirAPI api, String guild
    ) {
        super(api); // Set the Elixir API.
        this.guild = guild; // Set the guild.
    }

    @Override
    public void execute(Consumer<PlayerResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.POST)
                .endpoint("pause")
                .argument("guild", this.guild)
                .build();
        request.execute(res -> new GenericPlayerResponse(res.getResponse(), res.getResponseCode()));
    }
    
    public static class Builder extends PlayerRequest.Builder {
        public Builder(ElixirAPI api) {
            super(api);
        }

        @Override
        public PlayerRequest build() throws RequestBuildException {
            return new PauseRequest(api, guild);
        }
    }
}
