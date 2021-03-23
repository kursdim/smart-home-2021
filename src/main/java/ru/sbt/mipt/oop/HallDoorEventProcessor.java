package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {
    private final CommandSender commandSender;

    public HallDoorEventProcessor(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        smartHome.execute(new Action() {
            @Override
            public void act(PartOfTheHome partOfTheHome) {
                if (partOfTheHome instanceof Door) {
                    Door door = (Door) partOfTheHome;
                    if (door.getId().equals(event.getObjectId())) {
                        if (HomeUtils.isDoorInHallRoom(door.getId(), smartHome) && event.getType() == DOOR_CLOSED) {
                            turnAllLightsOff(smartHome);
                        }
                    }
                }
            }
        });
    }

    private void turnAllLightsOff(SmartHome smartHome) {
        smartHome.execute(new Action() {
            @Override
            public void act(PartOfTheHome partOfTheHome) {
                if (partOfTheHome instanceof Light) {
                    Light lightToUpdate = (Light) partOfTheHome;
                    lightToUpdate.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, lightToUpdate.getId());
                    commandSender.sendCommand(command);
                }
            }
        });
    }
}
