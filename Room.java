package Pong;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Julian on 16.10.2014.
 */
public class Room extends JPanel {

    private static int leftWidth = 10;
    private static int leftHeight = 100;
    private static int leftX;
    private static int leftY;

    private static int rightWidth = 10;
    private static int rightHeight = 100;
    private static int rightX;
    private static int rightY;

    private boolean win = false;
    private boolean winner = false;  //true = links
    private boolean ballvis = false;

    private Sounds sounds = new Sounds();

    private int shieldSpeed = 3;

    private boolean leftIsOn = false;
    private boolean rightIsOn = false;

    private Ball b;

    public Room() {
        setBackground(new Color(122, 197, 205));
    }

    void init() {

        leftX = getWidth() / 8 - leftWidth / 2;
        leftY = getHeight() / 2 - leftHeight / 2;
        rightX = getWidth() * 7 / 8 - rightWidth / 2;
        rightY = getHeight() / 2 - rightHeight / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(leftX, leftY, leftWidth, leftHeight);
        g2d.fillRect(rightX, rightY, rightWidth, rightHeight);

        if (ballvis) {
            Graphics2D g2ball = (Graphics2D) g;
            g2ball.setColor(new Color(255, 185, 15));
            g2ball.fillOval(b.getX(), b.getY(), b.getDiameter(), b.getDiameter());
        }

    }

    public void moveShield(boolean w, boolean s, boolean up, boolean down) {
        if (up) {
            if (rightY - shieldSpeed >= 0) {
                rightY = rightY - shieldSpeed;
            } else {
                rightY = 0;
            }
        }
        if (down) {
            if (rightY + shieldSpeed + rightHeight <= this.getHeight()) {
                rightY = rightY + shieldSpeed;
            } else {
                rightY = this.getHeight() - rightHeight;
            }
        }
        if (w) {
            if (leftY - shieldSpeed >= 0) {
                leftY = leftY - shieldSpeed;
            } else {
                leftY = 0;
            }
        }
        if (s) {
            if (leftY + shieldSpeed + leftHeight <= this.getHeight()) {
                leftY = leftY + shieldSpeed;
            } else {
                leftY = this.getHeight() - leftHeight;
            }
        }
        repaint();
    }

    public void createBall() {
        b = new Ball(this);
        ballvis = false;
        repaint();
    }

    public void setBallvis(boolean a) {
        ballvis = a;
        repaint();
    }

    public void moveBall() {
        if (b.getX() < 10) {
            //ballvis = false;
            sounds.playFail();
            win = true;
            winner = false;
        } else if (b.getX() > this.getWidth()-10-b.getDiameter()) {
            //ballvis = false;
            sounds.playFail();
            win = true;
            winner = true;
        }
        b.move(leftWidth, leftHeight, leftX, leftY, rightWidth, rightHeight, rightX, rightY,this.getSize());
    }

    public boolean win() {
        if (win) {
            win = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean getWinner() {
        return winner;  //true = links
    }

    public void reset() {
        leftHeight = 100;
        rightHeight = 100;
        init();
        ballvis = false;
        b.reset();
        repaint();
    }

    public void addShieldHeight(int diff) {
        if (leftHeight > 40) {
            leftHeight = rightHeight + diff;
            rightHeight = rightHeight + diff;
            leftY = leftY - (diff / 2);
            rightY = rightY - (diff / 2);
        }
    }


}