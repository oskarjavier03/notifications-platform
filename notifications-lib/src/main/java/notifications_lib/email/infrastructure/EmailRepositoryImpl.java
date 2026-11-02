package notifications_lib.email.infrastructure;

import lombok.extern.slf4j.Slf4j;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.email.domain.entity.Email;
import notifications_lib.email.domain.port.EmailRepository;

import java.time.LocalTime;

@Slf4j
public class EmailRepositoryImpl implements EmailRepository {
    @Override
    public NotificationResult sendEmailNotificacion(Email email) {
        LocalTime desde= LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Se generacion notificacion EMAIL");
        return new NotificationResult("UURR88", NotificationChannel.EMAIL, NotificationStatus.SENT, "", "12345", desde, LocalTime.now(), null);
    }
}
