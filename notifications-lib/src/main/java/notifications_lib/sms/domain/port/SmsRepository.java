package notifications_lib.sms.domain.port;

import notifications_lib.common.model.NotificationResult;
import notifications_lib.sms.domain.entity.Sms;

public interface SmsRepository {
    NotificationResult sendSmsNotificacion(Sms sms);
}
