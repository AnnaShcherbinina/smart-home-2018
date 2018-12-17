package ru.sbt.mipt.oop.adapter;


import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.events.SensorEventProcessor;

import java.util.List;

public class SensorEventsManagerAdapter implements EventsManager {

    private SensorEventsManager sensorEventsManager;

    public SensorEventsManagerAdapter(List<SensorEventProcessor> eventProcessorsList) {
        this.sensorEventsManager = new SensorEventsManager();
        for (SensorEventProcessor eventProcessor : eventProcessorsList) {
            this.sensorEventsManager.registerEventHandler(new HandlerEventProcessor(eventProcessor));
        }
    }

    @Override
    public void start() {
        sensorEventsManager.registerEventHandler(event ->
                System.out.println("Event type [" + event.getEventType() + "] from object with id=[" + event.getObjectId() + "]")
        );
        sensorEventsManager.start();
    }

    @Override
    public void addEventProcessor(SensorEventProcessor processor) {
        sensorEventsManager.registerEventHandler(new HandlerEventProcessor(processor));
    }

}
