package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_ShuttlePilot extends CrewMember {

    public CM_ShuttlePilot(int n) {
        super(n);
        infoSpecial = "\nStarts with 4 extra Health.\n";
        name = "Shuttle Pilot";
    }

    @Override
    public void special(GameData gameData) {

    }
}
