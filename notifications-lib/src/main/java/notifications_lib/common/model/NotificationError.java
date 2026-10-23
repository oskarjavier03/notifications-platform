package notifications_lib.common.model;

public record NotificationError(
        String code,
        String message,
        boolean retryable
) {}