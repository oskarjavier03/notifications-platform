package notifications_lib.email.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import notifications_lib.common.model.NotificationDTO;
import notifications_lib.common.model.enums.NotificationType;


@Setter
@Getter
public class NotificationEmail extends NotificationDTO {

    @Size(min = 10, max = 1000, message = "el mensaje debe tener al menos 10 caracteres y no superar los 1000 caracteres")
    private String message;
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo debe ser válido")
    private String email;
    @NotBlank(message = "El asunto es obligatorio")
    @Size(min = 6, max = 50, message = "el asunto debe tener al menos 6 caracteres y no superar los 50 caracteres")
    private String subject;

    public NotificationEmail(String message, String email, String subject, NotificationType type) {
        super(type);
        this.message = message;
        this.email = email;
        this.subject = subject;
    }
}
