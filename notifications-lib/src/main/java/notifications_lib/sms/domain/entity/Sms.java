package notifications_lib.sms.domain.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Sms {
    private String message;
    private String numberCelPhone;
}
