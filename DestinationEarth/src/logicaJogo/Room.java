package logicaJogo;

import java.util.*;

public class Room {

    private final String name;
    private final HashMap<Integer, Room> exits;
    private int aliens;

    public Room(String name) {
        exits = new HashMap<>();
        this.name = name;
        this.aliens = 0;
    }

    public HashMap<Integer, Room> getExits() {
        return exits;
    }

    public void setExits(int num, Room neighbor) {
        exits.put(num, neighbor);
    }

    public String getName() {
        return name;
    }

    public int getAliens() {
        return aliens;
    }

    public void setAliens(int aliens) {
        this.aliens = aliens;
    }
}
