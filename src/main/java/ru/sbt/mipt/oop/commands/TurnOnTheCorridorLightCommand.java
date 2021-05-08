package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.*;

public class TurnOnTheCorridorLightCommand implements Command {
    SmartHome smartHome;

    public TurnOnTheCorridorLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Room) {
                Room room = (Room) partOfTheHome;
                if (room.getName().equals("corridor")) {
                    turnOnTheCorridorLight();
                }
            }
        });
    }

    private void turnOnTheCorridorLight() {
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Light) {
                Light lightToUpdate = (Light) partOfTheHome;
                lightToUpdate.setOn(true);
            }
        });
    }
}
