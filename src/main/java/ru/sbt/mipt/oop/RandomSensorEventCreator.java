package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class RandomSensorEventCreator implements SensorEventCreator{
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (6 * Math.random())];
        if (sensorEventType == ALARM_ACTIVATE || sensorEventType == ALARM_DEACTIVATE){
            int code = ((int) (10 * Math.random()));
            return new SensorEvent(sensorEventType, code);
        }
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
