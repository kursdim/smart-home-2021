package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;

public class EventHandlersRegistrar {
    public static void registerEventHandlers(SensorEventsManager sensorEventsManager, SmartHome smartHome) {
        registerLightEventProcessor(sensorEventsManager, smartHome);
        registerDoorEventProcessor(sensorEventsManager, smartHome);
        registerHallDoorEventProcessor(sensorEventsManager, smartHome);
    }

    private static void registerLightEventProcessor(SensorEventsManager sensorEventsManager, SmartHome smartHome) {
        LightEventProcessor lightEventProcessor = new LightEventProcessor();
        sensorEventsManager.registerEventHandler(new EventHandler() {
            @Override
            public void handleEvent(CCSensorEvent event) {
                SensorEvent adaptedEvent = CCSensorEventToSensorEventAdapter.adaptEventToSensorEvent(event);
                if (adaptedEvent != null) {
                    lightEventProcessor.processEvent(adaptedEvent, smartHome);
                } else {
                    System.out.println("Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId());
                }
            }
        });
    }

    private static void registerDoorEventProcessor(SensorEventsManager sensorEventsManager, SmartHome smartHome) {
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        sensorEventsManager.registerEventHandler(new EventHandler() {
            @Override
            public void handleEvent(CCSensorEvent event) {
                SensorEvent adaptedEvent = CCSensorEventToSensorEventAdapter.adaptEventToSensorEvent(event);
                if (adaptedEvent != null) {
                    doorEventProcessor.processEvent(adaptedEvent, smartHome);
                } else {
                    System.out.println("Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId());
                }
            }
        });
    }

    private static void registerHallDoorEventProcessor(SensorEventsManager sensorEventsManager, SmartHome smartHome) {
        CommandSender commandSender = new SimpleCommandSender();
        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(commandSender);
        sensorEventsManager.registerEventHandler(new EventHandler() {
            @Override
            public void handleEvent(CCSensorEvent event) {
                SensorEvent adaptedEvent = CCSensorEventToSensorEventAdapter.adaptEventToSensorEvent(event);
                if (adaptedEvent != null) {
                    hallDoorEventProcessor.processEvent(adaptedEvent, smartHome);
                } else {
                    System.out.println("Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId());
                }
            }
        });
    }
}
