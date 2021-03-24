package ru.sbt.mipt.oop;

public class AlarmAlarmingDecorator implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        System.out.println("Sending sms");
    }
}
