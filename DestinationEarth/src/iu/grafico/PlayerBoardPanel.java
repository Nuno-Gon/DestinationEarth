package iu.grafico;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import javax.imageio.*;
import logicaEstados.*;

public class PlayerBoardPanel extends JPanel implements Observer {

    ObservableGame game;
    //Image
    private final String imageFiles[] = {"imagens/cDoctor.png", "imagens/cCaptain.png", "imagens/cEngineer.png", "imagens/cCommander.png", "imagens/cRedShirt.png", "imagens/cMoralOfficer.png", "imagens/cCommsOfficer.png", "imagens/cScienceOfficer.png", "imagens/cTransporterChief.png", "imagens/cSecurityOfficer.png", "imagens/cNavigationOfficer.png", "imagens/cShuttlePilot.png"};
    private final Image[] imagem = new Image[imageFiles.length];
    boolean loaded = false, paintCM1 = false, paintCM2 = false;
    //Crew Member panels right side
    ImageIcon cmIMG_1, cmIMG_2;
    int cmNum_1, cmNum_2;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    PlayerBoardPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);
        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
        cmIMG_1 = new ImageIcon();
        cmIMG_2 = new ImageIcon();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if ((game.getState() instanceof AwaitThirdTokenPlacementCM1)) {
            cmNum_1 = game.getCMNum(1);
            cmNum_2 = game.getCMNum(2);
        }

        if ((game.getState() instanceof AwaitThirdTokenPlacementCM1) == false && (game.getState() instanceof AwaitThirdTokenPlacementCM2) == true) {
            paintCM1 = true;

        } else if ((game.getState() instanceof AwaitThirdTokenPlacementCM1) == false && (game.getState() instanceof AwaitThirdTokenPlacementCM2) == false) {
            paintCM2 = true;

        }

        //paint crew member one
        if (paintCM1 == true) {
            cmIMG_1.setImage(getImagem(cmNum_1 - 1));
            cmIMG_1.paintIcon(this, g, 0, 0);
            g2d.setColor(Color.RED);
            g2d.fillRect(50, 77, 38, 36);
            g2d.fillRect(50, 156, 38, 35);
        }
        //paint crew member two
        if (paintCM2 == true) {
            cmIMG_2.setImage(getImagem(cmNum_2 - 1));
            cmIMG_1.paintIcon(this, g, 0, 369);
            g2d.setColor(Color.BLUE);
            g2d.fillRect(50, 446, 38, 36);
            g2d.fillRect(50, 525, 38, 36);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //setVisible(game.getState() instanceof AwaitBeginning);
        setVisible(true);
        revalidate();
        repaint();
    }

    Image getImagem(int i) {

        if (!loaded) {
            int j = 0;
            loaded = true;
            for (String fileName : imageFiles) {
                try {
                    imagem[j] = ImageIO.read(Resources.getResourceFile(fileName));
                    j++;
                } catch (IOException ex) {
                }
            }
        }

        return imagem[i];
    }

}
