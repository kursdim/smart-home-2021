package ru.sbt.mipt.oop;

public interface AlarmState {
     void activate(int code);

     void deactivate(int code);

     void startAlarm();

     void react(AlarmReactor alarmReactor);
}
