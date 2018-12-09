import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.alarm.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlarmContextTest {

    @Test
    public void AlarmTest(){
        String password = "12345";
        State alarmActivate = new AlarmActivate();
        AlarmContext alarmContext = new AlarmContext();
        alarmContext.setState(alarmActivate);
        alarmContext.setPassword(password);
        alarmContext.nextActive();
        Assert.assertTrue(alarmContext.getState() instanceof AlarmDeactivate);
        alarmContext.nextActive();
        Assert.assertTrue(alarmContext.getState() instanceof AlarmActivate);
        String p = "1234";
        alarmContext.setPassword(p);
        alarmContext.nextActive();
        Assert.assertTrue(alarmContext.getState() instanceof AlarmSignal);
    }
}
