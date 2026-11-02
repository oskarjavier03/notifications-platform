package notifications_lib.sms.infrastructure;

import lombok.extern.slf4j.Slf4j;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.sms.domain.entity.Sms;
import notifications_lib.sms.domain.port.SmsRepository;

import java.time.LocalTime;

@Slf4j
public class SmsRepositoryImpl implements SmsRepository {
    @Override
    public NotificationResult sendSmsNotificacion(Sms sms) {
        LocalTime desde= LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Se generacion notificacion SMS");


        return new NotificationResult("UURR88", NotificationChannel.SMS, NotificationStatus.SENT, "Twilio", "12345", desde, LocalTime.now(), null);
    }
}
