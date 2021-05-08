package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;

import static org.junit.jupiter.api.Assertions.*;

class AlarmActivateCommandTest {
    @Test
    public void testAlarmIsActivatedAfterAlarmActivateCommand() {
        Alarm alarm = new Alarm(1234);
        Command alarmActivateCommand = new AlarmActivateCommand(alarm, 1234);
        alarmActivateCommand.execute();
        assertTrue(alarm.isActivated());
    }
}