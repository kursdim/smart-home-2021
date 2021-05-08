package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
