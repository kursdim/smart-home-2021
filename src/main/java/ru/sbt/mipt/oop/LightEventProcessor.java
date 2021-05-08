package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome){
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Light) {
                Light lightToUpdate = (Light) partOfTheHome;
                if (lightToUpdate.getId().equals(event.getObjectId())) {
                    updateLightState(event.getType(), lightToUpdate);
                }
            }
        });
    }

    private void updateLightState(SensorEventType EventType, Light lightToUpdate) {
        if (EventType == LIGHT_ON) {
            lightToUpdate.setOn(true);
            System.out.println("Light " + lightToUpdate.getId() + " was turned on.");
        } else {
            lightToUpdate.setOn(false);
            System.out.println("Light " + lightToUpdate.getId() + " was turned off.");
        }
    }
}
