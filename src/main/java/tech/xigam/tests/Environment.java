package tech.xigam.tests;

import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.requests.player.*;
import tech.xigam.elixirapi.requests.playlist.FetchPlaylistRequest;
import tech.xigam.elixirapi.requests.playlist.QueuePlaylistRequest;
import tech.xigam.elixirapi.requests.queue.GetQueueRequest;
import tech.xigam.elixirapi.requests.queue.ShuffleRequest;

public final class Environment {
    private static ElixirAPI elixir;
    
    public static void main(String[] args) {
        if(args.length != 1)
            System.exit(0);
        Environment.elixir = ElixirAPI.create(args[0]);
        
        fetchPlaylistExample();
    }
    
    public static void fetchPlaylistExample() {
        try {
            var request = new FetchPlaylistRequest.Builder(elixir)
                    .playlist("magix").build();
            request.execute(response -> {
                System.out.println(response.getResponseCode());
//                var tracks = response.getAsTrackCollection();
//                for(var track : tracks.tracks)
//                    System.out.println("Track: " + track.title + " by " + track.author + "\n");
            });
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
    
    public static void queuePlaylistExample() {
        try {
            var request = new QueuePlaylistRequest.Builder(elixir)
                    .channel("887526479360065601").playlist("magix")
                    .guild("887516061266755585").build();
            request.execute(response -> System.out.println("Playlist queued."));
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
    
    public static void playExample() {
        try {
            var request = new PlayRequest.Builder(elixir)
                    .track("dream roadtrip").guild("887516061266755585").build();
            request.execute(response -> System.out.println("executed"));
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
    
    public static void playingTrackExample() {
        try {
            var request = new GetPlayingTrackRequest.Builder(elixir)
                    .guild("887516061266755585").build();
            request.execute(response -> {
                var trackResponse = (GetPlayingTrackRequest.Response) response;
                var trackObject = trackResponse.getAsTrack();
                System.out.println("Playing: " + trackObject.title);
            });
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
    
    public static void queueExample() {
        try {
            var request = new GetQueueRequest.Builder(elixir)
                    .guild("887516061266755585").build();
            request.execute(response -> response.getAsTrackCollection()
                    .tracks.forEach(track -> System.out.println("Queued: " + track.title)));
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
    
    public static void pauseExample() {
        try {
            var request = new PauseRequest.Builder(elixir)
                    .guild("887516061266755585").build();
            request.execute(response -> {
                if(response.getResponseCode() != 200)
                    System.out.println("Pause failed.");
                else System.out.println("Paused.");
            });
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
    
    public static void resumeExample() {
        try {
            var request = new ResumeRequest.Builder(elixir)
                    .guild("887516061266755585").build();
            request.execute(response -> {
                if(response.getResponseCode() != 200)
                    System.out.println("Resume failed.");
                else System.out.println("Resumed.");
            });
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
    
    public static void shuffleExample() {
        try {
            var request = new ShuffleRequest.Builder(elixir)
                    .guild("887516061266755585").build();
            request.execute(response -> {
                if(response.getResponseCode() != 200)
                    System.out.println("Shuffle failed with code: " + response.getResponseCode());
                else System.out.println("Shuffled.");
            });
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
    
    public static void skipExample() {
        try {
            var request = new SkipRequest.Builder(elixir)
                    .guild("887516061266755585").build();
            request.execute(response -> {
                if(response.getResponseCode() != 200)
                    System.out.println("Skip failed.");
                else System.out.println("Skipped.");
            });
        } catch (RequestBuildException ignored) {
            System.out.println("Request build failed.");
        }
    }
}