package iu.grafico;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import logicaEstados.*;
import javax.imageio.*;
import javax.swing.border.*;

public class DestinationEarthPanel extends JPanel implements Observer {

    private final ObservableGame game;
    //Panels & Buttons
    private CMSelectionPanel cmsPanel;
    private StartOptionPanel startPanel;
    private ShipBoardPanel shipBPanel;
    private PlayerBoardPanel playerBPanel;
    private GamingPanel gamingPanel;
    //Image
    private final String imgInicial = "imagens/space.gif";
    private Image bg;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DestinationEarthPanel(ObservableGame game) {
        this.game = game;

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(size);
        getBgGif(size);
        setupComponents();
        setupLayout();
    }

    private void getBgGif(Dimension size) {
        try {
            int w = size.width;
            int h = size.height;
            bg = Toolkit.getDefaultToolkit().createImage(Resources.getResourceFile(imgInicial));
            bg = bg.getScaledInstance(w, h, 0);
        } catch (Exception e) {
        }
    }

    private void setupComponents() {
        startPanel = new StartOptionPanel(game);
        cmsPanel = new CMSelectionPanel(game);
        gamingPanel = new GamingPanel(game);

        shipBPanel = new ShipBoardPanel(game);
        shipBPanel.setPreferredSize(new Dimension(450, 100));
        playerBPanel = new PlayerBoardPanel(game);
        playerBPanel.setPreferredSize(new Dimension(220, 100));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBackground(Color.darkGray);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!(game.getState() instanceof AwaitBeginning)) {
            g.drawImage(bg, 0, 0, this);
        }
        if ((game.getState() instanceof AwaitBeginning)) {
            add(startPanel, BorderLayout.CENTER);
            startPanel.setVisible(true);
//            playerBPanel.setVisible(false);
//            shipBPanel.setVisible(false);
        } else if ((game.getState() instanceof AwaitCrewMembersSelection)) {
            add(cmsPanel, BorderLayout.CENTER);
            remove(startPanel);
            startPanel.setVisible(false);
            playerBPanel.setVisible(false);
            startPanel.setVisible(false);
            shipBPanel.setVisible(false);
        } else {
            remove(cmsPanel);
            remove(startPanel);
            add(shipBPanel, BorderLayout.WEST);
            add(gamingPanel, BorderLayout.CENTER);
            add(playerBPanel, BorderLayout.EAST);
            if (game.getState() instanceof AwaitAlienSpawn) {
                game.alienSpawn();
            }
            if (game.getState() instanceof AwaitCrewPhase) {
                game.alienSpawn();
                game.alienPhase();
                if (game.getActionPoints() == 0) {
                    game.alienPhase();
                    System.out.println("ya");
                    if (game.getGameOver() == true) {
                        game.gameOver();
                    }
                }
            }
        }
        System.out.println(game.getState());
        validate();
    }

    @Override
    public void update(Observable o, Object arg) {
        revalidate();
        repaint();
    }
}
