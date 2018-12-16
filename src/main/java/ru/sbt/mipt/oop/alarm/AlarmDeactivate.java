package ru.sbt.mipt.oop.alarm;

public class AlarmDeactivate implements State {

    private String password;
    private State state;

    private void setState(State state) {
        this.state = state;
    }
    public State getState(){return state;}

    @Override
    public void activate(String password) {
        setState(new AlarmActivate());
        System.out.println("Activated!");
    }

    @Override
    public void deactivate(String password) {
        System.out.println("Already deactivated");
    }

    @Override
    public void alarm() {
        System.out.println("Alarm!");
        setState(new AlarmSignal());
    }
}
