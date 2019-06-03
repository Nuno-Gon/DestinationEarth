package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_Captain extends CrewMember {

    public CM_Captain(int n) {
        super(n);
        infoSpecial = "\nCan attack an alien on a 3+.\n";
        name = "Captain";
    }

    @Override
    public void special(GameData gameData) {

    }
}
