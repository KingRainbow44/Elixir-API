package tech.xigam.elixirapi.exceptions;

public final class RequestBuildException extends Exception {
    public RequestBuildException() {
        super("Abstract builder does not support build().");
    }
}
