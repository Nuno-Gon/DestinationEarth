package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_TransporterChief extends CrewMember {

    public CM_TransporterChief(int n) {
        super(n);
        infoSpecial = "\nCan teleport to any room on the ship for 1 Action Point.\n";
        name = "Transporter Chief";
    }

    @Override
    public void special(GameData gameData) {

    }
}
