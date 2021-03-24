package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == ALARM_ACTIVATE) {
            smartHome.alarm.Activate(event.getCode());
        } else if (event.getType() == ALARM_DEACTIVATE) {
            smartHome.alarm.Deactivate(event.getCode());
        }
    }
}
