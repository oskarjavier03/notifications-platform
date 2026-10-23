package notifications_lib.push.domain.port;

import notifications_lib.common.model.NotificationResult;
import notifications_lib.push.domain.entity.Push;

public interface PushRepository {
    NotificationResult sendPushNotificacion(Push push);
}
