package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.PartOfTheHome;
import ru.sbt.mipt.oop.SmartHome;

public class TurnAllTheLightsOnCommand implements Command {
    SmartHome smartHome;

    public TurnAllTheLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(partOfTheHome -> {
            if (partOfTheHome instanceof Light) {
                Light lightToUpdate = (Light) partOfTheHome;
                lightToUpdate.setOn(true);
            }
        });
    }
}
