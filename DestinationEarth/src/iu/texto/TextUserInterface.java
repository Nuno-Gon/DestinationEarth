package iu.texto;
import java.time.temporal.TemporalAdjusters;
import logicaJogo.*;
import java.util.*;

public class TextUserInterface {
    private final Game g;
    private boolean sair = false;
    private boolean virgem = true;
    Scanner sc;
        
    public TextUserInterface(Game game){
        g = game; 
        sc = new Scanner(System.in);
    }
	
    public void run(){
        while((virgem == true)){
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
            virgem = false;
        }
        
        while(!sair) {
            menuPrincipal();
        }
//  Codigo da funcao corre() > exercicio do 3 em linha:        
//        while (!sair) {
//            IEstado estado = jogo.getEstado();
//            if (estado instanceof AguardaInicio) {
//                iuAguardaInicio();
//            } else if (estado instanceof AguardaColocacao) {
//                iuAguardaColocacao();
//            } else if (estado instanceof AguardaDevolucao) {
//                iuAguardaDevolucao();
//            }
//        }
    }
    
    void menuPrincipal(){
        while(true){
            System.out.println("  --> Menu Principal <--");
            System.out.println("\t0 - Sair");
            System.out.println("\t1 - Jogar");
            System.out.println("\t2 - Guardar");
            System.out.println("\t3 - Carregar");
            System.out.print("\t>> ");
            char c = ' ';
            c = sc.next().charAt(0);
            
            if((c == '0')) {    //Opcao para sair do programa
                System.err.println("Abandonou o jogo. Ate a proxima!");
                sair = true;
                return;
            }
            
            if((c == '1')) {    //Opcao para Jogar o jogo
                System.out.println("Jogar...\n");
            }
            
            if((c == '2')) {   //Opcao para guardar o jogo
                System.out.println("Guardar Jogo Atual...\n");
            }
            
            if((c == '3')) {  //Opcao para carregar o jogo guardado
                System.out.println("Carregar Jogo Anterior...\n");
            }
            
        }
    }
}