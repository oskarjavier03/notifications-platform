package notifications_lib.common.meiator;

import java.util.concurrent.CompletableFuture;

public interface RequestHandler<T extends Request<R>, R> {
    CompletableFuture<R> handler(T reques);

    Class<T> getRequestType();

}
