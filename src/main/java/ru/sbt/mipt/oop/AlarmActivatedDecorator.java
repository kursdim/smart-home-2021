package ru.sbt.mipt.oop;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmActivatedDecorator implements EventProcessor {
    private final EventProcessor eventProcessor;

    public AlarmActivatedDecorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        eventProcessor.processEvent(event, smartHome);
        if (event.getType() != ALARM_ACTIVATE && event.getType() != ALARM_DEACTIVATE) {
            smartHome.alarm.startAlarm();
            System.out.println("Sending sms");
        }
    }
}
