package iu.texto;
import java.time.temporal.TemporalAdjusters;
import logicaJogo.*;
import java.util.*;

public class TextUserInterface {
    private final Game g;
    private boolean sair = false;
    Scanner sc;
        
    public TextUserInterface(Game game){
        g = game; 
    }
	
    public void run(){
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
        menuPrincipal();
    }
    
    void menuPrincipal(){
        while(true){
            System.out.println("  --> Menu Principal <--");
            System.out.println("\t0 - Sair");
            System.out.println("\t1 - Jogar");
            System.out.print("\t>> ");
            char c = ' ';
            sc = new Scanner(System.in);
            c = sc.next().charAt(0);
            if((c == '0')) {
                sair = true;
                return;
            }
            if((c == '1')) { //Esta a sair tambem. / exemplo de opcao de menu 
                sair = true;
                return;
            }
            
        }
    }
}