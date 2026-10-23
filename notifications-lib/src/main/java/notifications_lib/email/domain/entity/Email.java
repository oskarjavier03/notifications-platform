package notifications_lib.email.domain.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Email {
    private String message;
    private String email;
    private String subject;
}
