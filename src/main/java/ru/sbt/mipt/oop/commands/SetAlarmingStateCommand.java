package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.Alarm;
import ru.sbt.mipt.oop.AlarmActivatedState;
import ru.sbt.mipt.oop.AlarmState;

public class SetAlarmingStateCommand implements Command {
    Alarm alarm;

    public SetAlarmingStateCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.startAlarm();
    }
}
