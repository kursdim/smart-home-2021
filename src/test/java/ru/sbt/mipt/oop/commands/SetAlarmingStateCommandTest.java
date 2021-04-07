package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.AlarmStateEnum.ALARMING;

class SetAlarmingStateCommandTest {
    @Test
    public void testAlarmIsAlarmingAfterSetAlarmingStateCommand() {
        SmartHome smartHome = new SmartHome();
        Alarm alarm = new Alarm();
        smartHome.setAlarm(alarm);
        Command setAlarmingStateCommand = new SetAlarmingStateCommand(alarm);
        setAlarmingStateCommand.execute();
        assertSame(alarm.getState(), ALARMING);
    }
}