import javax.swing.JFrame;

import javafx.scene.paint.Color;

public class Frame_Class 
{
   public static void main(String[] args)
   {
	      JFrame obj = new JFrame();
		  Snake_Panel p1 = new Snake_Panel();
		  obj.setBounds(10,10,900,700);
		  obj.setResizable(false);
		  obj.setVisible(true);
		  obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  obj.setBackground(java.awt.Color.DARK_GRAY);
		  obj.add(p1);
   }
    
}
