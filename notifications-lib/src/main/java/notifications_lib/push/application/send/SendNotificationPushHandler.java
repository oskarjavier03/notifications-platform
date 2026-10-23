package notifications_lib.push.application.send;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notifications_lib.common.meiator.RequestHandler;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.push.domain.entity.Push;
import notifications_lib.push.domain.port.PushRepository;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
public class SendNotificationPushHandler implements RequestHandler<SendNotificationPushRequest, NotificationResult> {
    private final PushRepository pushRepository;

    @Override
    public CompletableFuture<NotificationResult> handler(SendNotificationPushRequest reques) {
        Push push = Push.builder()
                .message(reques.getMessage())
                .Destination(reques.getDestination())
                .Title(reques.getTitle())
                .Sender(reques.getSender())
                .Permission(reques.getPermission())
                .build();

        CompletableFuture<NotificationResult> fPushNotification = CompletableFuture.supplyAsync(() -> {
            return pushRepository.sendPushNotificacion(push);
        });

        return fPushNotification;

    }

    @Override
    public Class<SendNotificationPushRequest> getRequestType() {
        return SendNotificationPushRequest.class;
    }
}
