package ru.sbt.mipt.oop;

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
            eventProcessor.processEvent(event, smartHome);
        }
    }
}
