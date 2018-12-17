package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmActivate;
import ru.sbt.mipt.oop.alarm.AlarmContext;
import ru.sbt.mipt.oop.alarm.AlarmSignal;

public class AlarmDecorator implements SensorEventProcessor {

    private SmartHome smartHome;
    private SensorEventProcessor sensorEventProcessor;
    private AlarmContext alarmContext;

    public AlarmDecorator(SensorEventProcessor sensorEventProcessor, SmartHome smartHome) {
        this.sensorEventProcessor = sensorEventProcessor;
        this.smartHome = smartHome;
        this.alarmContext = smartHome.getAlarmSystem();
    }

    @Override
    public void processor(SensorEvent event) {
        if (smartHome.getAlarmSystem().getState() instanceof AlarmActivate) {
            System.out.println("Sending sms");
            return;
        }
        if (smartHome.getAlarmSystem().getState() instanceof AlarmSignal) {
            System.out.println("Sending sms");
            return;
        }
        sensorEventProcessor.processor(event);
    }
}



