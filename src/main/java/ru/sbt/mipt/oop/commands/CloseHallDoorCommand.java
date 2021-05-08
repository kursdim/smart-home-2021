package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.*;

public class CloseHallDoorCommand implements Command {
    SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Room) {
                Room room = (Room) partOfTheHome;
                if (room.getName().equals("hall")) {
                    closeHallDoor();
                }
            }
        });
    }

    private void closeHallDoor() {
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Door) {
                Door door = (Door) partOfTheHome;
                door.setOpen(false);
                turnAllLightsOff(smartHome);
            }
        });
    }

    private void turnAllLightsOff(SmartHome smartHome) {
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Light) {
                Light lightToUpdate = (Light) partOfTheHome;
                lightToUpdate.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, lightToUpdate.getId());
                (new SimpleCommandSender()).sendCommand(command);
            }
        });
    }
}
