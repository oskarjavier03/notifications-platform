package notifications_lib.common.exception;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(Long id){
        super(""+id+"");
    }
    public NotificationNotFoundException(Long id, Throwable cause) {
        super(""+id, cause);
    }
}
