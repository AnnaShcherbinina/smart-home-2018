package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Iterator;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void processor(SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            LightIterator lightIterator = new LightIterator(smartHome);
            Iterator<Light> lightIterator1 = lightIterator.iterator();
            while (lightIterator1.hasNext()) {
                Light light = lightIterator1.next();
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " was turned off.");
                    }
                }
            }
        }
    }
}


