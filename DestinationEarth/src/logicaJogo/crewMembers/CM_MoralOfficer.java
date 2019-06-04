package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_MoralOfficer extends CrewMember {
    GameData g;
    public CM_MoralOfficer(int n,GameData gameData) {
        super(n);
        g = gameData;
        infoSpecial = "\nStarts with 5 Inspirational Points.\n";
        name = "Moral Officer";
        special(g);
    }

    @Override
    public void special(GameData gameData) {
        gameData.setInspirationPoints(gameData.getInspirationPoints() + 5); //special
    }
}
