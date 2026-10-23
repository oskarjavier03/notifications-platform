package com.pinapp.notifications_client.common.application;

public interface CommandHandler<T extends Command<R>, R> {

    R handle(T command);

    Class<T> getCommandType();

}
