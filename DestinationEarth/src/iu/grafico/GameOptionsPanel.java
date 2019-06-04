package iu.grafico;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logicaEstados.*;
import javax.imageio.*;
import javax.swing.border.*;

public class GameOptionsPanel extends JPanel implements Observer {

    private final ObservableGame game;
    private int healthTracker, hullTracker, ipTracker;
    private JProgressBar healthBar, hullBar;
    private JPanel statsP, crewPhaseP, moveP;
    private JLabel healthLabel, hullLabel, ipLabel, ipLTracker;
    private GridBagConstraints c;
    private JButton b_Move, b_Attack, b_HealHealth, b_FixHull, b_SettingTrap, b_DetonatePD, b_SealRoom, move_cm1, move_cm2;
    private final Dimension d = new Dimension(300, 30);
    private final Font font = new Font("Arial black", Font.PLAIN, 14);

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
        ipLabel.setText(" " + ipTracker);
        move_cm1.setText(game.getNameCM1());
        move_cm2.setText(game.getNameCM2());

        if (game.getState() instanceof AwaitCrewPhase) {
//            add(moveP, BorderLayout.SOUTH);
//            moveP.setVisible(false);

            add(crewPhaseP, BorderLayout.CENTER);
//            remove(moveP);

        } else if (game.getState() instanceof AwaitMove) {
//            remove(crewPhaseP);
//            remove(moveP);
        } else {
        }
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        //Stats Panel ( HP, Hull, Inspiration Ponints)
        add(statsP, BorderLayout.SOUTH);

    }

    private void setupComponents() {
        //Stats CENTER.NORTH
        setupNorth();

        //CrewPhase CENTER.CENTER
        setupCenterCrewPhase();

        //Move CENTER.CENTER
        setupCenterMove();

        setupButtonsActionL();

    }

    private void setupNorth() {
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

        hullLabel = new JLabel("Hull: ");
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
        c.gridy = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(healthLabel, c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 25;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(healthBar, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(hullLabel, c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 25;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(hullBar, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        ipLTracker.setText("Inspiration Points:");
        statsP.add(ipLTracker, c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        ipLabel.setText(" " + ipTracker);
        statsP.add(ipLabel, c);
    }

    private void setupCenterMove() {
        moveP = new JPanel();
        moveP.setLayout(new GridBagLayout());
        moveP.setOpaque(false);

        move_cm1 = new JButton();
        move_cm1.setFont(font);
        move_cm1.setPreferredSize(d);
        move_cm1.setContentAreaFilled(false);
        move_cm1.setForeground(Color.ORANGE);

        move_cm2 = new JButton();
        move_cm2.setFont(font);
        move_cm2.setPreferredSize(d);
        move_cm2.setContentAreaFilled(false);
        move_cm2.setForeground(Color.ORANGE);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 0;
        moveP.add(move_cm1, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 170, 200);
        c.gridy = 1;
        moveP.add(move_cm2, c);
    }

    private void setupCenterCrewPhase() {
        crewPhaseP = new JPanel();
        crewPhaseP.setLayout(new GridBagLayout());
        crewPhaseP.setOpaque(false);

        b_Move = new JButton("Move");
        b_Move.setFont(font);
        b_Move.setPreferredSize(d);
        b_Move.setContentAreaFilled(false);
        b_Move.setForeground(Color.ORANGE);

        b_Attack = new JButton("Attack");
        b_Attack.setFont(font);
        b_Attack.setPreferredSize(d);
        b_Attack.setContentAreaFilled(false);
        b_Attack.setForeground(Color.ORANGE);

        b_HealHealth = new JButton("Heal one Health [Doctor Only]");
        b_HealHealth.setFont(font);
        b_HealHealth.setPreferredSize(d);
        b_HealHealth.setContentAreaFilled(false);
        b_HealHealth.setForeground(Color.ORANGE);

        b_FixHull = new JButton("Fix one Hull [Engineer Only]");
        b_FixHull.setFont(font);
        b_FixHull.setPreferredSize(d);
        b_FixHull.setContentAreaFilled(false);
        b_FixHull.setForeground(Color.ORANGE);

        b_SettingTrap = new JButton("Setting Trap");
        b_SettingTrap.setFont(font);
        b_SettingTrap.setPreferredSize(d);
        b_SettingTrap.setContentAreaFilled(false);
        b_SettingTrap.setForeground(Color.ORANGE);

        b_DetonatePD = new JButton("Detonate Particle Disperser");
        b_DetonatePD.setFont(font);
        b_DetonatePD.setPreferredSize(d);
        b_DetonatePD.setContentAreaFilled(false);
        b_DetonatePD.setForeground(Color.ORANGE);

        b_SealRoom = new JButton("Seal Room");
        b_SealRoom.setFont(font);
        b_SealRoom.setPreferredSize(d);
        b_SealRoom.setContentAreaFilled(false);
        b_SealRoom.setForeground(Color.ORANGE);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 0;
        crewPhaseP.add(b_Move, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 1;
        crewPhaseP.add(b_Attack, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 2;
        crewPhaseP.add(b_HealHealth, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 3;
        crewPhaseP.add(b_FixHull, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 4;
        crewPhaseP.add(b_SettingTrap, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 5;
        crewPhaseP.add(b_DetonatePD, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 170, 200);
        c.gridy = 6;
        crewPhaseP.add(b_SealRoom, c);
    }

    @Override
    public void update(Observable o, Object arg
    ) {
        //setVisible(game.getState() instanceof AwaitBeginning);
        setVisible(true);
        revalidate();
        repaint();
    }

    @SuppressWarnings("Convert2Lambda")
    private void setupButtonsActionL() {
        b_Move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(crewPhaseP);
                crewPhaseP.setVisible(false);
                add(moveP, BorderLayout.SOUTH);
                moveP.setVisible(true);
            }
        });
        move_cm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                moveP.setVisible(false);
                crewPhaseP.setVisible(true);
                game.moveCM(1);
            }
        });
        move_cm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                moveP.setVisible(false);
                crewPhaseP.setVisible(true);
                game.moveCM(2);
            }
        });

        b_Attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                game.getGame().attack();
            }
        });
    }

}
