package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TurnOnTheCorridorLightCommandTest {
    @Test
    public void testTheLightInTheCorridorIsTurnedOnAfterTurnOnTheCorridorLightCommand() {
        Light light = new Light("1", false);
        Room room = new Room(Collections.singletonList(light), Collections.emptyList(), "corridor");
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        Command turnOnTheCorridorLightCommand = new TurnOnTheCorridorLightCommand(smartHome);
        turnOnTheCorridorLightCommand.execute();
        assertTrue(light.isOn());
    }
}