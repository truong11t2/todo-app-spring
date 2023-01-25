package project.api.core.event;

import java.time.ZonedDateTime;
import static java.time.ZonedDateTime.now;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

public class Event<T, K, D> {
    public enum Type {
        CREATE,
        DELETE
    }

    private final T type;
    private final K key;
    private final D data;
    private final ZonedDateTime eventCreateAt;


    public Event() {
        this.type = null;
        this.key = null;
        this.data = null;
        this.eventCreateAt = null;
    }

    public Event(T type, K key, D data) {
        this.type = type;
        this.key = key;
        this.data = data;
        this.eventCreateAt = now();
    }


    public T getEventType() {
        return this.type;
    }


    public K getKey() {
        return this.key;
    }


    public D getData() {
        return this.data;
    }


    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    public ZonedDateTime getEventCreateAt() {
        return this.eventCreateAt;
    }


}
