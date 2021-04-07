package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TurnAllTheLightsOnCommandTest {
    @Test
    public void testAllTheLightsAreTurnedOnAfterTurnAllTheLightsOnCommand() {
        Light light1 = new Light("1", false);
        Light light2 = new Light("2", false);
        Light light3 = new Light("3", false);
        Light light4 = new Light("4", false);
        Light light5 = new Light("5", false);
        Light light6 = new Light("6", false);
        Room room1 = new Room(Arrays.asList(light1, light2, light3), Collections.emptyList(), "kitchen");
        Room room2 = new Room(Arrays.asList(light4, light5, light6), Collections.emptyList(), "living room");
        SmartHome smartHome = new SmartHome(Arrays.asList(room1, room2));
        Command turnAllTheLightsOnCommand = new TurnAllTheLightsOnCommand(smartHome);
        turnAllTheLightsOnCommand.execute();
        assertTrue(light1.isOn());
        assertTrue(light2.isOn());
        assertTrue(light3.isOn());
        assertTrue(light4.isOn());
        assertTrue(light5.isOn());
        assertTrue(light6.isOn());
    }
}