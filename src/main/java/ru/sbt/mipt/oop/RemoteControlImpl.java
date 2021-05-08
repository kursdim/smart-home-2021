package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.commands.Command;

import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
    private Map<String, Command> fromButtonsToCommandsMap;

    public RemoteControlImpl(Map<String, Command> fromButtonsToCommandsMap) {
        this.fromButtonsToCommandsMap = fromButtonsToCommandsMap;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        Command command = fromButtonsToCommandsMap.get(buttonCode);
        if (command != null) {
            command.execute();
        }
    }

    public void setCommands(Map<String, Command> fromButtonsToCommandsMap) {
        this.fromButtonsToCommandsMap = fromButtonsToCommandsMap;
    }
}
