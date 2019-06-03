package iu.grafico;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import logicaEstados.*;
import javax.imageio.*;
import javax.swing.border.*;

public class GameOptionsPanel extends JPanel implements Observer {

    private final ObservableGame game;
    private int healthTracker, hullTracker, ipTracker;
    private JProgressBar healthBar, hullBar;
    private JPanel statsP;
    private JLabel healthLabel, hullLabel = new JLabel("Hull: "), ipLabel, ipLTracker;
    private GridBagConstraints c;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    GameOptionsPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);

        setupComponents();
        setupLayout();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        healthTracker = game.getTHealth();
        hullTracker = game.getTHull();
        ipTracker = game.getTInspirationP();
        healthBar.setValue(healthTracker);
        hullBar.setValue(hullTracker);

        if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
        } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
        } else if (game.getState() instanceof AwaitCrewPhase) {
            ipLabel.setText(" " + ipTracker);
        } else {
        }
    }

    @Override
    public void update(Observable o, Object arg
    ) {
        //setVisible(game.getState() instanceof AwaitBeginning);
        setVisible(true);
        revalidate();
        repaint();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        //Stats Panel ( HP, Hull, Inspiration Ponints)
        add(statsP, BorderLayout.NORTH);
    }

    private void setupComponents() {
        //Stats CENTER.NORTH
        statsP = new JPanel();
        statsP.setLayout(new GridBagLayout());
        statsP.setOpaque(false);

        healthBar = new JProgressBar(0, 12);
        healthBar.setPreferredSize(new Dimension(350, 20));
        healthBar.setBackground(Color.red);
        healthBar.setForeground(Color.green);
        hullBar = new JProgressBar(0, 12);
        hullBar.setPreferredSize(new Dimension(350, 20));
        hullBar.setBackground(Color.red);
        hullBar.setForeground(Color.green);

        healthLabel = new JLabel("Health: ");
        healthLabel.setOpaque(false);
        healthLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
        healthLabel.setForeground(Color.LIGHT_GRAY);

        hullLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
        hullLabel.setForeground(Color.LIGHT_GRAY);
        hullLabel.setOpaque(false);

        ipLabel = new JLabel("Inspiration Points: ");
        ipLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
        ipLabel.setForeground(Color.ORANGE);
        ipLabel.setOpaque(false);

        ipLTracker = new JLabel("Inspiration Points: ");
        ipLTracker.setFont(new Font("Arial Black", Font.PLAIN, 16));
        ipLTracker.setForeground(Color.LIGHT_GRAY);
        ipLTracker.setOpaque(false);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(healthLabel, c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 25;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(healthBar, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(hullLabel, c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 25;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(hullBar, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        ipLabel.setText(" " + ipTracker);
        statsP.add(ipLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        ipLTracker.setText("Inspiration Points:");
        statsP.add(ipLTracker, c);
    }
}
