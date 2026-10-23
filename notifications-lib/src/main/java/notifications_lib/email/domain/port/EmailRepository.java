package notifications_lib.email.domain.port;

import notifications_lib.common.model.NotificationResult;
import notifications_lib.email.domain.entity.Email;

public interface EmailRepository {
    NotificationResult sendEmailNotificacion(Email email);
}
