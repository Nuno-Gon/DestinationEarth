package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_CommsOfficer extends CrewMember {

    public CM_CommsOfficer(int n) {
        super(n);
        infoSpecial = "\nBefore an alien attacks this crew member, roll 1 D6, on a 1 or 2 that alien does not attack this officer.\n";
        name = "Comm's Officer";
    }

    @Override
    public void special(GameData gameData) {

    }
}
