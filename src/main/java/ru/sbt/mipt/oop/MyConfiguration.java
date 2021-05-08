package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.commands.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyConfiguration {
    @Bean
    CommandSender simpleCommandSender() {
        return new SimpleCommandSender();
    }

    @Bean
    SmartHome smartHome() {
        return (new JsonSmartHomeReader("smart-home-1.js")).readSmartHome();
    }

    @Bean
    Map<String, SensorEventType> fromLibraryEventTypesToSensorEventTypesMap(){
        Map<String, SensorEventType> fromLibraryEventTypesToSensorEventTypesMap = new HashMap<>();
        fromLibraryEventTypesToSensorEventTypesMap.put("LightIsOn", SensorEventType.LIGHT_ON);
        fromLibraryEventTypesToSensorEventTypesMap.put("LightIsOff", SensorEventType.LIGHT_OFF);
        fromLibraryEventTypesToSensorEventTypesMap.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        fromLibraryEventTypesToSensorEventTypesMap.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        return fromLibraryEventTypesToSensorEventTypesMap;
    }

    @Bean
    EventProcessor doorEventProcessor() {
        return new DoorEventProcessor();
    }

    @Bean
    EventProcessor hallDoorEventProcessor() {
        return new HallDoorEventProcessor(simpleCommandSender());
    }

    @Bean
    EventProcessor lightEventProcessor() {
        return new LightEventProcessor();
    }

    @Bean
    SensorEventsManager sensorEventsManager(Collection<EventProcessor> eventProcessors) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        for (EventProcessor eventProcessor: eventProcessors) {
            sensorEventsManager.registerEventHandler(new CCSensorEventHandlerToSensorEventProcessorAdapter(eventProcessor,
                    smartHome(), fromLibraryEventTypesToSensorEventTypesMap()));
        }
        return sensorEventsManager;
    }

    @Bean
    Alarm alarm(){
        return smartHome().alarm;
    }

    @Bean
    Command alarmActivateCommand() {
        return new AlarmActivateCommand(alarm(), 1234);
    }

    @Bean
    Command closeHallDoorCommand() {
        return new CloseHallDoorCommand(smartHome());
    }

    @Bean
    Command setAlarmingStateCommand() {
        return new SetAlarmingStateCommand(alarm());
    }

    @Bean
    Command turnAllTheLightsOffCommand() {
        return new TurnAllTheLightsOffCommand(smartHome());
    }

    @Bean
    Command turnAllLightsOnCommand() {
        return new TurnAllTheLightsOnCommand(smartHome());
    }

    @Bean
    Command turnOnTheCorridorLightCommand() {
        return new TurnOnTheCorridorLightCommand(smartHome());
    }


    Map<String, Command> fromButtonsToCommandsMap(){
        Map<String, Command> fromButtonsToCommandsMap = new HashMap<>();
        fromButtonsToCommandsMap.put("A", alarmActivateCommand());
        fromButtonsToCommandsMap.put("B", closeHallDoorCommand());
        fromButtonsToCommandsMap.put("C", setAlarmingStateCommand());
        fromButtonsToCommandsMap.put("D", turnAllTheLightsOffCommand());
        fromButtonsToCommandsMap.put("1", turnAllLightsOnCommand());
        fromButtonsToCommandsMap.put("2", turnOnTheCorridorLightCommand());
        fromButtonsToCommandsMap.put("3", alarmActivateCommand());
        fromButtonsToCommandsMap.put("4", closeHallDoorCommand());
        return fromButtonsToCommandsMap;
    }

    @Bean
    RemoteControl remoteControlImpl() {
        return new RemoteControlImpl(fromButtonsToCommandsMap());
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControlImpl(), "1");
        return remoteControlRegistry;
    }
}
