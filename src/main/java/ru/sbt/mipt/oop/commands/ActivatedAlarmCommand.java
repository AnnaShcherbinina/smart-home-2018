package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmActivate;
import ru.sbt.mipt.oop.alarm.AlarmContext;

public class ActivatedAlarmCommand implements Command {

    private SmartHome smartHome;
    private String password;

    public ActivatedAlarmCommand(SmartHome smartHome, String password){
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void execute() {
        AlarmContext alarmContext = smartHome.getAlarmSystem();
        alarmContext.getState().activate(password);
    }
}
