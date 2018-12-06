import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.LightEventProcessor;

import java.util.ArrayList;

public class LightProcessorTest {

    @Test
    public void testLight() {
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", true);
        Door door = new Door(true,"1");
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        ArrayList<Door> doors = new ArrayList<Door>();
        doors.add(door);
        Room room = new Room(lights,doors, "kitchen");
        smartHome.addRoom(room);

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, light.getId());
        LightEventProcessor lightEventProcessor = new LightEventProcessor(smartHome);

        System.out.println(light.isOn());
        lightEventProcessor.processor(event);
        Assert.assertTrue(light.isOn());

        SensorEvent event1 = new SensorEvent(SensorEventType.LIGHT_OFF, light.getId());

        System.out.println(!light.isOn());
        lightEventProcessor.processor(event1);
        Assert.assertFalse(light.isOn());
    }
}
