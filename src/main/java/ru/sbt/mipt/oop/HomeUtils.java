package ru.sbt.mipt.oop;

public class HomeUtils {
    public static boolean isDoorInHallRoom(String doorId, SmartHome smartHome) {
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
}
