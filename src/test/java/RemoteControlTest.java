import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmActivate;
import ru.sbt.mipt.oop.commands.ActivatedAlarmCommand;
import ru.sbt.mipt.oop.commands.HallCloseCommand;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;
import ru.sbt.mipt.oop.remotecontrol.RemoteController;

import java.util.ArrayList;

public class RemoteControlTest {

    @Test
    public void TestRemoteControl(){
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", true);
        Door door = new Door(true,"1");
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        ArrayList<Door> doors = new ArrayList<Door>();
        doors.add(door);

        Room roomhall = new Room(lights,doors, "hall");
        smartHome.addRoom(roomhall);

        RemoteController remoteController = new RemoteController(new RemoteControlRegistry());
        remoteController.addNewButtonCommand(new HallCloseCommand(smartHome), "2");
        remoteController.onButtonPressed("2");
        Assert.assertFalse(door.isOpen());

    }
}
