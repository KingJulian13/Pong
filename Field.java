package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Julian on 15.10.2014.
 */
public class Field extends JFrame implements ActionListener {

    private Room room = new Room();
    private boolean isRunning = false;
    private Scoreboard scoreboard = new Scoreboard();
    private Timer timer = new Timer(5, this);
    private Thread t;

    private boolean[] keys = {false, false, false, false};  //W - S - Up - Down

    public Field(int speed) {

        timer.setDelay(8 - speed);

        JPanel fillerTop = new JPanel();
        JPanel fillerBottom = new JPanel();
        fillerTop.setOpaque(false);
        fillerBottom.setOpaque(false);

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                room.moveBall();
                room.moveShield(keys[0], keys[1], keys[2], keys[3]);
            }
        });

        setTitle("Pong - It's not a bug, it's a feature!");
        getContentPane().setBackground(Color.GRAY);
        getContentPane().add(fillerTop, BorderLayout.PAGE_START);
        getContentPane().add(fillerBottom, BorderLayout.PAGE_END);
        getContentPane().add(room);
        fillerTop.setLayout(new GridBagLayout());
        fillerTop.add(scoreboard);


        setMinimumSize(new Dimension(1000, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();                         //?
        room.init();                    //?
        setVisible(true);
        room.createBall();


        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent keyEvent) {
                //Nothing here!
            }

            public synchronized void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
                    keys[0] = true;
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
                    keys[1] = true;
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                    keys[2] = true;
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                    keys[3] = true;
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!isRunning) {
                        setResizable(false);
                        isRunning = true;
                        room.setBallvis(true);
                        timer.start();
                    } else {
                        isRunning = false;
                    }
                }
            }

            public synchronized void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
                    keys[0] = false;
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
                    keys[1] = false;
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                    keys[2] = false;
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                    keys[3] = false;
                }
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                room.init();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isRunning) {
                    setResizable(false);
                    isRunning = true;
                    room.setBallvis(true);
                    timer.start();
                } else {
                    isRunning = false;
                }

                if (e.getClickCount() == 2) {
                    isRunning = false;
                    timer.stop();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                    }
                    room.reset();
                }

                if (e.getClickCount() == 3) {
                    isRunning = false;
                    timer.stop();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {}
                    room.reset();
                    scoreboard.resetScore();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (isRunning) {
            updateShields();
        } else {
            timer.stop();
        }
    }


    public void updateShields() {
        t.run();
        if (room.win()) {
            timer.stop();
            isRunning = false;

            if (room.getWinner()) {
                scoreboard.addPoints(1, 0);
            } else {
                scoreboard.addPoints(0, 1);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            room.reset();
        }
    }
}


