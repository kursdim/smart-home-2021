package ru.sbt.mipt.oop;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventProcessorDecorator implements EventProcessor, AlarmReactor {
    private final EventProcessor eventProcessor;
    private SensorEvent event;
    private SmartHome smartHome;

    public EventProcessorDecorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
        smartHome.alarm.react(this);
    }

    @Override
    public void onAlarmActivatedState() {
        if (event.getType() != ALARM_ACTIVATE && event.getType() != ALARM_DEACTIVATE) {
            smartHome.alarm.startAlarm();
            (new SimpleMessageSender()).sendMessage();
        } else {
            eventProcessor.processEvent(event, smartHome);
        }
    }

    @Override
    public void onAlarmDeactivatedState() {
        eventProcessor.processEvent(event, smartHome);
    }

    @Override
    public void onAlarmAlarmingState() {
        (new SimpleMessageSender()).sendMessage();
    }
}
