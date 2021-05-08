package ru.sbt.mipt.oop;

public class Alarm {
    private AlarmState alarmState;
    private final int code;

    public Alarm(int code) {
        this.alarmState = new AlarmDeactivatedState(this);
        this.code = code;
    }

    public void activate(int code) {
        alarmState.activate(code);
    }

    public void deactivate(int code) {
        alarmState.deactivate(code);
    }

    public void startAlarm() {
        alarmState.startAlarm();
    }

    void changeAlarmState(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

    public boolean isActivated() {
        return (alarmState instanceof AlarmActivatedState);
    }

    public boolean isDeactivated() {
        return (alarmState instanceof AlarmDeactivatedState);
    }

    public boolean isAlarming() {
        return (alarmState instanceof AlarmAlarmingState);
    }

    public boolean isCorrectCode(int code) {
        return this.code == code;
    }

    public void react(AlarmReactor alarmReactor) {
        alarmState.react(alarmReactor);
    }
}
