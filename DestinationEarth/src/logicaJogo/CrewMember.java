package logicaJogo;

public abstract class CrewMember {
    public int movement;
    public int attack;
    abstract public void special(GameData gameData);
    
    public CrewMember(){
        this.movement = 1;
        this.attack = 106;
    }
}
