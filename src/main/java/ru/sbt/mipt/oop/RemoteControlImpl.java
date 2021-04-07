package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.commands.Command;

import java.util.HashMap;

public class RemoteControlImpl implements RemoteControl {
    private HashMap<String, Command> fromButtonsToCommandsMap;

    public RemoteControlImpl(HashMap<String, Command> fromButtonsToCommandsMap) {
        this.fromButtonsToCommandsMap = fromButtonsToCommandsMap;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        fromButtonsToCommandsMap.get(buttonCode).execute();
    }

    public void setCommands(HashMap<String, Command> fromButtonsToCommandsMap) {
        this.fromButtonsToCommandsMap = fromButtonsToCommandsMap;
    }
}
