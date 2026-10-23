package notifications_lib.email.application.send;

import lombok.AllArgsConstructor;
import lombok.Data;
import notifications_lib.common.meiator.Request;
import notifications_lib.common.model.NotificationResult;


@Data
@AllArgsConstructor
public class SendNotificationEmailRequest implements Request<NotificationResult> {
    private String message;
    private String email;
    private String subject;
}
