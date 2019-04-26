package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_TransporterChief extends CrewMember {

    public CM_TransporterChief() {
        super();
        infoSpecial = "\nCan teleport to any room on the ship for 1 Action Point.\n";
    }

    @Override
    public void special(GameData gameData) {

    }
}
