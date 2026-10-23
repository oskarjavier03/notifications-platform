package com.pinapp.notifications_client.common.infrastructure.event.consumer;

import org.apache.avro.specific.SpecificRecord;
import org.springframework.messaging.Message;

public interface EventSpecificConsumer<T extends SpecificRecord> {

    void accept(Message<T> genericRecordMessage);

    String getSchema();

}
