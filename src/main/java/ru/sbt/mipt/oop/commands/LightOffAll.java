package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.LightEventProcessor;

public class LightOffAll implements Command {

    SmartHome smartHome;

    public LightOffAll(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnOffLights();
    }
}
