package notifications_lib.sms.application.send;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notifications_lib.common.meiator.RequestHandler;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.sms.domain.entity.Sms;
import notifications_lib.sms.domain.port.SmsRepository;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
public class SendNotificationSmsHandler implements RequestHandler<SendNotificationSmsRequest, NotificationResult> {
    private final SmsRepository smsRepository;

    @Override
    public CompletableFuture<NotificationResult> handler(SendNotificationSmsRequest reques) {
        Sms sms = Sms.builder()
                .message(reques.getMessage())
                .numberCelPhone(reques.getNumberCelPhone())
                .build();

        CompletableFuture<NotificationResult> fSmsNotification = CompletableFuture.supplyAsync(() -> {
            return smsRepository.sendSmsNotificacion(sms);
        });

        return fSmsNotification;

    }

    @Override
    public Class<SendNotificationSmsRequest> getRequestType() {
        return SendNotificationSmsRequest.class;
    }
}
