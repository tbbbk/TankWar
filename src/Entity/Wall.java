package Entity;

import GUI.MainFrame;

import java.awt.*;

public class Wall extends entityObject {
    int size = 33;

    public Wall(String img, int x, int y, MainFrame gameFrame) {
        super(img, x, y, gameFrame);
    }

    @Override
    public void Paint(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, size, size);
    }
}
