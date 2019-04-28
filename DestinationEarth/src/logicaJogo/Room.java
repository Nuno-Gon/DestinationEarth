package logicaJogo;

import java.util.*;

public class Room {

    private final String name;
    private final int num;
    private final HashMap<Integer, Room> exits;
    private int aliens;
    private boolean trapOrganicDetonator;
    private boolean trapParticleDisperser;
    private boolean sealed;

    public Room(String name, int num) {
        exits = new HashMap<>();
        this.name = name;
        this.aliens = 0;
        this.num = num;
        this.trapOrganicDetonator = false;
        this.trapParticleDisperser = false;
        this.sealed = false;
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

    public boolean isTrapOrganicDetonator() {
        return trapOrganicDetonator;
    }

    public void setTrapOrganicDetonator(boolean trapOrganicDetonator) {
        this.trapOrganicDetonator = trapOrganicDetonator;
    }

    public boolean isTrapParticleDisperser() {
        return trapParticleDisperser;
    }

    public void setTrapParticleDisperser(boolean trapParticleDisperser) {
        this.trapParticleDisperser = trapParticleDisperser;
    }

    public int getNum() {
        return num;
    }

    public boolean isSealed() {
        return sealed;
    }

    public void setSealed(boolean sealed) {
        this.sealed = sealed;
    }
}
