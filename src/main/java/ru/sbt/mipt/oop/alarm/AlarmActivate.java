package ru.sbt.mipt.oop.alarm;

import jdk.nashorn.internal.runtime.Context;

public class AlarmActivate implements State {

    private String password;

    @Override
    public void method() {
        System.out.println("Alarm is activated");
    }
}
