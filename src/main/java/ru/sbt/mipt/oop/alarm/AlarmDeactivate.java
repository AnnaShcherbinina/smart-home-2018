package ru.sbt.mipt.oop.alarm;

public class AlarmDeactivate implements State {

    private String password;

    @Override
    public void method() {
        System.out.println("Alarm is deactivated");
    }
}
