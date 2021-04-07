package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.AlarmStateEnum.ACTIVATED;

class AlarmActivateCommandTest {
    @Test
    public void testAlarmIsActivatedAfterAlarmActivateCommand() {
        SmartHome smartHome = new SmartHome();
        Alarm alarm = new Alarm();
        smartHome.setAlarm(alarm);
        Command alarmActivateCommand = new AlarmActivateCommand(smartHome);
        alarmActivateCommand.execute();
        assertSame(alarm.getState(), ACTIVATED);
    }
}