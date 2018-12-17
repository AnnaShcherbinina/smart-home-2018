package ru.sbt.mipt.oop.adapter;

import ru.sbt.mipt.oop.HomeEventsObserver;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventProcessor;
import ru.sbt.mipt.oop.eventsgenerate.RandomGenerateEvent;

import java.util.ArrayList;
import java.util.List;

public class HomeEventsObserverAdapter implements EventsManager {

    private List<SensorEventProcessor> eventProcessorList;
    private RandomGenerateEvent randomGenerateEvent;

    public HomeEventsObserverAdapter(ArrayList<SensorEventProcessor> eventProcessorsList, RandomGenerateEvent randomGenerateEvent) {
        this.eventProcessorList = eventProcessorsList;
        this.randomGenerateEvent=randomGenerateEvent;
    }

    @Override
    public void start() {
        SensorEvent event = randomGenerateEvent.getNextSensorEvent();
        while (event != null) {
            processEvent(event);
            event = randomGenerateEvent.getNextSensorEvent();
        }
    }

    public void setEventProcessorList(List<SensorEventProcessor> list) {
        this.eventProcessorList = list;
    }

    public void setRandomGenerateEvent(RandomGenerateEvent randomGenerateEvent) {
        this.randomGenerateEvent = randomGenerateEvent;
    }

    public void addEventProcessor(SensorEventProcessor eventProcessor) {
        eventProcessorList.add(eventProcessor);
    }

    private void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        for (SensorEventProcessor eventProcessor : eventProcessorList) {
            eventProcessor.processor(event);
        }
    }
}
