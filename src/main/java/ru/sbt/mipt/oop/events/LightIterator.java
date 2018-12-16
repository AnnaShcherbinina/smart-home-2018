package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Iterator;
import java.util.List;

public class LightIterator implements Iterable {

    private List<Room> rooms;
    private int currentLight;
    private int currentRoom;

    public LightIterator(SmartHome smartHome){
        rooms = (List<Room>) smartHome.getRooms();
        currentLight = 0;
        currentRoom = 0;
    }

    private Light nextLight;

    @Override
    public Iterator<Light> iterator() {
        return new Iterator<Light>() {
            @Override
            public boolean hasNext() {
                if (currentRoom < rooms.size()) return true;
                if (currentRoom == rooms.size() - 1) {
                    if (currentLight < rooms.get(currentRoom).getLights().size()) return true;
                }
                return false;
            }

            @Override
            public void remove() {

            }

            public Light next() {
                if (currentRoom < rooms.size()) {
                    if (currentLight < rooms.get(currentRoom).getLights().size()) {
                        nextLight = ((List<Light>) rooms.get(currentRoom).getLights()).get(currentLight);
                        currentLight++;
                    }
                    // moving to next room
                    else {
                        currentRoom++;
                        currentLight = 0;
                        return next();
                    }
                }
                return nextLight;
            }
        };
    }
}
