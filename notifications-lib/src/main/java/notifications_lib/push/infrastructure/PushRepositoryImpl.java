package notifications_lib.push.infrastructure;

import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.push.domain.entity.Push;
import notifications_lib.push.domain.port.PushRepository;

import java.time.LocalTime;

public class PushRepositoryImpl implements PushRepository {
    @Override
    public NotificationResult sendPushNotificacion(Push push) {
        LocalTime desde= LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new NotificationResult("UURR88", NotificationChannel.PUSH, NotificationStatus.SENT, "Firebase", "12345", desde, LocalTime.now(), null);
    }
}
