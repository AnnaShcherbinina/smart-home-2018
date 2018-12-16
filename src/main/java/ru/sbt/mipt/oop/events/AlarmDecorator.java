package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmContext;

public class AlarmDecorator implements SensorEventProcessor {

    private SmartHome smartHome;

    public AlarmDecorator(SmartHome smartHome){this.smartHome = smartHome;}

    @Override
    public void processor(SensorEvent event) {
        AlarmContext alarmContext = smartHome.getAlarmSystem();
        if (event.getType() == SensorEventType.ALARM_ACTIVATE || event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
                alarmContext.activate(event.getObjectId());
            } else {
                alarmContext.deactivate(event.getObjectId());
            }
        }
    }
}

