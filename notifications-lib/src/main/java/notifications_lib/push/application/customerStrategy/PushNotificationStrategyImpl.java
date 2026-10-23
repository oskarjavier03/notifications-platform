package notifications_lib.push.application.customerStrategy;

import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import notifications_lib.common.exception.NotificationBadRequestException;
import notifications_lib.common.strategy.NotificationStrategy;
import notifications_lib.common.validation.ValidationConfiguration;
import notifications_lib.push.application.send.SendNotificationPushRequest;
import notifications_lib.push.infrastructure.dto.NotificationPush;
import notifications_lib.push.infrastructure.mapper.NotificationPushMapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.StringJoiner;

@RequiredArgsConstructor
public class PushNotificationStrategyImpl implements NotificationStrategy<NotificationPush> {
    private final ValidationConfiguration validate;
    private NotificationPushMapper mapper= Mappers.getMapper(NotificationPushMapper.class);


    @Override
    public Class<NotificationPush> getNotificationType() {
        return NotificationPush.class;
    }

    @Override
    public void validate(NotificationPush notification) {
        Set<ConstraintViolation<NotificationPush>> violaciones = validate.getValidator().validate(notification);
        if(!violaciones.isEmpty()){
            StringJoiner errors= new StringJoiner("\n");
            for (ConstraintViolation<NotificationPush> violacion : violaciones){
                errors.add(violacion.getPropertyPath()+ ": "+violacion.getMessage());
            }
            throw new NotificationBadRequestException(errors.toString()) ;
        }
    }

    @Override
    public SendNotificationPushRequest toRequest(NotificationPush notification) {
        return mapper.mapToSendNotificationPushRequest(notification);
    }
}
