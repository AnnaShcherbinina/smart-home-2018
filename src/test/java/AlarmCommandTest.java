import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.alarm.*;
import ru.sbt.mipt.oop.commands.ActivatedAlarmCommand;
import ru.sbt.mipt.oop.commands.AlarmDangerous;
import ru.sbt.mipt.oop.events.AlarmDecorator;

import java.util.ArrayList;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class AlarmCommandTest {

    @Test
    public void TestAlarmCommand()
    {
        SmartHome smartHome = new SmartHome();
//        Light light = new Light("1", true);
//        Door door = new Door(true,"1");
//        ArrayList<Light> lights = new ArrayList<Light>();
//        lights.add(light);
//        ArrayList<Door> doors = new ArrayList<Door>();
//        doors.add(door);
//
//        Room room = new Room(lights,doors, "kitchen");
//        smartHome.addRoom(room);

        //State state = new AlarmDeactivate();
        //smartHome.getAlarmSystem().setState(state);

        AlarmContext alarmContext = new AlarmContext();
        State state = new AlarmDeactivate();
        alarmContext.setState(state);
        smartHome.setAlarmSystem(alarmContext);

        ActivatedAlarmCommand activatedAlarmCommand = new ActivatedAlarmCommand(smartHome, "12345");
        activatedAlarmCommand.execute();
        AlarmContext alarmContext1 = smartHome.getAlarmSystem();
        //System.out.println(alarmContext1.getState());
        Assert.assertTrue(alarmContext1.getState() instanceof AlarmActivate);
        AlarmDangerous alarmDangerous = new AlarmDangerous(smartHome, "12");
        alarmDangerous.execute();
        Assert.assertTrue(alarmContext1.getState() instanceof AlarmSignal);
    }

}
