package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.LightEventProcessor;

public class LightOff implements Command {

    SmartHome smartHome;

    public LightOff(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnOffLights();
    }
}
