package logicaJogo.crewMembers;

import logicaJogo.*;

public abstract class CrewMember {

    protected String name;
    private int movement;
    private int attack;
    private int num;
    protected String infoSpecial;
    private Room currentRoom;

    abstract public void special(GameData gameData);

    public CrewMember(int n) {
        this.num = n;
        this.movement = 1;
        this.attack = 1;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getInfoSpecial() {
        return infoSpecial;
    }

    public void setInfoSpecial(String infoSpecial) {
        this.infoSpecial = infoSpecial;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
