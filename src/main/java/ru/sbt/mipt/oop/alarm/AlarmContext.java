package ru.sbt.mipt.oop.alarm;

public class AlarmContext {

    private State state;
    private String password;

    public void setState(State s) {state = s;}
    public void setPassword(String p) {password = p;}

    public void activate(String password) {
        state.activate(password);
        setState(state.getState());
    }

    public void deactivate(String password) {
        state.deactivate(password);
        setState(state.getState());
    }

    public void alarm() {
        state.alarm();
        setState(state.getState());
    }

    public State getState(){return state;}
}
