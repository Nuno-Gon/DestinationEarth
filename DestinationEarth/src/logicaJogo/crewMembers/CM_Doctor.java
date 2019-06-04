package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_Doctor extends CrewMember {

    public CM_Doctor(int n) {
        super(n);
        infoSpecial = "\nCan heal 2 Health for 1 AP when resting.\nCan heal 1 Health for 1 AP.\nCan heal 1 Health per round for free if in Sickbay.\n";
        name = "Doctor";
    }

    @Override
    public void special(GameData gameData) {
        
    }

}
