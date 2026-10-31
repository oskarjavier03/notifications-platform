package notifications_lib.common.Notification;

import notifications_lib.common.model.NotificationDTO;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.strategy.NotificationStrategy;

import java.util.concurrent.CompletableFuture;

public interface NotificationActions {
    CompletableFuture<NotificationResult> sendNotification(NotificationDTO notification);
}
