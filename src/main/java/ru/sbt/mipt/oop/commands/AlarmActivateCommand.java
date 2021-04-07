package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.AlarmEventProcessor;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

public class AlarmActivateCommand implements Command {
    SmartHome smartHome;

    public AlarmActivateCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        SensorEvent alarmActivateEvent = new SensorEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor();
        alarmEventProcessor.processEvent(alarmActivateEvent, smartHome);
    }
}
