package ru.sbt.mipt.oop;


import com.coolcompany.smarthome.events.*;


import java.util.List;

public class EventLoopProcessor {
    private final SmartHome smartHome;
    private final List<EventProcessor> eventProcessors;
    private final SensorEventCreator sensorEventCreator;

    public EventLoopProcessor(SmartHome smartHome, SensorEventCreator sensorEventCreator, List<EventProcessor> eventProcessors) {
        this.smartHome = smartHome;
        this.sensorEventCreator = sensorEventCreator;
        this.eventProcessors = eventProcessors;
    }

    public void startProcessingEvents (){
        SensorEvent event = sensorEventCreator.getNextSensorEvent();
        while (event != null) {
            processEvent(event);
            event = sensorEventCreator.getNextSensorEvent();
        }
    }

    private void processEvent(SensorEvent event) {
        for (EventProcessor eventProcessor: eventProcessors) {
            if (smartHome.alarm.getState() == AlarmStateEnum.ACTIVATED){
                eventProcessor = new AlarmActivatedDecorator(eventProcessor);
            }
            if (smartHome.alarm.getState() == AlarmStateEnum.ALARMING){
                eventProcessor = new AlarmAlarmingDecorator();
            }
            eventProcessor.processEvent(event, smartHome);
        }
    }
}
