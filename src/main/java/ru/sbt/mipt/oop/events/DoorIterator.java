package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DoorIterator implements Iterator {

    private List<Room> rooms;
    private int currentDoor;
    private int currentRoom;

    public DoorIterator(SmartHome smartHome){
        rooms = (List<Room>) smartHome.getRooms();
        currentDoor = 0;
        currentRoom = 0;
    }

    public class Result {
        Door nextDoor;
        String nextRoom;
    }

    @Override
    public boolean hasNext() {
        if(currentRoom < rooms.size())return true;
        if(currentRoom == rooms.size()-1) {
            if (currentDoor < rooms.get(currentRoom).getDoors().size()) return true;
        }
        return false;
    }

    Result result = new Result();

    public Result next() {
        if (currentRoom < rooms.size()) {
            if (currentDoor < rooms.get(currentRoom).getDoors().size()) {
                result.nextDoor = ((List<Door>) rooms.get(currentRoom).getDoors()).get(currentDoor);
                result.nextRoom = rooms.get(currentRoom).getName();
                currentDoor++;
            }
            // moving to next room
            else {
                currentRoom++;
                currentDoor = 0;
                return next();
            }
        }
        return result;
    }
}
