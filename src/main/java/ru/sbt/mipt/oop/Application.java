package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = smartHomeReader.readSmartHome();
        SensorEventCreator sensorEventCreator = new RandomSensorEventCreator();
        CommandSender commandSender = new SimpleCommandSender();
        List<EventProcessor> eventProcessors = Arrays.asList(new LightEventProcessor(),
                new DoorEventProcessor(), new HallDoorEventProcessor(commandSender));
        EventLoopProcessor eventLoopProcessor = new EventLoopProcessor(smartHome, sensorEventCreator, eventProcessors);
        eventLoopProcessor.startProcessingEvents();
    }
}