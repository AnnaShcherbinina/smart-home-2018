import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.HallCloseCommand;

import java.util.ArrayList;

public class HallDoorCommandTest {
    @Test
    public void TestHallDoorCommand() {
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", true);
        Door door = new Door(true,"1");
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        ArrayList<Door> doors = new ArrayList<Door>();
        doors.add(door);

        Room roomhall = new Room(lights,doors, "hall");
        smartHome.addRoom(roomhall);

        HallCloseCommand hallCloseCommand = new HallCloseCommand(smartHome);
        hallCloseCommand.execute();
        Assert.assertFalse(door.isOpen());
    }
}
