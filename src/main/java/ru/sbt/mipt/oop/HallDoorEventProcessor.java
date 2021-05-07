package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {
    private final CommandSender commandSender;

    public HallDoorEventProcessor(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Room) {
                Room room = (Room) partOfTheHome;
                if (room.getName().equals("hall")) {
                    checkIfThereIsTheDoorWithNeededIdAndTurnAllLightsOff(event.getObjectId(), smartHome);
                }
            }
        });
    }

    private void checkIfThereIsTheDoorWithNeededIdAndTurnAllLightsOff(String doorId, SmartHome smartHome) {
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Door) {
                Door door = (Door) partOfTheHome;
                if (door.getId().equals(doorId)) {
                    turnAllLightsOff(smartHome);
                }
            }
        });
    }

    private void turnAllLightsOff(SmartHome smartHome) {
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Light) {
                Light lightToUpdate = (Light) partOfTheHome;
                lightToUpdate.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, lightToUpdate.getId());
                commandSender.sendCommand(command);
            }
        });
    }
}
