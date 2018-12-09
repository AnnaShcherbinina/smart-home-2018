package ru.sbt.mipt.oop.alarm;

public class AlarmSignal implements State{

    @Override
    public void method() {
        System.out.println("Dangerous");
    }
}
