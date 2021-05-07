package ru.sbt.mipt.oop;

public class AlarmDeactivatedState implements AlarmState {
    private final Alarm alarm;

    public AlarmDeactivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
        if (alarm.isCorrectCode(code)) {
            alarm.changeAlarmState(new AlarmActivatedState(alarm));
        }
    }

    @Override
    public void deactivate(int code) {
    }

    @Override
    public void startAlarm() {
        alarm.changeAlarmState(new AlarmDeactivatedState(alarm));
    }

    @Override
    public void react(AlarmReactor alarmReactor) {
        alarmReactor.onAlarmDeactivatedState();
    }
}
