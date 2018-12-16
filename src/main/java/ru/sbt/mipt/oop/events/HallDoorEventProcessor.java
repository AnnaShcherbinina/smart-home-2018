package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

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
            Iterator<Room> rooms = smartHome.getRooms().iterator();
            Room current;
            SmartHome smartHome1 = new SmartHome();
            while(rooms.hasNext()){
                current = rooms.next();
                if (current.getName()=="hall") {
                    smartHome1.addRoom(current);
                }
            }
            DoorIterator doorIterator = new DoorIterator(smartHome1);
            LightIterator lightIterator = new LightIterator(smartHome1);
            Iterator<Door> doorIterator1 = doorIterator.iterator();
            Iterator<Light> lightIterator1 = lightIterator.iterator();
            while (doorIterator1.hasNext()) {
                Door door = doorIterator1.next();
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " in room hall was opened.");
                        } else {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " in room hall was closed.");
                            while (lightIterator1.hasNext()) {
                                Light light = lightIterator1.next();
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

