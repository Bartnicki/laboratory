package app;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String txt){

        super(txt);
        setFont(new Font("Text", Font.ROMAN_BASELINE | Font.ITALIC, 12));

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        g.setColor(Color.RED);

        g.fillOval(w - 50, h/2 - 4, 8, 8);

    }

}
