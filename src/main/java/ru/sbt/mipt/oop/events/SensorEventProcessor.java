package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SensorEvent;

public interface SensorEventProcessor {

    void processor(SensorEvent event);

}
