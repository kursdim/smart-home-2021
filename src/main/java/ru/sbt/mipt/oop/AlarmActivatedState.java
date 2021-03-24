package ru.sbt.mipt.oop;

public class AlarmActivatedState implements AlarmState {
    private int code;
    private final Alarm alarm;

    public AlarmActivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void Activate(int code) {
        this.code = code;
        alarm.changeAlarmState(this);
    }

    @Override
    public void Deactivate(int code) {
        if (this.code != code) {
            alarm.changeAlarmState(new AlarmAlarmingState());
        } else {
            alarm.changeAlarmState(new AlarmDeactivatedState(alarm));
        }
    }

    @Override
    public void startAlarm() {
        alarm.changeAlarmState(new AlarmAlarmingState());
    }

    @Override
    public AlarmStateEnum getState() {
        return AlarmStateEnum.ACTIVATED;
    }
}
