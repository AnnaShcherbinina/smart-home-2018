package ru.sbt.mipt.oop.alarm;

public class AlarmContext {

    private State state;
    private String password;

    public void setState(State s) {state = s;}
    public void setPassword(String p) {password = p;}

    public void nextActive() {
        if ((state instanceof AlarmActivate) && (password.equals("12345"))) {
            //System.out.println(state instanceof AlarmActivate);
            setState(new AlarmDeactivate());
        }
        else if ((state instanceof AlarmActivate) && (password != "12345")) {
            setState(new AlarmSignal());
        }
        else if ((state instanceof AlarmDeactivate) && (password.equals("12345"))) {
            setState(new AlarmActivate());
        }
        else if ((state instanceof AlarmSignal) && (password.equals("12345"))) {
            setState(new AlarmActivate());
        }
    }

    public State getState(){return state;}

    public void method(){
        state.method();
    }
}
