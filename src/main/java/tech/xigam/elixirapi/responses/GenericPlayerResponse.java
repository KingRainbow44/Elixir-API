package tech.xigam.elixirapi.responses;

import com.google.gson.Gson;
import tech.xigam.elixirapi.Response;
import tech.xigam.elixirapi.interfaces.PlayerResponse;

public class GenericPlayerResponse extends Response implements PlayerResponse {
    public GenericPlayerResponse(String response, int responseCode) {
        super(response, responseCode);
    }

    public String getMessage() {
        return new Gson().fromJson(
                this.getResponse(),
                JsonResponse.class
        ).message;
    }

    public static class JsonResponse {
        public int status;
        public String message;
        public Timestamps timestamps;

        public static class Timestamps {
            public String date;
            public long unix;
        }
    }
}
