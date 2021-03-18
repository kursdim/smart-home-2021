package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor{
    private final CommandSender commandSender;

    public HallDoorEventProcessor(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }
        if (isClosedDoorInHallRoom(event.getObjectId(), smartHome)){
            switchOffAllLights(smartHome);
        }
    }

    private boolean isClosedDoorInHallRoom(String doorId, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    if (room.getName().equals("hall")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void switchOffAllLights(SmartHome smartHome){
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        }
    }
}
