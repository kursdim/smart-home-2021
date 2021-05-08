package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.*;

public class AlarmActivateCommand implements Command {
    Alarm alarm;
    int code;

    public AlarmActivateCommand(Alarm alarm, int code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void execute() {
        alarm.activate(code);
    }
}
