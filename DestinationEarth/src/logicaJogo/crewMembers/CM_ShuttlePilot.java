package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_ShuttlePilot extends CrewMember {
    GameData g;
    public CM_ShuttlePilot(int n,GameData gameData) {
        super(n);
        g = gameData;
        infoSpecial = "\nStarts with 4 extra Health.\n";
        name = "Shuttle Pilot";
        special(g);
    }

    @Override
    public void special(GameData gameData) {
        gameData.setHealthTracker(gameData.getHealthTracker() + 4);
    }
}
