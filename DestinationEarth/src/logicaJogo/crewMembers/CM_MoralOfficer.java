package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_MoralOfficer extends CrewMember {

    public CM_MoralOfficer(int n) {
        super(n);
        infoSpecial = "\nStarts with 5 Inspirational Points.\n";
        name = "Moral Officer";
    }

    @Override
    public void special(GameData gameData) {

    }
}
