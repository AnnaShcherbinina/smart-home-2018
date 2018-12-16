import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.HallDoorEventProcessor;
import ru.sbt.mipt.oop.events.LightEventProcessor;
import ru.sbt.mipt.oop.events.SensorEventProcessor;

import java.util.ArrayList;
import static org.mockito.Mockito.mock;


import static org.mockito.Mockito.verify;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class HomeEventsObserverTest {


    @Test
    public void testObserver() {

        LightEventProcessor lightEventProcessor = mock(LightEventProcessor.class);
        ArrayList<SensorEventProcessor> handlers = new ArrayList<>();
        handlers.add(lightEventProcessor);

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(handlers);

        homeEventsObserver.loop();

        verify(lightEventProcessor, Mockito.atLeast(1)).processor((SensorEvent) Mockito.any());

    }
}
