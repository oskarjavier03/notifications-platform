package notifications_lib.common.meiator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mediator {
    private final Map<Class<?>, RequestHandler<?, ?>> requestHandlerMap = new HashMap<>();

//    private final Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap= new HashMap<>();


    public Mediator(List<RequestHandler<?, ?>> handlers) {
        for (RequestHandler<?, ?> handler : handlers) {
            this.requestHandlerMap.put(handler.getRequestType(), handler);
        }
    }

    public <R, T extends Request<R>> CompletableFuture<R> dispatch(T request) throws ExecutionException, InterruptedException {
        @SuppressWarnings("unchecked")
        RequestHandler<T, R> handler= (RequestHandler<T, R>) requestHandlerMap.get(request.getClass()) ;
        if(handler==null){
            log.error("No handler found for request type: {}", request.getClass());
            throw new RuntimeException("No handler found for request type: " + request.getClass());
        }

        return handler.handler(request);
    }

}
