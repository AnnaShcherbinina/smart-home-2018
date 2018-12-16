package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SmartHome;

public class LightOnAll implements Command {

    SmartHome smartHome;

    public LightOnAll(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnOnLights();
    }
}
