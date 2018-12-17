package ru.sbt.mipt.oop.adapter;

import ru.sbt.mipt.oop.events.SensorEventProcessor;

public interface EventsManager {
    void start();
    void addEventProcessor(SensorEventProcessor processor);
}
