package Entity;

import GUI.MainFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class GoodTank extends Tank{
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    public GoodTank(String img, int x, int y, MainFrame gameFrame, String upImg, String downImg, String leftImg, String rightImg) {
        super(img, x, y, gameFrame, upImg, downImg, leftImg, rightImg);
        this.speed = 5;
        this.Direction = 1;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_SPACE:
                attack();
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void Paint(Graphics g) {
        g.drawImage(img, x, y, null);
        if (up) {
            Direction = 1;
            move(Direction);
        } else if (down) {
            Direction = 2;
            move(Direction);
        } else if (left) {
            Direction = 3;
            move(Direction);
        } else if (right) {
            Direction = 4;
            move(Direction);
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
