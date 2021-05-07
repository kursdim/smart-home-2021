package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlarmEventProcessorTest {
    @Test
    public void testAlarmActivateEvent() {
        SmartHome smartHome = new SmartHome();
        Alarm alarm = new Alarm(1234);
        smartHome.setAlarm(alarm);
        SensorEvent alarmActivateEvent = new SensorEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor();
        alarmEventProcessor.processEvent(alarmActivateEvent, smartHome);
        assertTrue(alarm.isActivated());
    }

    @Test
    public void testAlarmDeactivateEventWithCorrectCode() {
        SmartHome smartHome = new SmartHome();
        Alarm alarm = new Alarm(1234);
        smartHome.setAlarm(alarm);
        SensorEvent alarmActivateEvent = new SensorEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        SensorEvent alarmDeactivateEvent = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, 1234);
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor();
        alarmEventProcessor.processEvent(alarmActivateEvent, smartHome);
        alarmEventProcessor.processEvent(alarmDeactivateEvent, smartHome);
        assertTrue(alarm.isDeactivated());
    }

    @Test
    public void testAlarmDeactivateEventWithIncorrectCode() {
        SmartHome smartHome = new SmartHome();
        Alarm alarm = new Alarm(1234);
        smartHome.setAlarm(alarm);
        SensorEvent alarmActivateEvent = new SensorEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        SensorEvent alarmDeactivateEvent = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, 1237);
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor();
        alarmEventProcessor.processEvent(alarmActivateEvent, smartHome);
        alarmEventProcessor.processEvent(alarmDeactivateEvent, smartHome);
        assertTrue(alarm.isAlarming());
    }
}