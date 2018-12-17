package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.LightEventProcessor;

import java.util.Iterator;

public class LightOnHall implements Command {

    private SmartHome smartHome;

    public LightOnHall(SmartHome smartHome){this.smartHome = smartHome;}

    @Override
    public void execute() {
        LightEventProcessor lightEventProcessor = new LightEventProcessor(smartHome);
        Iterator<Room> roomIterator = smartHome.getRooms().iterator();
        Room current;
        String id;
        while (roomIterator.hasNext()) {
            current = roomIterator.next();
            if (current.getName() == "hall") {
                Iterator<Light> lightIterator = current.getLights().iterator();
                id = lightIterator.next().getId();
                lightEventProcessor.processor(new SensorEvent(SensorEventType.LIGHT_ON, id));
                SensorCommand command = new SensorCommand(CommandType.LIGHT_ON, id);
                System.out.println("Pretent we're sending command " + command);
            }
        }
    }
}
