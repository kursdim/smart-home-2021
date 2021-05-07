package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LightEventProcessorTest {
    @Test
    public void testLightIsOnAfterLightOnEvent() {
        Light light = new Light("1", false);
        Room room = new Room(Collections.singletonList(light), Collections.emptyList(), "living room");
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        SensorEvent lightOnEvent = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        LightEventProcessor lightEventProcessor = new LightEventProcessor();
        lightEventProcessor.processEvent(lightOnEvent, smartHome);
        assertTrue(light.isOn());
    }

    @Test
    public void testLightIsOffAfterLightOffEvent() {
        Light light = new Light("1", true);
        Room room = new Room(Collections.singletonList(light), Collections.emptyList(), "living room");
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        SensorEvent lightOffEvent = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        LightEventProcessor lightEventProcessor = new LightEventProcessor();
        lightEventProcessor.processEvent(lightOffEvent, smartHome);
        assertFalse(light.isOn());
    }
}