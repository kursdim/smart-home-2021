package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean
    SensorEventsManager sensorEventsManager() {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = smartHomeReader.readSmartHome();
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        EventHandlersRegistrar.registerEventHandlers(sensorEventsManager, smartHome);
        return sensorEventsManager;
    }
}
