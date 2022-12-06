package Entity;

import GUI.MainFrame;

import java.awt.*;

public class House extends entityObject {
    int size = 40;

    public House(String img, int x, int y, MainFrame gameFrame) {
        super(img, x, y, gameFrame);
    }

    @Override
    public void Paint(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, 77, 54);
    }
}
