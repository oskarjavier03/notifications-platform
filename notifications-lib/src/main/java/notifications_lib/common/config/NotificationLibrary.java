package notifications_lib.common.config;


import notifications_lib.common.Notification.NotificationActions;
import notifications_lib.common.model.NotificationDTO;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.validation.ValidationConfiguration;

import java.util.concurrent.CompletableFuture;

public final class NotificationLibrary implements AutoCloseable {

    private final NotificationActions actions;
    private final ValidationConfiguration validation;

    NotificationLibrary(NotificationActions actions, ValidationConfiguration validation) {
        this.actions = actions;
        this.validation = validation;
    }

    public CompletableFuture<NotificationResult> sendNotification(NotificationDTO notification) {
        return actions.sendNotification(notification);
    }

    @Override
    public void close() {
        validation.close();
    }
}

