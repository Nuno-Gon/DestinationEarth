package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_Captain extends CrewMember {

    public CM_Captain() {
        super();
        infoSpecial = "\nCan attack an alien on a 3+.\n";
    }

    @Override
    public void special(GameData gameData) {

    }
}
