package notifications_lib.email.application.send;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notifications_lib.common.meiator.RequestHandler;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.email.domain.entity.Email;
import notifications_lib.email.domain.port.EmailRepository;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
public class SendNotificationEmailHandler implements RequestHandler<SendNotificationEmailRequest, NotificationResult> {
    private final EmailRepository emailRepository;

    @Override
    public CompletableFuture<NotificationResult> handler(SendNotificationEmailRequest reques) {
        Email email = Email.builder()
                .message(reques.getMessage())
                .subject(reques.getSubject())
                .email(reques.getEmail())
                .build();

        CompletableFuture<NotificationResult> fEmailNotification = CompletableFuture.supplyAsync(() -> {
            return emailRepository.sendEmailNotificacion(email);
        });

        return fEmailNotification;

    }

    @Override
    public Class<SendNotificationEmailRequest> getRequestType() {
        return SendNotificationEmailRequest.class;
    }
}
