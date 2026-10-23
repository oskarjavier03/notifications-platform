package notifications_lib.sms.infrastructure.DTO;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import notifications_lib.common.model.NotificationDTO;
import notifications_lib.common.model.enums.NotificationType;


@Setter
@Getter
public class NotificationSms extends NotificationDTO {

    @Size(min = 10, max = 40, message = "el mensaje debe tener al menos 10 caracteres y no superar los 40 caracteres")
    private String message;
    @Pattern(
            regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "El formato del número de teléfono no es válido"
    )
    private String numberCelPhone;

    public NotificationSms(String message, String numberCelPhone, NotificationType type) {
        super(type);
        this.message = message;
        this.numberCelPhone = numberCelPhone;
    }
}
