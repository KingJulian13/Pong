package Pong;

import java.awt.*;

/**
 * Created by Julian and Lukas on 21.10.2014.
 */

public class Ball {

    private int bottom;
    private int diameter = 30;
    private int fieldY;
    private int fieldX;
    private double xPos = 500;
    private double yPos = 100;

    private boolean leftHit = false;
    private boolean rightHit = false;
    private double multiplikator;

    private Room room;
    private Sounds sounds = new Sounds();

    private double deltaX = 1.5;
    private double deltaY = 2;

    public Ball(Room r) {
        room = r;
        sounds.playFail();
        sounds.playWallHit();
        sounds.playShieldHit();
    }

    public void move(int leftWidth, int leftHeight, int leftX, int leftY, int rightWidth, int rightHeight, int rightX, int rightY, Dimension d) {

        fieldX = (int) d.getWidth();
        fieldY = (int) d.getHeight();

        bottom = (int) d.getHeight(); //Untere Kante

        double ballUpside = yPos;
        double ballFloor = yPos + diameter;
        double ballRight = xPos + diameter;
        double ballLeft = xPos;

        double leftBarX = leftWidth + leftX;
        double leftBarYupside = leftY;
        double leftBarYfloor = leftY + leftHeight;

        double rightBarX = rightX;
        double rightBarYupside = rightY;
        double rightBarYFloor = rightY + rightHeight;

        if ((ballLeft + deltaX) < leftBarX - 10) {
            leftHit = true;
        }
        if ((ballRight + deltaX) > rightBarX + 10) {
            rightHit = true;
        }

        if ((ballUpside + deltaY) < 0) {
            deltaY = -deltaY;
            sounds.playWallHit();
        }

        if ((ballFloor + deltaY) > bottom) {
            deltaY = -deltaY;
            sounds.playWallHit();
        }

        if (((ballRight + deltaX) > rightBarX) && ((ballFloor + deltaY) > rightBarYupside) && ((ballUpside + deltaY) < rightBarYFloor)) {
            if (!rightHit) {
                deltaX = -deltaX;
                sounds.playShieldHit();
                room.addShieldHeight(-3);
            }
        }

        if (((ballLeft + deltaX) < leftBarX) && ((ballFloor + deltaY) > leftBarYupside) && ((ballUpside + deltaY) < leftBarYfloor)) {
            if (!leftHit) {
                deltaX = -deltaX;
                sounds.playShieldHit();
                room.addShieldHeight(-3);
            }
        }


        if (((ballFloor + deltaY) > rightBarYupside) && ((ballRight + deltaX) > rightBarX) && ((ballLeft + deltaX) < (rightBarX + rightWidth)) && (ballUpside < rightBarYFloor)) {
            deltaY = -deltaY;
            sounds.playShieldHit();
        }

        if (((ballFloor + deltaY) > leftBarYupside) && ((ballRight + deltaX) > (leftX) && ((ballLeft + deltaX) < (leftBarX))) && (ballUpside < leftBarYfloor)) {
            deltaY = -deltaY;
            sounds.playShieldHit();
        }


        if (((ballUpside + deltaY) < rightBarYFloor) && ((ballRight + deltaX) > rightBarX) && ((ballLeft + deltaX) < (rightBarX + rightWidth)) && (ballFloor > rightBarYupside)) {
            deltaY = -deltaY;
            sounds.playShieldHit();
        }

        if (((ballUpside + deltaY) < leftBarYfloor) && ((ballRight + deltaX) > (leftX) && ((ballLeft + deltaX) < (leftBarX))) && (ballFloor > rightBarYupside)) {
            deltaY = -deltaY;
            sounds.playShieldHit();
        }

        xPos = xPos + deltaX;
        yPos = yPos + deltaY;
    }

    public void reset() {
        xPos = (fieldX - diameter) / 2;
        yPos = (fieldY - diameter) / 2;
        leftHit = false;
        rightHit = false;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getX() {
        return (int) xPos;
    }

    public int getY() {
        return (int) yPos;
    }
}