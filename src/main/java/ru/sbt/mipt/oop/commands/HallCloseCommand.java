package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.HallDoorEventProcessor;

import java.util.Iterator;

public class HallCloseCommand implements Command {

    SmartHome smartHome;

    public HallCloseCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(smartHome);
        Iterator<Room> roomIterator = smartHome.getRooms().iterator();
        Room current;
        String id;
        while (roomIterator.hasNext()) {
            current = roomIterator.next();
            if (current.getName() == "hall") {
                Iterator<Door> doorIterator = current.getDoors().iterator();
                id = doorIterator.next().getId();
                hallDoorEventProcessor.processor(new SensorEvent(SensorEventType.DOOR_CLOSED, id));
                SensorCommand command = new SensorCommand(CommandType.DOOR_CLOSED, id);
                System.out.println("Pretent we're sending command " + command);
            }
        }
    }
}

