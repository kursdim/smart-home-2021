package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RemoteControlImplTest {
    @Test
    public void testCorrectCommandsAreExecuted() {
        Light light1 = new Light("1", false);
        Light light2 = new Light("2", false);
        Light light3 = new Light("3", false);
        Light light4 = new Light("4", false);
        Light light5 = new Light("5", false);
        Light light6 = new Light("6", false);
        Light light7 = new Light("7", false);
        Door door = new Door(true, "1");
        Room room1 = new Room(Arrays.asList(light1, light2, light3), Collections.emptyList(), "kitchen");
        Room room2 = new Room(Arrays.asList(light4, light5, light6), Collections.singletonList(door), "hall");
        Room room3 = new Room(Collections.singletonList(light7), Collections.emptyList(), "corridor");
        SmartHome smartHome = new SmartHome(Arrays.asList(room1, room2, room3));
        Alarm alarm = new Alarm(1234);
        smartHome.setAlarm(alarm);

        HashMap<String, Command> fromButtonsToCommandsMap = new HashMap<>();
        fromButtonsToCommandsMap.put("A", new AlarmActivateCommand(alarm, 1234));
        fromButtonsToCommandsMap.put("B", new CloseHallDoorCommand(smartHome));
        fromButtonsToCommandsMap.put("C", new SetAlarmingStateCommand(alarm));
        fromButtonsToCommandsMap.put("D", new TurnAllTheLightsOffCommand(smartHome));
        fromButtonsToCommandsMap.put("1", new TurnAllTheLightsOnCommand(smartHome));
        fromButtonsToCommandsMap.put("2", new TurnOnTheCorridorLightCommand(smartHome));
        fromButtonsToCommandsMap.put("3", new AlarmActivateCommand(alarm, 1234));
        fromButtonsToCommandsMap.put("4", new CloseHallDoorCommand(smartHome));
        RemoteControlImpl remoteControl = new RemoteControlImpl(fromButtonsToCommandsMap);

        remoteControl.onButtonPressed("A");
        assertTrue(alarm.isActivated());

        remoteControl.onButtonPressed("1");
        assertTrue(light1.isOn());
        assertTrue(light2.isOn());
        assertTrue(light3.isOn());
        assertTrue(light4.isOn());
        assertTrue(light5.isOn());
        assertTrue(light6.isOn());
        assertTrue(light7.isOn());

        remoteControl.onButtonPressed("D");
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(light3.isOn());
        assertFalse(light4.isOn());
        assertFalse(light5.isOn());
        assertFalse(light6.isOn());
        assertFalse(light7.isOn());

        remoteControl.onButtonPressed("2");
        assertTrue(light7.isOn());

        remoteControl.onButtonPressed("B");
        assertFalse(door.isOpen());
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(light3.isOn());
        assertFalse(light4.isOn());
        assertFalse(light5.isOn());
        assertFalse(light6.isOn());

        remoteControl.onButtonPressed("C");
        assertTrue(alarm.isAlarming());
    }
}