package Entity;

import GUI.MainFrame;

import java.awt.*;
import java.util.ArrayList;

public class Bullet extends entityObject{
    int width = 10;
    int height = 10;
    int speed = 7;
    int Direction;

    public Bullet(String img, int x, int y, MainFrame gameFrame, int Direction) {
        super(img, x, y, gameFrame);
        this.Direction = Direction;
    }

    public void move(int direction) {
        switch (direction) {
            case 1:
                direction = 1;
                y -= speed;
                break;
            case 2:
                direction = 2;
                y += speed;
                break;
            case 3:
                direction = 3;
                x -= speed;
                break;
            case 4:
                direction = 4;
                x += speed;
                break;
        }
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public void hitEnemy() {
        ArrayList<BadTank> e = this.gameFrame.enemyArrayList;
        for (BadTank b: e) {
            if (b.getRec().intersects(this.getRec())) {
                this.gameFrame.enemyArrayList.remove(b);
                this.gameFrame.removeList.add(this);
                break;
            }
        }
    }

    public boolean moveOut(int x, int y) {
        if (x < 0 || x + width > this.gameFrame.getWidth() || y < 0 || y + height > this.gameFrame.getHeight()) {
            return true;
        }
        return false;
    }

    public void hitWall() {
        ArrayList<Wall> walls = this.gameFrame.wallArrayList;
        ArrayList<HardWall> hardWalls = this.gameFrame.hardWallArrayList;
        if (moveOut(x, y)) {
            this.gameFrame.removeList.add(this);
        }
        for (Wall w: walls) {
            if (w.getRec().intersects(this.getRec())) {
                walls.remove(w);
                this.gameFrame.removeList.add(this);
                break;
            }
        }
        for (HardWall hw: hardWalls) {
            if (hw.getRec().intersects(this.getRec())) {
                this.gameFrame.removeList.add(this);
                break;
            }
        }
    }

    @Override
    public void Paint(Graphics g) {
        g.drawImage(img, x, y, null);
        move(Direction);
        hitEnemy();
        hitWall();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
