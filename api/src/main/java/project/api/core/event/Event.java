package project.api.core.event;

import java.time.ZonedDateTime;
import static java.time.ZonedDateTime.now;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

public class Event<K, T> {
    public enum Type {
        CREATE,
        DELETE
    }

    private final Type eventType;
    private final K key;
    private final T data;
    private final ZonedDateTime eventCreateAt;


    public Event() {
        this.eventType = null;
        this.key = null;
        this.data = null;
        this.eventCreateAt = null;
    }

    public Event(Type eventType, K key, T data) {
        this.eventType = eventType;
        this.key = key;
        this.data = data;
        this.eventCreateAt = now();
    }


    public Type getEventType() {
        return this.eventType;
    }


    public K getKey() {
        return this.key;
    }


    public T getData() {
        return this.data;
    }


    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    public ZonedDateTime getEventCreateAt() {
        return this.eventCreateAt;
    }


}
