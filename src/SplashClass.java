import java.awt.*;
import javax.swing.*;

public class SplashClass extends JWindow{
    JLabel splashimg;
    
public SplashClass(){

setLayout(null);
splashimg=new JLabel(new ImageIcon("images/nibl_splash.png"));
add(splashimg);
splashimg.setBounds(0,0,600,363);
setVisible(true);
setSize(600,363);
setLocationRelativeTo(null);

}

public void ShowSplash(){

}
public static void main(String args[]) throws InterruptedException{
new SplashClass();

}

}
