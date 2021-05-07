package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventProcessorTest {
    @Test
    public void testAllLightsAreTurnedOffAfterHallDoorIsClosed() {
        Door door = new Door(true, "1");
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", true);
        Room room = new Room(Arrays.asList(light1, light2, light3), Collections.singletonList(door), "hall");
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        SensorEvent doorClosedEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        CommandSender commandSender = new SimpleCommandSender();
        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(commandSender);
        hallDoorEventProcessor.processEvent(doorClosedEvent, smartHome);
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(light3.isOn());
    }

    @Test
    public void testLightsStatesAreNotChangedIfNotHallDoorClosed() {
        Door door = new Door(true, "1");
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", false);
        Light light3 = new Light("3", true);
        Room room = new Room(Arrays.asList(light1, light2, light3), Collections.singletonList(door), "kitchen");
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        SensorEvent doorClosedEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        CommandSender commandSender = new SimpleCommandSender();
        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(commandSender);
        hallDoorEventProcessor.processEvent(doorClosedEvent, smartHome);
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());
        assertTrue(light3.isOn());
    }
}