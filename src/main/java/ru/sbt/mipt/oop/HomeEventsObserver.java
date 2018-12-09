package ru.sbt.mipt.oop;

import javafx.collections.ObservableArray;
import ru.sbt.mipt.oop.events.SensorEventProcessor;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventsObserver {

    private Collection<SensorEventProcessor> handlers;

    public HomeEventsObserver(ArrayList<SensorEventProcessor> handlers){this.handlers = handlers;}


    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

    public void onEvent(SensorEvent event) {
        for (SensorEventProcessor handler: handlers) {
            handler.processor(event);
        }
    }

    public void loop() {
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            onEvent(event);
            event = getNextSensorEvent();
        }
    }

}
