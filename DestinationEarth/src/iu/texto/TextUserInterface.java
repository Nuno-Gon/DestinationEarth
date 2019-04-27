package iu.texto;

import logicaJogo.*;
import java.util.*;
import logicaEstados.*;
import logicaJogo.crewMembers.*;

public class TextUserInterface {

    Scanner sc;
    private final Game g;
    private boolean sair = false;

    public TextUserInterface(Game game) {
        g = game;
        sc = new Scanner(System.in);
    }

    public void run() {
        while (!sair) {
            menuPrincipal();
        }
    }

    public void menuPrincipal() {
        System.out.println("\n**********************************************************************\n");
        System.out.println(" _____              _    _  _    _                _    _");
        System.out.println("|  __ \\            | |  (_)| |  (_)              | |  (_)");
        System.out.println("| |  | |  ___  ___ | |_  _ | |_  _  _ __    __ _ | |_  _   ___   _ __");
        System.out.println("| |  | | / _ \\/ __|| __|| || __|| || '_ \\  / _` || __|| | / _ \\ | '_ \\");
        System.out.println("| |__| ||  __/\\__ \\| |_ | || |_ | || | | || (_| || |_ | || (_) || | | |");
        System.out.println("|_____/  \\___||___/ \\__||_| \\__||_||_| |_| \\__,_| \\__||_| \\___/ |_| |_|\n");
        System.out.println("\t    _______  _______  _______ _________         ");
        System.out.println("\t   (  ____ \\(  ___  )(  ____ )\\__   __/|\\     /|");
        System.out.println("\t   | (    \\/| (   ) || (    )|   ) (   | )   ( |");
        System.out.println("\t   | (__    | (___) || (____)|   | |   | (___) |");
        System.out.println("\t   |  __)   |  ___  ||     __)   | |   |  ___  |");
        System.out.println("\t   | (      | (   ) || (\\ (      | |   | (   ) |");
        System.out.println("\t   | (____/\\| )   ( || ) \\ \\__   | |   | )   ( |");
        System.out.println("\t   (_______/|/     \\||/   \\__/   )_(   |/     \\|");
        System.out.println("\n**********************************************************************");
        System.out.println("\t\t   ===== Prod PaRiS & Psyk0 =====\n\n");

        while (true) {
            System.out.println("  --> Menu Principal <--");
            System.out.println("\t1 - Jogar");
            System.out.println("\t2 - Guardar");
            System.out.println("\t3 - Carregar");
            System.out.println("\t4 - Sair");
            System.out.print("\t>> ");
            int c = sc.nextInt();
            switch (c) {
                case 1:
                    //Opcao para Jogar o jogo
                    jogar();
                    break;
                case 2:
                    //Opcao para guardar o jogo
                    System.out.println("Guardar Jogo Atual...\n");
                    break;
                case 3:
                    //Opcao para carregar o jogo guardado
                    System.out.println("Carregar Jogo Anterior...\n");
                    break;
                case 4:
                    //Opcao para sair do programa
                    System.err.println("Abandonou o jogo. Ate a proxima!");
                    sair = true;
                    return;
                default:
                    System.err.println("Opcao errada!");
                    break;
            }
        }
    }

    public void jogar() {
        while (true) {
            if (g.getState() instanceof AwaitBeginning) {
                iuAwaitBeginning();
            } else if (g.getState() instanceof AwaitCrewMembersSelection) {
                iuAwaitCrewMembersSelection();
            } else if (g.getState() instanceof AwaitThirdTokenPlacementCM1) {
                iuAwaitThirdTokenPlacementCM1();
            } else if (g.getState() instanceof AwaitThirdTokenPlacementCM2) {
                iuAwaitThirdTokenPlacementCM2();
            } else if (g.getState() instanceof AwaitAlienSpawn) {
                iuAwaitAlienSpawn();
            } else if (g.getState() instanceof AwaitCrewPhase) {
                iuAwaitCrewPhase();
            } else if (g.getState() instanceof AwaitMove) {
                iuAwaitMove();
            } else if (g.getState() instanceof AwaitDieRoll) {
                iuAwaitDieRoll();
            } else if (g.getState() instanceof AwaitRestPhase) {
                return;
            }
        }
    }

