package tech.xigam.tests;

import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.Request;
import tech.xigam.elixirapi.requests.PlayerRequest;
import tech.xigam.elixirapi.requests.PlaylistRequest;
import tech.xigam.elixirapi.requests.QueueRequest;

public final class Environment {
    
    public static void main(String[] args) {
        ElixirAPI.ENDPOINT_URL = "http://localhost:1000/";
        
        playingExample();
    }
    
    public static void rawRequestExample() {
        var elixir = ElixirAPI.create("(API KEY)");
        var request = new Request.Builder(elixir)
                .endpoint("player")
                .method(Request.Method.GET)
                .argument("action", "resume")
                .argument("guildId", "887516061266755585")
                .build();
        request.execute(response -> {
            System.out.println(response.getResponse());
        });
    }
    
    public static void pauseExample() {
        var elixir = ElixirAPI.create("(API KEY)");
        var playerRequest = new PlayerRequest.Builder(elixir)
                .action(PlayerRequest.Action.PAUSE)
                .guild("887516061266755585")
                .build();
        playerRequest.execute(response -> System.out.println("Now paused."));
    }
    
    public static void playingExample() {
        var elixir = ElixirAPI.create("(API KEY)");
        var playerRequest = new PlayerRequest.Builder(elixir)
                .action(PlayerRequest.Action.NOWPLAYING)
                .guild("887516061266755585")
                .build();
        playerRequest.execute(response -> {
            var object = response.getAsTrack();
            System.out.println("Playing: " + object.title);
        });
    }
    
    public static void queueExample() {
        var elixir = ElixirAPI.create("(API KEY)");
        var queueRequest = new QueueRequest.Builder(elixir)
                .action(QueueRequest.Action.QUEUE)
                .guild("887516061266755585")
                .build();
        queueRequest.execute(response -> {
            var queue = response.getAsCollection();
            for(var track : queue.tracks) {
                System.out.println("In queue: " + track.title);
            }
        });
    }
    
    public static void playPlaylistExample() {
        var elixir = ElixirAPI.create("(API KEY)");
        var playlistRequest = new PlaylistRequest.Builder(elixir)
                .action(PlaylistRequest.Action.QUEUE)
                .playlist("magix")
                .guild("887516061266755585")
                .channel("887526479360065601")
                .build();
        playlistRequest.execute(response -> System.out.println("Playlist queued."));
    }
}