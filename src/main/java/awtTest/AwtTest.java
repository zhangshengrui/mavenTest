package awtTest;
import javax.swing.*;
import java.awt.*;

public class AwtTest extends JFrame{

    public void CreateJFrame(){
        JFrame jf = new JFrame();
        Container container = jf.getContentPane();
        JLabel jl = new JLabel("有钱请你去吃小龙虾");
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jl);
        container.setBackground(Color.WHITE);
        jf.setVisible(true);
        jf.setSize(300,150);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new AwtTest().CreateJFrame();
    }
}
