package logicaEstados;

import javax.swing.JOptionPane;
import logicaJogo.*;
import logicaJogo.crewMembers.CM_CommsOfficer;
import logicaJogo.crewMembers.CrewMember;

public class AwaitAlienPhase extends StateAdapter {

    boolean atacaOfficer = true;

    public AwaitAlienPhase(GameData gameData) {
        super(gameData);
        if (gameData.getCrewMemberFirst().getNum() == 3
                && gameData.getCrewMemberFirst().getCurrentRoom().getNum() == 6) {
            gameData.setHullTracker(gameData.getHullTracker() + 1);
        }
    }

    /*I. Each alien will move one room towards the closest
    crew member through any open doorways.
    Aliens cannot move through solid walls.

    *II. If an alien ends its move in a room with an Organic Detonator
    it is immediately destroyed (along with the Detonator).
    Place both back in their relevant piles.

    *III. 1If an alien(s) is in a room with no crew members then each
    alien will automatically damage the Hull by 1.
    Remove one from the Hull strength (Hull Tracker).

    *IV. If there is an alien in the same room as a crew member,
    each alien will attack by rolling one die.
    One a roll of FIVE (5) or more the alien hits and one is removed
    from the crew's health (Health Tracker).
     */
    @Override
    public IStates alienPhase() {
        alienMovementI();
        checkOrganicDetonatorII();
        checkAlienAloneIII();
        checkAlienBattleIV();

        gameData.setTurn(gameData.getTurn() + 1);
        if (gameData.getHealthTracker() == 0 || gameData.getHullTracker() == 0) {
            return new GameOver(gameData);
        }

        switch (gameData.getJourneyTrackerIndex(gameData.getTurn())) {
            case "R":
                restPhase();
                if (gameData.getInspirationPoints() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "You have 0 Inspiration Points...", "Rest Phase Skipped!",
                            JOptionPane.WARNING_MESSAGE);
                    return new AwaitAlienSpawn(gameData);
                }
                return new AwaitRestPhase(gameData);
            case "E":
                return new Victory(gameData);
            default:
                return new AwaitAlienSpawn(gameData);
        }
    }

    //Transformar em estados,ações,transições?
    private void restPhase() {
        // transformar em estado e fazer return new rest phase
        //String ss = gameData.getJourneyTrackerIndex(gameData.getTurn());
        //if (ss.length() == 3) { //tem astrisco no alien spawn 2A*
        gameData.getShipRoomList().stream().filter((roomList) -> (roomList.getAliens() > 0)).forEachOrdered((roomList) -> {
            roomList.setAliens(0);
        });

    }

    private void alienMovementI() {
        Room room_cm1 = gameData.getCrewMemberFirst().getCurrentRoom();
        Room room_cm2 = gameData.getCrewMemberSecond().getCurrentRoom();

        //For each room that contains aliens
        gameData.getShipRoomList().stream().filter((roomList) -> (roomList.getAliens() > 0)).forEachOrdered((Room roomList) -> {
            //If there is more than 1 alien in the room, only 1 moves, not a bug, it's a feature

            //For each adjecent room
            roomList.getExits().keySet().forEach((Integer i) -> {
                //cm1 or cm2 found in adjecent room
                if (roomList.getExits().get(i) == room_cm1 || roomList.getExits().get(i) == room_cm2) {
                    //remove alien from current room
                    roomList.setAliens(roomList.getAliens() - 1);
                    //add removed alien in adjecent room
                    roomList.getExits().get(i).setAliens(roomList.getExits().get(i).getAliens() + 1);
                }
            });
        });
    }

    private void checkOrganicDetonatorII() {
        gameData.getShipRoomList().stream().filter((roomList) -> (roomList.getAliens() > 0)).forEachOrdered((Room roomList) -> {
            if (roomList.isTrapOrganicDetonator() == true) {
                roomList.setAliens(0);
                roomList.setTrapOrganicDetonator(false);
            }
        });
    }

    private void checkAlienAloneIII() {
        Room room_cm1 = gameData.getCrewMemberFirst().getCurrentRoom();
        Room room_cm2 = gameData.getCrewMemberSecond().getCurrentRoom();

        //For each room that contains aliens
        gameData.getShipRoomList().stream().filter((roomList) -> (roomList.getAliens() > 0)).forEachOrdered((Room roomList) -> {
            if (roomList != room_cm1 && roomList != room_cm2) {
                gameData.setHullTracker(gameData.getHullTracker() - 1);
            }
        });
    }

    private void checkAlienBattleIV() {
        Room room_cm1 = gameData.getCrewMemberFirst().getCurrentRoom();
        Room room_cm2 = gameData.getCrewMemberSecond().getCurrentRoom();
        CrewMember c_cm1 = gameData.getCrewMemberFirst();
        CrewMember c_cm2 = gameData.getCrewMemberSecond();
        atacaOfficer = true;

        //For each room that contains aliens
        gameData.getShipRoomList().stream().filter((roomList) -> (roomList.getAliens() > 0)).forEachOrdered((Room roomList) -> {
            if (roomList == room_cm1 || roomList == room_cm2) {
                //special CM_CommsOfficer: lanca dado antes de
                // alien phase. se for 1 ou  2 o resultado, nao é atacado
                if (c_cm1 instanceof CM_CommsOfficer) {
                    gameData.setCurrentDice(gameData.rollDice());
                    if (gameData.getCurrentDice() == 1 || gameData.getCurrentDice() == 2) {
                        atacaOfficer = false;
                    }
                }
                if (c_cm2 instanceof CM_CommsOfficer) {
                    gameData.setCurrentDice(gameData.rollDice());
                    if (gameData.getCurrentDice() == 1 || gameData.getCurrentDice() == 2) {
                        atacaOfficer = false;
                    }
                }
                //System.out.println("CommsOfficer resultado lancamento =" + gameData.getCurrentDice());
                if (atacaOfficer == true) {
                    gameData.setCurrentDice(gameData.rollDice());
                    if (gameData.getCurrentDice() >= 5) {
                        gameData.setHealthTracker(gameData.getHealthTracker() - 1);
                        //System.out.println("Alien ataca");
                    }
                    //System.out.println("Alien resutado lanacamento =" + gameData.getCurrentDice());
                }

            }
        });
    }

}
//Create toString to explain that the aliens that ended in the same room as an Organic Detonator died, also OD destroyed
