import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.DoorEventProcessor;
import org.junit.Test;
import org.junit.Assert;
import ru.sbt.mipt.oop.events.HallDoorEventProcessor;

import java.util.ArrayList;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorProcessorTest {

    @Test
    public void testDoor(){

        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", true);
        Door door = new Door(true,"1");
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        ArrayList<Door> doors = new ArrayList<Door>();
        doors.add(door);

        Room room = new Room(lights,doors, "kitchen");
        smartHome.addRoom(room);

        SensorEvent event = new SensorEvent(DOOR_OPEN, door.getId());
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(smartHome);

        System.out.println(door.isOpen());
        doorEventProcessor.processor(event);
        Assert.assertTrue(door.isOpen());

        SensorEvent event1 = new SensorEvent(DOOR_CLOSED, door.getId());

        System.out.println(door.isOpen());
        doorEventProcessor.processor(event1);
        Assert.assertFalse(door.isOpen());

        Room roomhall = new Room(lights,doors, "hall");
        smartHome.addRoom(roomhall);

        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(smartHome);

        System.out.println(door.isOpen());
        hallDoorEventProcessor.processor(event1);
        Assert.assertFalse(door.isOpen());
        System.out.println(light.isOn());
        Assert.assertFalse(light.isOn());

    }
}