    public void iuAwaitBeginning() {
        infoGame();
        g.resolveStart();
    }

    public void infoGame() {
        System.out.println();
        System.out.println("Journey Tracker: " + Arrays.toString(g.getGameData().getJourneyTracker()));
        System.out.println(">> " + g.getGameData().getJourneyTrackerIndex(g.getGameData().getTurn()));
        System.out.println("Turn: " + g.getGameData().getTurn());
        System.out.println("Hull: " + g.getGameData().getHullTracker());
        System.out.println("Health: " + g.getGameData().getHealthTracker());
        System.out.println("Inspiration Points: " + g.getGameData().getInspirationPoints());

    }

    private void iuAwaitCrewMembersSelection() {
        int i = 1;
        if (g.getGameData().getCrewMemberFirst() != null) {
            i += 1;
        }
        System.out.println();
        System.out.println("  --> Select Crew Member #" + i + " <--");
        System.out.println("\t0 - Random");
        System.out.println("\t1 - Doctor");
        System.out.println("\t2 - Captain");
        System.out.println("\t3 - Engineer");
        System.out.println("\t4 - Commander");
        System.out.println("\t5 - Red Shirt");
        System.out.println("\t6 - Moral Officer");
        System.out.println("\t7 - Comm's Officer");
        System.out.println("\t8 - Science Officer");
        System.out.println("\t9 - Transporter Chief");
        System.out.print("\t>>");

        int x = sc.nextInt();
        //show special and confirm selection
        System.out.println();
        System.out.print("Special:");
        showSpecial(x);
        System.out.println("Confirm Crew Member (Y/N)");
        System.out.print(">>");
        char c = sc.next().charAt(0);
        c = Character.toUpperCase(c);
        if (c == 'Y') {
            g.selectCrewMember(x);
        } else {
            iuAwaitCrewMembersSelection();
        }
    }

    private void showSpecial(int x) {
        CrewMember k;
        switch (x) {
            case 1:
                k = new CM_Doctor();
                System.out.println(k.getInfoSpecial());
                break;
            case 2:
                k = new CM_Captain();
                System.out.println(k.getInfoSpecial());
                break;
            case 3:
                k = new CM_Engineer();
                System.out.println(k.getInfoSpecial());
                break;
            case 4:
                k = new CM_Commander();
                System.out.println(k.getInfoSpecial());
                break;
            case 5:
                k = new CM_RedShirt();
                System.out.println(k.getInfoSpecial());
                break;
            case 6:
                k = new CM_MoralOfficer();
                System.out.println(k.getInfoSpecial());
                break;
            case 7:
                k = new CM_CommsOfficer();
                System.out.println(k.getInfoSpecial());
                break;
            case 8:
                k = new CM_ScienceOfficer();
                System.out.println(k.getInfoSpecial());
                break;
            case 9:
                k = new CM_TransporterChief();
                System.out.println(k.getInfoSpecial());
                break;
        }
    }

    private void iuAwaitThirdTokenPlacementCM1() {
        System.out.println();
        System.out.println("  --> Place Crew Member #1 <--");
        System.out.println("\t1 - Select room");
        System.out.println("\t2 - Choose random");
        System.out.print("\t>>");
        int x = sc.nextInt();
        switch (x) {
            case 1:
                showRoomList();
                System.out.print("\t>>");
                x = sc.nextInt();
                g.thirdTokenPlacementCM1(x);
                break;
            case 2:
                System.out.println("\nYou rolled 2D6 for Crew Member #1 placement!");
                x = 420;
                g.thirdTokenPlacementCM1(x);
                System.out.println("Dice value: " + g.getGameData().getCurrentDice());
                System.out.println("Room placed in: " + g.getGameData().getCrewMemberFirst().getCurrentRoom().getName());
                break;
            default:
                iuAwaitThirdTokenPlacementCM1();
                break;
        }
    }

