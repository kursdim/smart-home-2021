package ru.sbt.mipt.oop;

public class AlarmDeactivatedState implements AlarmState {
    private final Alarm alarm;

    public AlarmDeactivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void Activate(int code) {
        alarm.changeAlarmState(new AlarmActivatedState(alarm));
        alarm.Activate(code);
    }

    @Override
    public void Deactivate(int code) {

    }

    @Override
    public void startAlarm() {
        alarm.changeAlarmState(new AlarmDeactivatedState(alarm));
    }

    @Override
    public AlarmStateEnum getState() {
        return AlarmStateEnum.DEACTIVATED;
    }
}
