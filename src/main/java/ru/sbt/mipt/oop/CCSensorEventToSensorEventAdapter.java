package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

public class CCSensorEventToSensorEventAdapter {
    public static SensorEvent adaptEventToSensorEvent(CCSensorEvent event) {
        switch (event.getEventType()) {
            case ("LightIsOn"):
                return new SensorEvent(SensorEventType.LIGHT_ON, event.getObjectId());
            case ("LightIsOff"):
                return new SensorEvent(SensorEventType.LIGHT_OFF, event.getObjectId());
            case ("DoorIsOpen"):
                return new SensorEvent(SensorEventType.DOOR_OPEN, event.getObjectId());
            case ("DoorIsClosed"):
                return new SensorEvent(SensorEventType.DOOR_CLOSED, event.getObjectId());
            default:
                return null;
        }
    }
}