package Entity;

import GUI.MainFrame;

import java.awt.*;
import java.util.Random;

public class BadTank extends Tank{
    int movCnt = 0;

    public BadTank(String img, int x, int y, MainFrame gameFrame, String upImg, String downImg, String leftImg, String rightImg) {
        super(img, x, y, gameFrame, upImg, downImg, leftImg, rightImg);
        this.speed = 3;
    }

    public int RandomDirection() {
        Random r = new Random();
        int n = r.nextInt(4);
        return n + 1;
    }

    public void go() {
        attack();
        move(Direction);
        if (movCnt == 20) {
            Direction = RandomDirection();
            movCnt = 0;
        } else {
            movCnt++;
        }
    }

    public void attack() {
        Point p = getHead();
        Random r = new Random();
        int rNum =r.nextInt(100);
        if (rNum < 2) {
            this.gameFrame.bulletArrayList.add(new BadBullet("src/Imgs/Bullet.png", p.x, p.y, this.gameFrame, this.Direction));
        }
    }

    @Override
    public void Paint(Graphics g) {
        g.drawImage(img, x, y, null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
