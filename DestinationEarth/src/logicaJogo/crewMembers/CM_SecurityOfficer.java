package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_SecurityOfficer extends CrewMember {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CM_SecurityOfficer(int n) {
        super(n);
        infoSpecial = "\nStarts with 2D6 for attack.\n";
        name = "Security Officer";
        this.attack = 2;
    }

    @Override
    public void special(GameData gameData) {

    }
}
