package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import org.w3c.dom.events.Event;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.events.SensorEventProcessor;

import java.util.HashMap;
import java.util.Map;

public class HandlerEventProcessor implements EventHandler {

    private static Map<String, SensorEventType> eventTypeMap = new HashMap<>();

    static {
        eventTypeMap.put("LightIsOn", SensorEventType.LIGHT_ON);
        eventTypeMap.put("LightIsOff", SensorEventType.LIGHT_OFF);
        eventTypeMap.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        eventTypeMap.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        eventTypeMap.put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
        eventTypeMap.put("DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED);
    }

    private final SensorEventProcessor sensorEventProcessor;
    public HandlerEventProcessor (SensorEventProcessor s) {this.sensorEventProcessor = s;}

    @Override
    public void handleEvent(CCSensorEvent event) {
        sensorEventProcessor.processor(new SensorEvent(eventTypeMap.get(event.getEventType()), event.getObjectId()));
    }
}
