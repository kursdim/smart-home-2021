package ru.sbt.mipt.oop;

public class AlarmAlarmingState implements AlarmState {
    private final Alarm alarm;

    public AlarmAlarmingState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
    }

    @Override
    public void deactivate(int code) {
        if (alarm.isCorrectCode(code)) {
            alarm.changeAlarmState(new AlarmDeactivatedState(alarm));
        }
    }

    @Override
    public void startAlarm() {
    }

    @Override
    public void react(AlarmReactor alarmReactor) {
        alarmReactor.onAlarmAlarmingState();
    }
}
