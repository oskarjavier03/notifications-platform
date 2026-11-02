package notifications_lib.push.infrastructure;

import lombok.extern.slf4j.Slf4j;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.push.domain.entity.Push;
import notifications_lib.push.domain.port.PushRepository;

import java.time.LocalTime;

@Slf4j
public class PushRepositoryImpl implements PushRepository {
    @Override
    public NotificationResult sendPushNotificacion(Push push) {
        LocalTime desde= LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Se generacion notificacion PUSH");
        return new NotificationResult("UURR88", NotificationChannel.PUSH, NotificationStatus.SENT, "Firebase", "12345", desde, LocalTime.now(), null);
    }
}
