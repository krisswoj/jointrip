package pl.jointrip.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class SystemNotification {

    private Map<String, String>  systemNotification;

    public SystemNotification(String key, String value) {
        this.systemNotification = new HashMap<>();
        this.systemNotification.put(key, value);
    }
}