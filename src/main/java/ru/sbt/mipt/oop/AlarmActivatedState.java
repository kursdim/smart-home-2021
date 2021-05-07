package ru.sbt.mipt.oop;

public class AlarmActivatedState implements AlarmState {
    private final Alarm alarm;

    public AlarmActivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
    }

    @Override
    public void deactivate(int code) {
        if (!alarm.isCorrectCode(code)) {
            startAlarm();
        } else {
            alarm.changeAlarmState(new AlarmDeactivatedState(alarm));
        }
    }

    @Override
    public void startAlarm() {
        alarm.changeAlarmState(new AlarmAlarmingState(alarm));
    }

    @Override
    public void react(AlarmReactor alarmReactor) {
        alarmReactor.onAlarmActivatedState();
    }
}
