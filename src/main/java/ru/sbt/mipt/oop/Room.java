package ru.sbt.mipt.oop;

import java.util.Collection;
import java.util.Iterator;

public class Room implements Actionable{
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        Iterator<Door> doorIterator = this.doors.iterator();

        while (doorIterator.hasNext()) {
            Door door = (Door) doorIterator.next();
            door.executeAction(action);
        }

        Iterator<Light> lightIterator = this.lights.iterator();

        while (lightIterator.hasNext()) {
            Light light = (Light) lightIterator.next();
            light.executeAction(action);
        }
    }
}
