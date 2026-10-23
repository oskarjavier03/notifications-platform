package notifications_lib.sms.application.customerStrategy;

import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import notifications_lib.common.exception.NotificationBadRequestException;
import notifications_lib.common.strategy.NotificationStrategy;
import notifications_lib.common.validation.ValidationConfiguration;
import notifications_lib.sms.application.send.SendNotificationSmsRequest;
import notifications_lib.sms.infrastructure.DTO.NotificationSms;
import notifications_lib.sms.infrastructure.mapper.NotificationSmsMapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.StringJoiner;

@RequiredArgsConstructor
public class SmsNotificationStrategyImpl implements NotificationStrategy<NotificationSms> {
    private final ValidationConfiguration validate;
    private NotificationSmsMapper mapper= Mappers.getMapper(NotificationSmsMapper.class);


    @Override
    public Class<NotificationSms> getNotificationType() {
        return NotificationSms.class;
    }

    @Override
    public void validate(NotificationSms notification) {
        Set<ConstraintViolation<NotificationSms>> violaciones = validate.getValidator().validate(notification);
        if(!violaciones.isEmpty()){
            StringJoiner errors= new StringJoiner("\n");
            for (ConstraintViolation<NotificationSms> violacion : violaciones){
                errors.add(violacion.getPropertyPath()+ ": "+violacion.getMessage());
            }
            throw new NotificationBadRequestException(errors.toString()) ;
        }
    }

    @Override
    public SendNotificationSmsRequest toRequest(NotificationSms notification) {
        return mapper.mapToSendNotificationSmsRequest(notification);
    }
}
