package app;


import javax.swing.*;
import java.awt.*;

public class Application extends JFrame{


    public static void main(String[] args) {


        Application apk = new Application();




    }

    public Application(){


        this.setSize(1000, 700);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);

        this.setLocation(xPos, yPos);
        this.setResizable(false);
        this.setTitle("Laboratory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GUI gui = new GUI();

        this.getContentPane().add(gui);

        this.setVisible(true);




    }

}
