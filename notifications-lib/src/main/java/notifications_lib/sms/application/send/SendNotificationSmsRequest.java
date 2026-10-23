package notifications_lib.sms.application.send;

import lombok.AllArgsConstructor;
import lombok.Data;
import notifications_lib.common.meiator.Request;
import notifications_lib.common.model.NotificationResult;


@Data
@AllArgsConstructor
public class SendNotificationSmsRequest implements Request<NotificationResult> {
    private String message;
    private String numberCelPhone;
}
