package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    public static MainFrame instance = new MainFrame();
    JLabel points = new JLabel("得分：" + 100);

    private MainFrame() {
        // 基础设置
        this.setSize(300, 400);
        this.setTitle("坦克大战");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 获取容器
        JPanel upPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        upPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new GridLayout(3, 3, 0, 0));
        points.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("C:\\Users\\TBK\\Desktop\\test\\222.jpg");
        icon.setImage(icon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        points.setIcon(icon);
        points.setHorizontalTextPosition(JLabel.RIGHT);
        points.setVerticalTextPosition(JLabel.CENTER);
        upPanel.add(points, BorderLayout.WEST);
        for (int i = 1; i <= 9; i++) {
            JLabel tmp = new JLabel();
            ImageIcon con = new ImageIcon("C:\\Users\\TBK\\Desktop\\test\\222.jpg");
            con.setImage(con.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
            tmp.setIcon(con);
            centerPanel.add(tmp);
        }
        this.setLayout(new BorderLayout());
        this.add(upPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        // 显示
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame tbk = MainFrame.instance;
    }
}
