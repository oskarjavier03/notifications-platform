package notifications_lib.common.exception;

public class NotificationBadRequestException extends RuntimeException {
    public NotificationBadRequestException(String message) {
        super(message);
    }

    public NotificationBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
