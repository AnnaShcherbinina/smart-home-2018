package ru.sbt.mipt.oop.remotecontrol;

public class RemoteController implements RemoteControl {

    RemoteControlRegistry remoteControlRegistry;

    public RemoteController(RemoteControlRegistry remoteControlRegistry) {
        this.remoteControlRegistry = remoteControlRegistry;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        remoteControlRegistry.registerRemoteControl();
    }

    private String remoteControllerId;
    private Map<String, Command> remoteControllerButtonCommandMap = new HashMap<>();

    public RemoteController(String remoteControllerId) {
        this.remoteControllerId = remoteControllerId;
    }

    public String getRemoteControllerId() {
        return remoteControllerId;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (remoteControllerButtonCommandMap.containsKey(buttonCode)) {
            remoteControllerButtonCommandMap.get(buttonCode).execute();
        }
    }

    public void addNewButtonCommand(Command command, String buttoncCode) {
        remoteControllerButtonCommandMap.put(buttoncCode, command);
    }
}
}