    private void showRoomList() {
        int i = 1;
        System.out.println("\n  --> Room List <--");
        for (Room roomList : g.getGameData().getShipRoomList()) {
            System.out.println("\t" + i + " - " + roomList.getName());
            i++;
        }
    }

    private void iuAwaitThirdTokenPlacementCM2() {
        System.out.println();
        System.out.println("  --> Place Crew Member #2 <--");
        System.out.println("\t1 - Select room");
        System.out.println("\t2 - Choose random");
        System.out.print("\t>>");
        int x = sc.nextInt();
        switch (x) {
            case 1:
                showRoomList();
                System.out.print("\t>>");
                x = sc.nextInt();
                g.thirdTokenPlacementCM2(x);
                break;
            case 2:
                System.out.println("\nYou rolled 2D6 for Crew Member #2 placement!");
                x = 420;
                g.thirdTokenPlacementCM2(x);
                System.out.println("Dice value: " + g.getGameData().getCurrentDice());
                System.out.println("Room placed in: " + g.getGameData().getCrewMemberSecond().getCurrentRoom().getName());
                break;
            default:
                iuAwaitThirdTokenPlacementCM2();
                break;
        }
    }

    private void iuAwaitAlienSpawn() {
        infoGame();
        g.alienPlacment();
        showAliens();
    }

    private void showAliens() {
        System.out.println("\n  --> Aliens Locations <--");
        g.getGameData().getShipRoomList().stream().filter((roomList) -> (roomList.getAliens() > 0)).forEachOrdered((roomList) -> {
            System.out.println("\t" + roomList.getName() + " -> " + roomList.getAliens());
        });
    }

    private void iuAwaitCrewPhase() {
        System.out.println();
        System.out.println("  --> Crew Phase <--");
        System.out.println("\tEach action uses 1 AP.");
        System.out.println("\tYou have: " + g.getGameData().getActionPoints() + " AP");
        System.out.println("\t1 - Move");
        System.out.println("\t2 - Attack");
        System.out.println("\t3 - Heal one Health (Doctor Only)");
        System.out.println("\t4 - Fix one Hull [Engineer Only]");
        System.out.println("\t5 - Setting Trap");
        System.out.println("\t6 - Detonate Particle Disperser");
        System.out.println("\t7 - Seal Room");
        System.out.print("\t>>");
        int x = sc.nextInt();
        switch (x) {
            case 1:
                g.move();
                break;
            case 2:
                g.attack();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                g.sealRoom();
                break;
            default:
                break;
        }
    }

    private void iuAwaitDieRoll() {
        System.out.println();
        System.out.println("  --> Dice <--");
        System.out.println("\t1 - Roll die");
        System.out.println("\t2 - Choose number");
        System.out.print("\t>>");
        int x = sc.nextInt();
        switch (x) {
            case 1:
                break;
            case 2:
                break;
            default:
                iuAwaitDieRoll();
        }
    }

    private void iuAwaitMove() {
        int i = 1, x = 0;
        System.out.println();
        System.out.println("  --> Movement <--");
        System.out.println("\t1 - " + g.getGameData().getCrewMemberFirst().getName());
        System.out.println("\t2 - " + g.getGameData().getCrewMemberSecond().getName());
        System.out.print("\t>>");
        int cm = sc.nextInt();
        Room r;
        switch (cm) {
            case 1:
                r = g.getGameData().getCrewMemberFirst().getCurrentRoom();
                r = showConnectedRooms(r);
                g.moveCM(r, 1);
                break;
            case 2:
                r = g.getGameData().getCrewMemberSecond().getCurrentRoom();
                r = showConnectedRooms(r);
                g.moveCM(r, 2);
                break;
            default:
                System.out.println("Not an option.");
                iuAwaitMove();
                break;
        }
    }

    private Room showConnectedRooms(Room r) {
        System.out.println();
        System.out.println(" --> Connected Rooms <--");
        System.out.println("Current: " + r.getName());
        r.getExits().keySet().forEach((Integer i) -> {
            System.out.println("\tRoom: " + i);
        });
        System.out.print("Choose a room >>");
        int x = sc.nextInt();

        return r.getExits().get(x);
    }
}
