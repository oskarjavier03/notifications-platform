package notifications_lib.push.application.send;

import lombok.AllArgsConstructor;
import lombok.Data;
import notifications_lib.common.meiator.Request;
import notifications_lib.common.model.NotificationResult;


@Data
@AllArgsConstructor
public class SendNotificationPushRequest implements Request<NotificationResult> {
    private String message;
    private String destination;
    private String sender;
    private String permission;
    private String title;
}
