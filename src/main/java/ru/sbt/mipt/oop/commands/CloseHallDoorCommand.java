package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.*;

public class CloseHallDoorCommand implements Command {
    SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(new Action() {
            @Override
            public void act(PartOfTheHome partOfTheHome) {
                if (partOfTheHome instanceof Door) {
                    Door doorToUpdate = (Door) partOfTheHome;
                    if (HomeUtils.isDoorInHallRoom(doorToUpdate.getId(), smartHome)) {
                        doorToUpdate.setOpen(false);
                    }
                }
            }
        });
        Command turnAllTheLightsOffCommand = new TurnAllTheLightsOffCommand(smartHome);
        turnAllTheLightsOffCommand.execute();
    }
}
