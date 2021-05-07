package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = smartHomeReader.readSmartHome();
        Alarm alarm = new Alarm(1234);
        smartHome.setAlarm(alarm);
        SensorEventCreator sensorEventCreator = new RandomSensorEventCreator();
        CommandSender commandSender = new SimpleCommandSender();
        List<EventProcessor> eventProcessors = Arrays.asList(
                new EventProcessorDecorator(new LightEventProcessor()),
                new EventProcessorDecorator(new DoorEventProcessor()),
                new EventProcessorDecorator(new HallDoorEventProcessor(commandSender)),
                new EventProcessorDecorator(new AlarmEventProcessor()));
        EventLoopProcessor eventLoopProcessor = new EventLoopProcessor(smartHome, sensorEventCreator, eventProcessors);
        eventLoopProcessor.startProcessingEvents();
    }
}