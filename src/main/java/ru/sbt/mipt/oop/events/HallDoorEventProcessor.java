package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.*;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class HallDoorEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    public HallDoorEventProcessor (SmartHome smartHome) {this.smartHome = smartHome;}

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    @Override
    public void processor(SensorEvent event) {

        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            DoorIterator doorIterator = new DoorIterator(smartHome);
            LightIterator lightIterator = new LightIterator(smartHome);
            while (doorIterator.hasNext()) {
                Door door = doorIterator.next().nextDoor;
                String room = doorIterator.next().nextRoom;
                if (room.equals("hall")) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " in room " + room + " was opened.");
                        } else {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " in room " + room + " was closed.");
                            while (lightIterator.hasNext()) {
                                Light light = lightIterator.next().nextLight;
                                light.setOn(false);
                                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                sendCommand(command);
                            }
                        }
                    }
                }
            }
        }
    }
}
