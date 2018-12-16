package ru.sbt.mipt.oop.alarm;

import jdk.nashorn.internal.runtime.Context;

public class AlarmActivate implements State {

    private String password;
    private State state;

    public void setState(State state){
            this.state = state;
    }

    @Override
    public void activate(String password) {
        System.out.println("Alarm!");
        setState(new AlarmSignal());
    }

    @Override
    public void deactivate(String password) {
        if (password == "12345") {
            System.out.println("Deactivated!");
            setState(new AlarmDeactivate());
        } else {
            System.out.println("Wrong password!");
            setState(new AlarmSignal());
        }
    }

    @Override
    public void alarm() {
        System.out.println("Alarm!");
        setState(new AlarmSignal());
    }
}
