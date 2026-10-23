package notifications_lib.common.strategy;

import notifications_lib.common.model.NotificationDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationStrategyRegistry {
    private final Map<Class<?>, NotificationStrategy<?>> strategies = new HashMap<>();

    public NotificationStrategyRegistry(List<NotificationStrategy<?>> strategies) {

        for (NotificationStrategy<?> strategy : strategies) {
            Class<?> type = strategy.getNotificationType();

            if (this.strategies.putIfAbsent(type, strategy) != null) {
                throw new IllegalArgumentException("Ya existe una estrategia para: " + type.getName());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <D extends NotificationDTO> NotificationStrategy<D> getStrategy(D notification) {

        NotificationStrategy<D> strategy = (NotificationStrategy<D>) strategies.get(notification.getClass());

        if (strategy == null) {
            throw new IllegalArgumentException("No existe estrategia para: " + notification.getClass().getName());
        }

        return  strategy;
    }


}