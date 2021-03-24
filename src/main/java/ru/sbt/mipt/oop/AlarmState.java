package ru.sbt.mipt.oop;

public interface AlarmState {
     void Activate(int code);

     void Deactivate(int code);

     void startAlarm();

    AlarmStateEnum getState();
}
