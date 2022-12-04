package Entity;

import GUI.MainFrame;

import java.awt.*;
import java.util.ArrayList;

public class BadBullet extends Bullet {
    public BadBullet(String img, int x, int y, MainFrame gameFrame, int Direction) {
        super(img, x, y, gameFrame, Direction);
    }

    public void hitMe() {
        if (this.getRec().intersects(this.gameFrame.house.getRec())) {
            this.gameFrame.state = 2;
        }
        if (this.getRec().intersects(this.gameFrame.player.getRec())) {
            this.gameFrame.state = 2;
        }
    }

    public void Paint(Graphics g) {
        g.drawImage(img, x, y, null);
        move(Direction);
        hitMe();
        hitWall();
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
