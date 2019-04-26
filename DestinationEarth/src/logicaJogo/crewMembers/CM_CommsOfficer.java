package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_CommsOfficer extends CrewMember {

    public CM_CommsOfficer() {
        super();
        infoSpecial = "\nBefore an alien attacks this crew member, roll 1 D6, on a 1 or 2 that alien does not attack this officer.\n";
    }

    @Override
    public void special(GameData gameData) {

    }
}
