package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_SecurityOfficer extends CrewMember {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CM_SecurityOfficer(int n) {
        super(n);
        infoSpecial = "\nStarts with 2D6 for attack.\n";
        setAttack(2);
        name = "Security Officer";
    }

    @Override
    public void special(GameData gameData) {

    }
}
