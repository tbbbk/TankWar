package GUI;

import Entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame extends JFrame {
    public static MainFrame instance = new MainFrame();
    int width = 800;
    int height = 800;
    public int state = 0;
    int cnt = 0;
    int enemyCnt = 0;
    // 双缓存图片
    Image Buffer = null;
    public ArrayList<Bullet> bulletArrayList = new ArrayList<Bullet>();
    public ArrayList<BadTank> enemyArrayList = new ArrayList<BadTank>();
    public ArrayList<Bullet> removeList = new ArrayList<Bullet>();
    public ArrayList<Wall> wallArrayList = new ArrayList<Wall>();
    public ArrayList<HardWall> hardWallArrayList = new ArrayList<HardWall>();
    public House house = new House("src/Imgs/House.png", 400, 400, this);
    public GoodTank player = new GoodTank("src/Imgs/1_U.png", 125, 510, this, "src/Imgs/1_U.png", "src/Imgs/1_D.png", "src/Imgs/1_L.png", "src/Imgs/1_R.png");

    private MainFrame() {
        // 基础设置
        setTitle("TankWar");
        setSize(800, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addKeyListener(new KeyBoardListener());
        for (int i = 1; i <= 14; i++) {
            wallArrayList.add(new Wall("src/Imgs/Wall.png", 50 + i * 32, 170, this));
        }
        for (int i = 1; i <= 7; i++){
            hardWallArrayList.add(new HardWall("src/Imgs/HardWall.png", 50 + i * 40, 340, this));
        }
        while (true) {
            if (cnt % 100 == 1 && enemyCnt <= 10) {
                Random r = new Random();
                int rx = r.nextInt(700);
                int ry = r.nextInt(590);
                enemyArrayList.add(new BadTank("src/Imgs/2_U.png", rx, ry, this,
                        "src/Imgs/2_U.png", "src/Imgs/2_D.png", "src/Imgs/2_L.png",
                        "src/Imgs/2_R.png"));
                enemyCnt++;
            }
            repaint();
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (Buffer == null) {
            Buffer = createImage(width, height);
        }
        Graphics gBuffer = Buffer.getGraphics();
        gBuffer.setColor(Color.black);
        gBuffer.fillRect(0, 0, width, height);
        gBuffer.setColor(Color.white);
        if (state == 0) {
            gBuffer.setFont(new Font("", Font.BOLD, 50));
            gBuffer.drawString("坦克大战", 300, 280);
            gBuffer.setFont(new Font("", Font.BOLD, 40));
            gBuffer.drawString("按空格开始游戏", 250, 370);
        } else if (state == 1) {
            player.Paint(gBuffer);
            for (Bullet b: bulletArrayList) {
                b.Paint(gBuffer);
            }
            bulletArrayList.removeAll(removeList);
            for (BadTank b: enemyArrayList) {
                b.Paint(gBuffer);
            }
            for (Wall w: wallArrayList) {
                w.Paint(gBuffer);
            }
            for (HardWall hw: hardWallArrayList) {
                hw.Paint(gBuffer);
            }
            house.Paint(gBuffer);
            cnt++;
        } else if (state == 2) {
            gBuffer.setFont(new Font("", Font.BOLD, 40));
            gBuffer.drawString("你死了", 250, 370);

        }
        g.drawImage(Buffer, 0, 0, null);
    }

    class KeyBoardListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_SPACE:
                    state = 1;
                default:
                    player.keyPressed(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        MainFrame tbk = MainFrame.instance;
    }
}
