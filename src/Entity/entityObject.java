package Entity;

import GUI.MainFrame;

import java.awt.*;

public abstract class entityObject {
    public int x;
    public int y;
    public Image img;
    public MainFrame gameFrame;

    public entityObject(String img, int x, int y, MainFrame gameFrame) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.gameFrame = gameFrame;
    }

    public abstract void Paint(Graphics g);

    public abstract Rectangle getRec();
}
