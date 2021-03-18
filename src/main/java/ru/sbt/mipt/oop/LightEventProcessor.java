package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor{
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() != LIGHT_ON && event.getType() != LIGHT_OFF){
            return;
        }
        Light lightToUpdate = findLightToUpdate(event.getObjectId(), smartHome);
        if (lightToUpdate != null){
            updateLightState(event.getType(), lightToUpdate);
        }
    }

    private Light findLightToUpdate(String lightId, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    return light;
                }
            }
        }
        return null;
    }

    private void updateLightState(SensorEventType EventType, Light lightToUpdate){
        if (EventType == LIGHT_ON) {
            lightToUpdate.setOn(true);
            System.out.println("Light " + lightToUpdate.getId() + " was turned on.");
        } else {
            lightToUpdate.setOn(false);
            System.out.println("Light " + lightToUpdate.getId() + " was turned off.");
        }
    }
}
