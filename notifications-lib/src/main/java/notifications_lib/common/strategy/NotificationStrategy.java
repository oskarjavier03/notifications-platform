package notifications_lib.common.strategy;

import notifications_lib.common.meiator.Request;
import notifications_lib.common.model.NotificationDTO;
import notifications_lib.common.model.NotificationResult;

public interface NotificationStrategy<D extends NotificationDTO> {
    Class<D> getNotificationType();

    void validate(D notification);

    Request<NotificationResult> toRequest(D notification);
}
