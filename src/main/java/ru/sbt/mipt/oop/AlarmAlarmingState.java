package ru.sbt.mipt.oop;

public class AlarmAlarmingState implements AlarmState {
    @Override
    public void Activate(int code) {
    }

    @Override
    public void Deactivate(int code) {
    }

    @Override
    public void startAlarm() {
    }

    @Override
    public AlarmStateEnum getState() {
        return AlarmStateEnum.ALARMING;
    }
}
