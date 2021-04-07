package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TurnAllTheLightsOffCommandTest {
    @Test
    public void testAllTheLightsAreTurnedOffAfterTurnAllTheLightsOffCommand() {
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", true);
        Light light4 = new Light("4", true);
        Light light5 = new Light("5", true);
        Light light6 = new Light("6", true);
        Room room1 = new Room(Arrays.asList(light1, light2, light3), Collections.emptyList(), "kitchen");
        Room room2 = new Room(Arrays.asList(light4, light5, light6), Collections.emptyList(), "living room");
        SmartHome smartHome = new SmartHome(Arrays.asList(room1, room2));
        Command turnAllTheLightsOffCommand = new TurnAllTheLightsOffCommand(smartHome);
        turnAllTheLightsOffCommand.execute();
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(light3.isOn());
        assertFalse(light4.isOn());
        assertFalse(light5.isOn());
        assertFalse(light6.isOn());
    }
}