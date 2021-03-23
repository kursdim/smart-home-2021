package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventProcessorTest {
    @Test
    public void testDoorIsOpenedAfterDoorOpenEvent() {
        Door door = new Door(false, "1");
        Room room = new Room(Collections.emptyList(), Collections.singletonList(door), "living room");
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        SensorEvent doorOpenEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        doorEventProcessor.processEvent(doorOpenEvent, smartHome);
        assertTrue(door.isOpen());
    }

    @Test
    public void testDoorIsClosedAfterDoorClosedEvent() {
        Door door = new Door(true, "1");
        Room room = new Room(Collections.emptyList(), Collections.singletonList(door), "living room");
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        SensorEvent doorClosedEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        doorEventProcessor.processEvent(doorClosedEvent, smartHome);
        assertFalse(door.isOpen());
    }
}