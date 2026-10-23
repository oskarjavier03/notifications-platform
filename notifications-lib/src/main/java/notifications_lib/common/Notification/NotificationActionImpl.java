package notifications_lib.common.Notification;

import lombok.RequiredArgsConstructor;
import notifications_lib.common.exception.NotificationException;
import notifications_lib.common.meiator.Mediator;
import notifications_lib.common.model.NotificationDTO;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.strategy.NotificationStrategy;
import notifications_lib.common.strategy.NotificationStrategyRegistry;

import java.util.concurrent.CompletableFuture;


@RequiredArgsConstructor
public class NotificationActionImpl implements NotificationActions {
    private final NotificationStrategyRegistry notificationStrategyRegistry;
    private final Mediator mediator;



    @Override
    public CompletableFuture<NotificationResult> sendNotification(NotificationDTO notification) {
        NotificationStrategy strategy = notificationStrategyRegistry.getStrategy(notification);
        strategy.validate(notification);
        try {
            CompletableFuture<NotificationResult> result=mediator.dispatch(strategy.toRequest(notification));
            return result;
        } catch (Exception e) {
            throw new NotificationException("Error en mediator con estrategy " + strategy.getNotificationType().getSimpleName(), e);
        }
    }
}
