package iu.grafico;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logicaEstados.*;
import javax.imageio.*;
import javax.swing.border.*;
import logicaJogo.crewMembers.CM_ScienceOfficer;

public class GameOptionsPanel extends JPanel implements Observer {

    private final ObservableGame game;
    private int healthTracker, hullTracker, ipTracker;
    private JProgressBar healthBar, hullBar;
    private JPanel statsP, crewPhaseP, moveP, restingPhaseP;
    private JLabel healthLabel, hullLabel, ipLabel, ipLTracker;
    private GridBagConstraints c;
    private JButton b_Move, b_Attack, b_HealHealth, b_FixHull, b_SettingTrap, b_DetonatePD, b_SealRoom, move_cm1, move_cm2;
    private JButton b_heal, b_buildODetonator, b_addToMovement, b_buildPDesperser, b_sealToken, b_addAttackDie, b_addOneResultAttack, b_fix;
    private Dimension d = new Dimension(300, 30);
    private final Font font = new Font("Arial black", Font.PLAIN, 14);
    boolean moved = false;

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
            if (moved == true) {
                crewPhaseP.setVisible(true);
            }
            add(crewPhaseP, BorderLayout.CENTER);
            remove(restingPhaseP);
        } else if (game.getState() instanceof AwaitRestPhase) {
            remove(crewPhaseP);
            add(restingPhaseP, BorderLayout.CENTER);
        } else {
        }
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        //Stats Panel ( HP, Hull, Inspiration Ponints)
        add(statsP, BorderLayout.SOUTH);

    }

    private void setupComponents() {
        //Stats CENTER.SOUTH
        setupSouth();

        //CrewPhase CENTER.CENTER
        setupCenterCrewPhase();
        setupCenterRestPhase();

        //Move CENTER.CENTER
        setupCenterMove();

        setupButtonsActionL();

    }

    private void setupSouth() {
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
        d = new Dimension(300, 30);
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
        c.fill = GridBagConstraints.HORIZONTAL;
        moveP.add(move_cm1, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 100, 200);
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
        c.insets = new Insets(10, 0, 100, 200);
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
                moved = false;
                add(moveP, BorderLayout.CENTER);
                moveP.setVisible(true);
            }
        });
        move_cm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                moveP.setVisible(false);
                moved = true;
//                crewPhaseP.setVisible(true);
                game.moveCM(1);
            }
        });
        move_cm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                moveP.setVisible(false);
                moved = true;
//                crewPhaseP.setVisible(true);
                game.moveCM(2);
            }
        });

        b_Attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getCrewMemberFirst() instanceof CM_ScienceOfficer || game.getCrewMemberSecond() instanceof CM_ScienceOfficer) {
                    remove(crewPhaseP);
                    crewPhaseP.setVisible(false);
                    game.getGame().attack();
                }
                if (game.getCM1_CurrentRoom().getAliens() != 0 || game.getCM2_CurrentRoom().getAliens() != 0) {
                    remove(crewPhaseP);
                    crewPhaseP.setVisible(false);
                    game.getGame().attack();
                }
            }
        });

        b_FixHull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(crewPhaseP);
                crewPhaseP.setVisible(false);

                if (game.getCrewMemberFirst().getNum() == 3 || game.getCrewMemberSecond().getNum() == 3) {
                    game.getGame().fixHull();
                }
                crewPhaseP.setVisible(true);
            }
        });

        b_HealHealth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(crewPhaseP);
                crewPhaseP.setVisible(false);

                if (game.getCrewMemberFirst().getNum() == 1 || game.getCrewMemberSecond().getNum() == 1) {
                    game.getGame().heal();
                }
                crewPhaseP.setVisible(true);
            }
        });
    }

    private void setupCenterRestPhase() {
        d = new Dimension(500, 30);
        restingPhaseP = new JPanel();
        restingPhaseP.setLayout(new GridBagLayout());
        restingPhaseP.setAlignmentX(Component.LEFT_ALIGNMENT);
        restingPhaseP.setOpaque(false);

        b_heal = new JButton("[1] Add one to Health (Double if Doctor)");
        b_heal.setFont(font);
        b_heal.setPreferredSize(d);
        b_heal.setContentAreaFilled(false);
        b_heal.setForeground(Color.ORANGE);

        b_fix = new JButton("[1] Repair one Hull (Double if Engineer)");
        b_fix.setFont(font);
        b_fix.setPreferredSize(d);
        b_fix.setContentAreaFilled(false);
        b_fix.setForeground(Color.ORANGE);

        b_buildODetonator = new JButton("[1] Build one Organic Detonator");
        b_buildODetonator.setFont(font);
        b_buildODetonator.setPreferredSize(d);
        b_buildODetonator.setContentAreaFilled(false);
        b_buildODetonator.setForeground(Color.ORANGE);

        b_addToMovement = new JButton("[2] Add one to Movement");
        b_addToMovement.setFont(font);
        b_addToMovement.setPreferredSize(d);
        b_addToMovement.setContentAreaFilled(false);
        b_addToMovement.setForeground(Color.ORANGE);

        b_buildPDesperser = new JButton("[4] Build one Particle Desperser");
        b_buildPDesperser.setFont(font);
        b_buildPDesperser.setPreferredSize(d);
        b_buildPDesperser.setContentAreaFilled(false);
        b_buildPDesperser.setForeground(Color.ORANGE);

        b_sealToken = new JButton("[5] Gain one Sealed Room Token");
        b_sealToken.setFont(font);
        b_sealToken.setPreferredSize(d);
        b_sealToken.setContentAreaFilled(false);
        b_sealToken.setForeground(Color.ORANGE);

        b_addAttackDie = new JButton("[5] Gain one extra Attack Die");
        b_addAttackDie.setFont(font);
        b_addAttackDie.setPreferredSize(d);
        b_addAttackDie.setContentAreaFilled(false);
        b_addAttackDie.setForeground(Color.ORANGE);

        b_addOneResultAttack = new JButton("[6] Add 1 to the result of an Attack Dice");
        b_addOneResultAttack.setFont(font);
        b_addOneResultAttack.setPreferredSize(d);
        b_addOneResultAttack.setContentAreaFilled(false);
        b_addOneResultAttack.setForeground(Color.ORANGE);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        restingPhaseP.add(b_heal, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        restingPhaseP.add(b_fix, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        restingPhaseP.add(b_buildODetonator, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        restingPhaseP.add(b_addToMovement, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        restingPhaseP.add(b_buildPDesperser, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 5;
        c.anchor = GridBagConstraints.WEST;
        restingPhaseP.add(b_sealToken, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 6;
        c.anchor = GridBagConstraints.WEST;
        restingPhaseP.add(b_addAttackDie, c);

        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 200);
        c.gridy = 7;
        c.anchor = GridBagConstraints.WEST;
        restingPhaseP.add(b_addOneResultAttack, c);
    }

}
