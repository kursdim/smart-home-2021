package ru.sbt.mipt.oop;

public class SensorEvent {
    private final SensorEventType type;
    private String objectId;
    private int code;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEvent(SensorEventType type, int code) {
        this.type = type;
        this.code = code;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
