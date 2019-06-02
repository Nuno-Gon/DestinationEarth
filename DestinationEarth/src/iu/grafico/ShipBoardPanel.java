package iu.grafico;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import javax.imageio.*;
import logicaEstados.*;

public class ShipBoardPanel extends JPanel implements Observer {

    ObservableGame game;
    //Image
    private final String shipURL = "imagens/shipOnly.png";
    private Image shipImg;
    private final String imageFiles[] = {"imagens/base.png", "", ""};
    private final Image[] imagem = new Image[imageFiles.length];
    boolean loaded = false;
    boolean paintCM1 = false, paintCM2 = false, paintAliens = false;
    //Buttons
    JButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    ShipBoardPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);
        setupLayout();
        setupComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
//        shipImg = Toolkit.getDefaultToolkit().createImage(Resources.getResourceFile(shipURL));
//        shipImg = shipImg.getScaledInstance(200, 240, 0);
//        g.drawImage(shipImg, 0, 0, this);
        try {
            shipImg = ImageIO.read(Resources.getResourceFile(shipURL));
            //shipImg = shipImg.getScaledInstance(500, 540, 0);
        } catch (IOException ex) {

        }
        g.drawImage(shipImg, -50, 0, this);
        if ((game.getState() instanceof AwaitThirdTokenPlacementCM1) == false && (game.getState() instanceof AwaitThirdTokenPlacementCM2) == true) {
            paintCM1 = true;
        } else if ((game.getState() instanceof AwaitThirdTokenPlacementCM1) == false && (game.getState() instanceof AwaitThirdTokenPlacementCM2) == false) {
            paintCM2 = true;
        }

        //paint crew member one
        if (paintCM1 == true) {
            g2d.setColor(Color.RED);
            switch (game.getLocationCM1()) {
                case 1:
                    g2d.fillRect(180, 100, 30, 30);
                    break;
                case 2:
                    g2d.fillRect(250, 400, 30, 30);
                    break;
                case 3:
                    g2d.fillRect(70, 170, 30, 30);
                    break;
                case 4:
                    g2d.fillRect(310, 250, 30, 30);
                    break;
                case 5:
                    g2d.fillRect(170, 260, 30, 30);
                    break;
                case 6:
                    g2d.fillRect(180, 600, 30, 30);
                    break;
                case 7:
                    g2d.fillRect(315, 420, 30, 30);
                    break;
                case 8:
                    g2d.fillRect(235, 190, 30, 30);
                    break;
                case 9:
                    g2d.fillRect(65, 370, 30, 30);
                    break;
                case 10:
                    g2d.fillRect(160, 385, 30, 30);
                    break;
                case 11:
                    g2d.fillRect(310, 315, 30, 30);
                    break;
                case 12:
                    g2d.fillRect(65, 430, 30, 30);
                    break;
                default:
                    System.out.println("didn't paint cm1");
                    break;
            }
        }
        //paint crew member two
        if (paintCM2 == true) {
            g2d.setColor(Color.BLUE);
            switch (game.getLocationCM2()) {
                case 1:
                    g2d.fillRect(270, 100, 30, 30);
                    break;
                case 2:
                    g2d.fillRect(250, 450, 30, 30);
                    break;
                case 3:
                    g2d.fillRect(110, 170, 30, 30);
                    break;
                case 4:
                    g2d.fillRect(360, 250, 30, 30);
                    break;
                case 5:
                    g2d.fillRect(170, 300, 30, 30);
                    break;
                case 6:
                    g2d.fillRect(235, 600, 30, 30);
                    break;
                case 7:
                    g2d.fillRect(355, 420, 30, 30);
                    break;
                case 8:
                    g2d.fillRect(265, 190, 30, 30);
                    break;
                case 9:
                    g2d.fillRect(105, 370, 30, 30);
                    break;
                case 10:
                    g2d.fillRect(190, 385, 30, 30);
                    break;
                case 11:
                    g2d.fillRect(310, 355, 30, 30);
                    break;
                case 12:
                    g2d.fillRect(105, 430, 30, 30);
                    break;
                default:
                    System.out.println("didn't paint cm2");
                    break;
            }
        }
        if (!(game.getState() instanceof AwaitAlienSpawn)) {

            for (Room roomList : game.getGame().getGameData().getShipRoomList()) {

                int numAliens = roomList.getAliens();
                if (numAliens > 0) {

                    g2d.setColor(Color.GREEN);
                    g2d.setFont(new Font("Arial Black", Font.PLAIN, 12));
                    switch (roomList.getNum()) {
                        case 1:
                            g2d.fillRect(150, 70, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 160, 90);
                            break;
                        case 2:
                            g2d.fillRect(250, 500, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 260, 520);
                            break;
                        case 3:
                            g2d.fillRect(85, 240, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 95, 260);
                            break;
                        case 4:
                            g2d.fillRect(330, 190, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 340, 210);
                            break;
                        case 5:
                            g2d.fillRect(170, 190, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 180, 210);
                            break;
                        case 6:
                            g2d.fillRect(210, 565, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 220, 585);
                            break;
                        case 7:
                            g2d.fillRect(305, 465, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 315, 485);
                            break;
                        case 8:
                            g2d.fillRect(245, 290, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 255, 310);
                            break;
                        case 9:
                            g2d.fillRect(80, 305, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 90, 325);
                            break;
                        case 10:
                            g2d.fillRect(170, 480, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 180, 500);
                            break;
                        case 11:
                            g2d.fillRect(360, 335, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 370, 355);
                            break;
                        case 12:
                            g2d.fillRect(95, 470, 30, 30);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("" + numAliens, 105, 490);
                            break;
                        default:
                            System.out.println("didn't paint aliens");
                            break;
                    }
                }
            }
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

    @SuppressWarnings("Convert2Lambda")
    private void setupComponents() {
        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(1);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(1);
                }
                game.getGame().getGameData().setTurn(game.getGame().getGameData().getTurn() + 1);
            }
        });
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(2);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(2);
                }
            }
        });
        r3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(3);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(3);
                }
            }
        });
        r4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(4);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(4);
                }
            }
        });
        r5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(5);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(5);
                }
            }
        });
        r6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(6);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(6);
                }
            }
        });
        r7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(7);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(7);
                }
            }
        });
        r8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(8);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(8);
                }
            }
        });
        r9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(9);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(9);
                }
            }
        });
        r10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(10);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(10);
                }
            }
        });
        r11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(11);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(11);
                }
            }
        });
        r12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
                    game.thirdTokenCM1(12);
                } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
                    game.thirdTokenCM2(12);
                }
            }
        });
    }

    private void setupLayout() {
        GridBagConstraints c = new GridBagConstraints();
        Dimension d = new Dimension();
        setLayout(new GridBagLayout());
        setOpaque(false);

        r1 = new JButton();
        c.insets = new Insets(-50, 0, 0, 0);
        r1.setBorder(BorderFactory.createEmptyBorder());
        r1.setContentAreaFilled(false);
        d.height = 120;
        d.width = 100;
        r1.setPreferredSize(d);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(r1, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r2 = new JButton();
        r2.setBorder(BorderFactory.createEmptyBorder());
        r2.setContentAreaFilled(false);
        d.height = 160;
        d.width = 85;
        r2.setPreferredSize(d);
        c.gridx = 2;
        c.gridy = 3;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        add(r2, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r3 = new JButton();
        r3.setBorder(BorderFactory.createEmptyBorder());
        r3.setContentAreaFilled(false);
        d.height = 130;
        d.width = 85;
        r3.setPreferredSize(d);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
//        c.fill = GridBagConstraints.NONE;
        add(r3, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r4 = new JButton();
        r4.setBorder(BorderFactory.createEmptyBorder());
        r4.setContentAreaFilled(false);
        d.height = 130;
        d.width = 85;
        r4.setPreferredSize(d);
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
//        c.fill = GridBagConstraints.NONE;
        add(r4, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r5 = new JButton();
        r5.setBorder(BorderFactory.createEmptyBorder());
        r5.setContentAreaFilled(false);
        d.height = 200;
        d.width = 85;
        r5.setPreferredSize(d);
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        add(r5, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r6 = new JButton();
        r6.setBorder(BorderFactory.createEmptyBorder());
        r6.setContentAreaFilled(false);
        d.height = 100;
        d.width = 100;
        r6.setPreferredSize(d);
        c.gridx = 1;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(r6, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r7 = new JButton();
        r7.setBorder(BorderFactory.createEmptyBorder());
        r7.setContentAreaFilled(false);
        d.height = 120;
        d.width = 85;
        r7.setPreferredSize(d);
        c.gridx = 3;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        add(r7, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r8 = new JButton();
        r8.setBorder(BorderFactory.createEmptyBorder());
        r8.setContentAreaFilled(false);
        d.height = 200;
        d.width = 85;
        r8.setPreferredSize(d);
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.SOUTH;
        add(r8, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r9 = new JButton();
        r9.setBorder(BorderFactory.createEmptyBorder());
        r9.setContentAreaFilled(false);
        d.height = 120;
        d.width = 85;
        r9.setPreferredSize(d);
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.SOUTH;
        add(r9, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r10 = new JButton();
        r10.setBorder(BorderFactory.createEmptyBorder());
        r10.setContentAreaFilled(false);
        d.height = 160;
        d.width = 85;
        r10.setPreferredSize(d);
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        add(r10, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r11 = new JButton();
        r11.setBorder(BorderFactory.createEmptyBorder());
        r11.setContentAreaFilled(false);
        d.height = 120;
        d.width = 85;
        r11.setPreferredSize(d);
        c.gridx = 3;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.SOUTH;
        add(r11, c);

        c = new GridBagConstraints();
        d = new Dimension();

        r12 = new JButton();
        r12.setBorder(BorderFactory.createEmptyBorder());
        r12.setContentAreaFilled(false);
        d.height = 120;
        d.width = 85;
        r12.setPreferredSize(d);
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        add(r12, c);

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
