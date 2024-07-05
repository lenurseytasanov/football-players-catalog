package edu.spring.footballplayerscatalog.exception;

public class PlayerNotFoundException extends RuntimeException {

    private static final String PLAYER_NOT_FOUND_MESSAGE = "Player %s not found";

    public PlayerNotFoundException(String message) {
        super(PLAYER_NOT_FOUND_MESSAGE.formatted(message));
    }

    public PlayerNotFoundException(String message, Throwable cause) {
        super(PLAYER_NOT_FOUND_MESSAGE.formatted(message), cause);
    }
}
