package fr.graphics;
import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{

    public Container contentPane ;

    public Fenetre(String titre , String IconAcces){
        super(titre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setSize(920,800);
        this.setVisible(true);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(IconAcces)));

        contentPane = getContentPane();
        setVisible(true);
    }

    public Fenetre(String titre){
        super(titre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setSize(920,800);
        this.setVisible(true);

        contentPane = getContentPane();
        setVisible(true);
    }

    public Fenetre(String titre,int width,int height){
        super(titre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setSize(width,height);
        this.setVisible(true);
    }
}
