package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class DoorEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() != DOOR_OPEN && event.getType() != DOOR_CLOSED) {
            return;
        }
        Door doorToUpdate = findDoorToUpdate(event.getObjectId(), smartHome);
        if (doorToUpdate != null){
            updateDoorState(event.getType(), doorToUpdate);
        }
    }

    private Door findDoorToUpdate(String doorId, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    return door;
                }
            }
        }
        return null;
    }

    private void updateDoorState(SensorEventType EventType, Door doorToUpdate){
        if (EventType == DOOR_OPEN) {
            doorToUpdate.setOpen(true);
            System.out.println("Door " + doorToUpdate.getId() + " was opened.");
        } else {
            doorToUpdate.setOpen(false);
            System.out.println("Door " + doorToUpdate.getId() + " was closed.");
        }
    }
}
