package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {

    RemoteControlRegistry remoteControlRegistry;

    public RemoteController(RemoteControlRegistry remoteControlRegistry) {
        this.remoteControlRegistry = remoteControlRegistry;
    }

    private Map<String, Command> remoteControllerButtonCommandMap = new HashMap<>();


    @Override
    public void onButtonPressed(String buttonCode) {
        if (remoteControllerButtonCommandMap.containsKey(buttonCode)) {
            remoteControllerButtonCommandMap.get(buttonCode).execute();
        }
    }

    public void addNewButtonCommand(Command command, String buttonCode) {
        remoteControllerButtonCommandMap.put(buttonCode, command);
    }
}

