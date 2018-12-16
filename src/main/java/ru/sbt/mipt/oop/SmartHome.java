package ru.sbt.mipt.oop;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import ru.sbt.mipt.oop.alarm.AlarmContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SmartHome {
    Collection<Room> rooms;
    private AlarmContext alarmContext;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public AlarmContext getAlarmSystem() {
        return this.alarmContext;
    }

    public void setAlarmSystem(AlarmContext alarmContext){
        this.alarmContext = alarmContext;
    }

    public void executeAction(Action action) {
        action.execute(this);
        Iterator<Room> roomIterator = this.rooms.iterator();

        while (roomIterator.hasNext()) {
            Room room = (Room) roomIterator.next();
            room.executeAction(action);
        }
    }

    public void turnOffLights() {
        this.executeAction(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                System.out.println("Pretent we're sending command " + command);
            }
        });
    }

    public void turnOnLights() {
        this.executeAction(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_ON, light.getId());
                System.out.println("Pretent we're sending command " + command);
            }
        });
    }
}
