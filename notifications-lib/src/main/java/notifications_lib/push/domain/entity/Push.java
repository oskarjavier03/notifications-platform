package notifications_lib.push.domain.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Push {
    private String message;
    private String Destination;
    private String Sender;
    private String Permission;
    private String Title;
}
