package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class HallDoorEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    @Override
    public void processor(SensorEvent event) {

        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери

            smartHome.executeAction(obj -> {
                if (obj instanceof Room) {
                    Room room = (Room) obj;
                    if (room.getName() == "hall") {
                        Iterator<Door> doors = room.getDoors().iterator();
                        while (doors.hasNext()) {
                            Door door = doors.next();
                            if (door.getId().equals(event.getObjectId())) {
                                if (event.getType() == DOOR_OPEN) {
                                    door.setOpen(true);
                                    System.out.println("Door " + door.getId() + " in room hall was opened.");
                                } else {
                                    door.setOpen(false);
                                    System.out.println("Door " + door.getId() + " in room hall was closed.");
                                    smartHome.turnOffLights();
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}

