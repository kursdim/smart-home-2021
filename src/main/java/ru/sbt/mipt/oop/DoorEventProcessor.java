package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome){
        smartHome.execute(new Action() {
            @Override
            public void act(PartOfTheHome partOfTheHome) {
                if (partOfTheHome instanceof Door) {
                    Door doorToUpdate = (Door) partOfTheHome;
                    if (doorToUpdate.getId().equals(event.getObjectId())) {
                        updateDoorState(event.getType(), doorToUpdate);
                    }
                }
            }
        });
    }
    private void updateDoorState(SensorEventType EventType, Door doorToUpdate) {
        if (EventType == DOOR_OPEN) {
            doorToUpdate.setOpen(true);
            System.out.println("Door " + doorToUpdate.getId() + " was opened.");
        } else {
            doorToUpdate.setOpen(false);
            System.out.println("Door " + doorToUpdate.getId() + " was closed.");
        }
    }
}
