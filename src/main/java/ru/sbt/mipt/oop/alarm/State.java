package ru.sbt.mipt.oop.alarm;

import jdk.nashorn.internal.runtime.Context;

public interface State {

    void activate(String password);
    void deactivate(String password);
    void alarm();
}
