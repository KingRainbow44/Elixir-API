package tech.xigam.elixirapi.requests.queue;

import tech.xigam.elixirapi.Bot;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.responses.QueueResponse;

import java.util.function.Consumer;

public abstract class QueueRequest {
    protected final ElixirAPI api;
    protected final Bot bot;
    
    public QueueRequest(ElixirAPI api, Bot bot) {
        this.api = api; this.bot = bot;
    }
    
    public abstract void execute(Consumer<QueueResponse> response);
    
    public static class Builder {
        protected final ElixirAPI api;

        protected String guild = "";
        protected Bot bot = null;

        public Builder(ElixirAPI api) {
            this.api = api;
        }

        public Builder guild(String guild) {
            this.guild = guild; return this;
        }

        public Builder bot(Bot bot) {
            this.bot = bot; return this;
        }
        
        public QueueRequest build() throws RequestBuildException {
            throw new RequestBuildException();
        }
    }
}
