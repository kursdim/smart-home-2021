package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CloseHallDoorCommandTest {
    @Test
    public void testAllLightsAreTurnedOffAndHallDoorIsClosedAfterHallDoorIsClosedCommand() {
        Door door = new Door(true, "1");
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", true);
        Room room = new Room(Arrays.asList(light1, light2, light3), Collections.singletonList(door), "hall");
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        Command closeHallDoorCommand = new CloseHallDoorCommand(smartHome);
        closeHallDoorCommand.execute();
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(light3.isOn());
        assertFalse(door.isOpen());
    }
}