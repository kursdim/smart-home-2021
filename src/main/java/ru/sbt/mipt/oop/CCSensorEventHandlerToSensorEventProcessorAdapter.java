package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.Map;

public class CCSensorEventHandlerToSensorEventProcessorAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventProcessor eventProcessor;
    private final Map<String, SensorEventType> fromLibraryEventTypesToSensorEventTypesMap;
    private final SmartHome smartHome;

    public CCSensorEventHandlerToSensorEventProcessorAdapter(EventProcessor eventProcessor, SmartHome smartHome, Map<String, SensorEventType> fromLibraryEventTypesToSensorEventTypesMap) {
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
        this.fromLibraryEventTypesToSensorEventTypesMap = fromLibraryEventTypesToSensorEventTypesMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEventType sensorEventType = fromLibraryEventTypesToSensorEventTypesMap.get(event.getEventType());
        if (sensorEventType != null) {
            SensorEvent sensorEvent = new SensorEvent(sensorEventType, event.getObjectId());
            eventProcessor.processEvent(sensorEvent, smartHome);
        }
    }
}
