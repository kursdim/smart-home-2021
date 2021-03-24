package ru.sbt.mipt.oop;

public class Alarm {
    private AlarmState alarmState;

    public Alarm() {
        this.alarmState = new AlarmDeactivatedState(this);
    }

    public void changeAlarmState(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

    public void Activate(int code) {
        alarmState.Activate(code);
    }

    public void Deactivate(int code) {
        alarmState.Deactivate(code);
    }

    public void startAlarm() {
        alarmState.startAlarm();
    }

    public AlarmStateEnum getState() {
        return alarmState.getState();
    }
}
