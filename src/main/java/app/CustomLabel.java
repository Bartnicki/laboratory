package app;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    public CustomLabel(String txt){

        super(txt);
        setFont(new Font("Dialog", Font.BOLD | Font.PLAIN, 12));

    }

    public void paintComponent(Graphics g){
        g.setColor(Color.RED);
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        g.drawRoundRect(0, 0, w, h, 6, 6);

    }

}
