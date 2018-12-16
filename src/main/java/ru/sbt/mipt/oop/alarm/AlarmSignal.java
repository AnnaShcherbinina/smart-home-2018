package ru.sbt.mipt.oop.alarm;

public class AlarmSignal implements State{

    private State state;

    public void setState(State s) {state = s;}

    @Override
    public void activate(String password) {
        System.out.println("ALARM!");
    }

    @Override
    public void deactivate(String passwod) {
        if (passwod=="12345") {
            setState(new AlarmDeactivate());
            System.out.println("Deactivated");
        }
        System.out.println("Wrong password!");
    }

    @Override
    public void alarm() {

    }
}
