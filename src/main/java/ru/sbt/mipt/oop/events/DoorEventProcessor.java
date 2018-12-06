package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.*;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void processor(SensorEvent event) {
        DoorIterator doorIterator = new DoorIterator(smartHome);
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            while (doorIterator.hasNext()) {
                Door door = doorIterator.next().nextDoor;
                String room = doorIterator.next().nextRoom;
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room + " was closed.");
                    }
                }
            }
        }
    }
}

