package Entity;

import GUI.MainFrame;

import java.awt.*;
import java.security.DigestInputStream;
import java.util.ArrayList;
import java.util.Random;

public abstract class Tank extends entityObject {
    public int width = 40;
    public int height = 40;
    public int speed;
    public int Direction;
    public String upImg;
    public String downImg;
    public String leftImg;
    public String rightImg;
    public boolean attackState = true;
    public int attackCd = 500;


    public Tank(String img, int x, int y, MainFrame gameFrame, String upImg, String downImg, String leftImg, String rightImg) {
        super(img, x, y, gameFrame);
        this.upImg = upImg;
        this.downImg = downImg;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
    }

    public void move(int direction) {
        switch (direction) {
            case 1:
                direction = 1;
                if (!hitWall(x, y - speed) && !moveOut(x, y - speed)) {
                    y -= speed;
                }
                setImg(upImg);
                break;
            case 2:
                direction = 2;
                if (!hitWall(x, y + speed) && !moveOut(x, y + speed)) {
                    y += speed;
                }
                setImg(downImg);
                break;
            case 3:
                direction = 3;
                if (!hitWall(x - speed, y) && !moveOut(x - speed, y)) {
                    x -= speed;
                }
                setImg(leftImg);
                break;
            case 4:
                direction = 4;
                if (!hitWall(x + speed, y) && !moveOut(x + speed, y)) {
                    x += speed;
                }
                setImg(rightImg);
                break;
        }
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public Point getHead() {
        switch (Direction) {
            case 1:
                return new Point(x + width / 3, y);
            case 2:
                return new Point(x + width / 3, y + height);
            case 3:
                return new Point(x, y + height / 3);
            case 4:
                return new Point(x + width, y + height / 3);
        }
        return null;
    }

    public boolean hitWall(int x, int y) {
        ArrayList<Wall> walls = this.gameFrame.wallArrayList;
        ArrayList<HardWall> hardWalls = this.gameFrame.hardWallArrayList;
        Rectangle next = new Rectangle(x, y, width, height);
        for (Wall wall: walls) {
            if (next.intersects(wall.getRec())) {
                return true;
            }
        }
        for (HardWall hardWall: hardWalls) {
            if (next.intersects(hardWall.getRec())) {
                return true;
            }
        }
        return false;
    }

    public boolean moveOut(int x, int y) {
        if (x < 0 || x + width > this.gameFrame.getWidth() || y < 0 || y + height > this.gameFrame.getHeight()) {
            return true;
        }
        return false;
    }

    public void attack() {
        if (attackState) {
            Point p = getHead();
            Bullet bullet = new Bullet("src/Imgs/Bullet.png", p.x, p.y, this.gameFrame, this.Direction);
            this.gameFrame.bulletArrayList.add(bullet);
            new AttackCD().start();
        }
    }

    class AttackCD extends Thread {
        @Override
        public void run() {
            attackState = false;
            try {
                Thread.sleep((attackCd));
            } catch (Exception e) {
                e.printStackTrace();
            }
            attackState = true;
        }
    }

    @Override
    public abstract void Paint(Graphics g);

    @Override
    public abstract Rectangle getRec();
}
