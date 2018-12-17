import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.LightOffAll;
import ru.sbt.mipt.oop.commands.LightOnAll;
import ru.sbt.mipt.oop.commands.LightOnHall;

import java.util.ArrayList;

public class LightCommandTest {

    @Test
    public void TestLightCommand() {
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", false);
        Door door = new Door(true, "1");
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        ArrayList<Door> doors = new ArrayList<Door>();
        doors.add(door);
        Room room = new Room(lights, doors, "kitchen");
        smartHome.addRoom(room);
        Room roomhall = new Room(lights,doors, "hall");
        smartHome.addRoom(roomhall);

        LightOnAll lightOnAll = new LightOnAll(smartHome);
        lightOnAll.execute();
        Assert.assertTrue(light.isOn());

        LightOffAll lightOffAll = new LightOffAll(smartHome);
        lightOffAll.execute();
        Assert.assertFalse(light.isOn());

        LightOnHall lightOnHall = new LightOnHall(smartHome);
        lightOnHall.execute();
        Assert.assertTrue(roomhall.getLights().iterator().next().isOn());
    }
}
