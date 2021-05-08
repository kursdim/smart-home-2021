package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;

import static org.junit.jupiter.api.Assertions.*;

class SetAlarmingStateCommandTest {
    @Test
    public void testAlarmIsAlarmingAfterSetAlarmingStateCommand() {
        Alarm alarm = new Alarm(1234);
        Command setAlarmingStateCommand = new SetAlarmingStateCommand(alarm);
        setAlarmingStateCommand.execute();
        assertTrue(alarm.isAlarming());
    }
}