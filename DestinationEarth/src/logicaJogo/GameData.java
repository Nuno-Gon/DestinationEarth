package logicaJogo;

import logicaJogo.crewMembers.*;
import java.util.*;

public class GameData { //Dados do jogo
    ////-----------JOURNEY TRACKER-----------

    private String[] journeyTracker;
    private int turn;

    //--------------SHIP BOARD--------------
    private List<Room> shipRoomList;

    //-------------PLAYER BOARD-------------
    //Health Tracker
    private int healthTracker;
    //Hull Tracker
    private int hullTracker;
    //Inspiration Points
    private int inspirationPoints;
    //Crew Members
    private CrewMember crewMemberFirst, crewMemberSecond;
    //Current Dice
    private int currentDice;

    public GameData() {
        this.journeyTracker = new String[]{"S", "2A", "3A", "4A", "5A*", "R", "4A", "5A", "6A*", "R", "6A", "7A*", "R", "8A", "E"};
        this.turn = 0;
        this.healthTracker = 8;
        this.hullTracker = 8;
        this.inspirationPoints = 0;
        this.crewMemberFirst = null;
        this.crewMemberSecond = null;
        createRooms();
    }

    private void createRooms() {
        Room bridge, sickBay, brig, crewQuarters, conferenceRoom, shuttleBay, weaponsBay, messHall, engineering, astrometics, holodeck, hydroponics;
        bridge = new Room("Bridge");
        sickBay = new Room("Sick Bay");
        brig = new Room("Brig");
        crewQuarters = new Room("Crew Quarters");
        conferenceRoom = new Room("Conference Room");
        shuttleBay = new Room("Shuttle Bay");
        weaponsBay = new Room("Weapons Bay");
        messHall = new Room("Mess Hall");
        engineering = new Room("Engineering");
        astrometics = new Room("Astrometics");
        holodeck = new Room("Holodeck");
        hydroponics = new Room("Hydroponics");

        bridge.setExits(5, conferenceRoom);
        bridge.setExits(8, messHall);
        sickBay.setExits(7, weaponsBay);
        sickBay.setExits(8, messHall);
        brig.setExits(5, conferenceRoom);
        brig.setExits(9, engineering);
        crewQuarters.setExits(8, messHall);
        crewQuarters.setExits(11, holodeck);
        conferenceRoom.setExits(1, bridge);
        conferenceRoom.setExits(3, brig);
        conferenceRoom.setExits(8, messHall);
        conferenceRoom.setExits(10, astrometics);
        shuttleBay.setExits(2, sickBay);
        shuttleBay.setExits(10, astrometics);
        weaponsBay.setExits(2, sickBay);
        weaponsBay.setExits(11, holodeck);
        messHall.setExits(1, bridge);
        messHall.setExits(2, sickBay);
        messHall.setExits(4, crewQuarters);
        messHall.setExits(5, conferenceRoom);
        engineering.setExits(3, brig);
        engineering.setExits(12, hydroponics);
        astrometics.setExits(5, conferenceRoom);
        astrometics.setExits(12, hydroponics);
        holodeck.setExits(4, crewQuarters);
        holodeck.setExits(7, weaponsBay);
        hydroponics.setExits(9, engineering);
        hydroponics.setExits(10, astrometics);

        shipRoomList = new ArrayList<>();
        shipRoomList.add(bridge);
        shipRoomList.add(sickBay);
        shipRoomList.add(brig);
        shipRoomList.add(crewQuarters);
        shipRoomList.add(conferenceRoom);
        shipRoomList.add(shuttleBay);
        shipRoomList.add(weaponsBay);
        shipRoomList.add(messHall);
        shipRoomList.add(engineering);
        shipRoomList.add(astrometics);
        shipRoomList.add(holodeck);
        shipRoomList.add(hydroponics);
    }

    public String[] getJourneyTracker() {
        return journeyTracker;
    }

    public void setJourneyTracker(String[] journeyTracker) {
        this.journeyTracker = journeyTracker;
    }

    public int getHealthTracker() {
        return healthTracker;
    }

    public void setHealthTracker(int healthTracker) {
        this.healthTracker = healthTracker;
    }

    public int getHullTracker() {
        return hullTracker;
    }

    public void setHullTracker(int hullTracker) {
        this.hullTracker = hullTracker;
    }

    public int getInspirationPoints() {
        return inspirationPoints;
    }

    public void setInspirationPoints(int inspirationPoints) {
        this.inspirationPoints = inspirationPoints;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public CrewMember getCrewMemberFirst() {
        return crewMemberFirst;
    }

    public void setCrewMemberFirst(CrewMember crewMemberFirst) {
        this.crewMemberFirst = crewMemberFirst;
    }

    public CrewMember getCrewMemberSecond() {
        return crewMemberSecond;
    }

    public void setCrewMemberSecond(CrewMember crewMemberSecond) {
        this.crewMemberSecond = crewMemberSecond;
    }

    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    public int getCurrentDice() {
        return currentDice;
    }

    public void setCurrentDice(int currentDice) {
        this.currentDice = currentDice;
    }

    public List<Room> getShipRoomList() {
        return shipRoomList;
    }

    public void setShipRoomList(List<Room> shipRoomList) {
        this.shipRoomList = shipRoomList;
    }

    public Room getIndexShipRoomList(int x) {
        return this.shipRoomList.get(x);
    }
}
