package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_Commander extends CrewMember {

    public CM_Commander() {
        super();
        infoSpecial = "\n6 Action Points per turn instead of 5.\n";
        name = "Commander";
    }

    @Override
    public void special(GameData gameData) {

    }
}
