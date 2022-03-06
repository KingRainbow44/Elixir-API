package tech.xigam.elixirapi.requests.player;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import tech.xigam.elixirapi.Bot;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.interfaces.PlayerResponse;
import tech.xigam.elixirapi.objects.TrackObject;
import tech.xigam.elixirapi.responses.TrackResponse;

import java.util.function.Consumer;

public final class GetPlayingTrackRequest extends PlayerRequest {
    private final String guild;

    public GetPlayingTrackRequest(
            ElixirAPI api, Bot bot, String guild
    ) {
        super(api, bot); // Set the Elixir API.
        this.guild = guild; // Set the guild.
    }

    public void execute(Consumer<PlayerResponse> response) {
        var request = new Request.Builder(this.api)
                .method(Request.Method.GET)
                .endpoint("nowplaying")
                .argument("bot", this.bot.getBotId())
                .argument("guild", this.guild)
                .build();
        request.execute(res -> response.accept(new Response(res.getResponse(), res.getResponseCode())));
    }

    public static class Builder extends PlayerRequest.Builder {
        public Builder(ElixirAPI api) {
            super(api);
        }

        @Override
        public GetPlayingTrackRequest build() {
            if (this.bot == null) this.bot = this.api.preferredBot();
            return new GetPlayingTrackRequest(this.api, this.bot, this.guild);
        }
    }

    public static class Response extends TrackResponse {
        public Response(String response, int responseCode) {
            super(response, responseCode);
        }

        @Override
        public TrackObject getAsTrack() {
            var jsonObject = JsonParser.parseString(this.getResponse()).getAsJsonObject();
            return new Gson().fromJson(
                    jsonObject.get("nowplaying"),
                    TrackObject.class
            );
        }
    }
}
