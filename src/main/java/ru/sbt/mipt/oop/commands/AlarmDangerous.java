package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmContext;

public class AlarmDangerous implements Command {

    private String password;
    private SmartHome smartHome;

    public AlarmDangerous(SmartHome smartHome, String password){
        this.password = password;
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        AlarmContext alarmContext = smartHome.getAlarmSystem();
        alarmContext.getState().alarm();
    }
}
