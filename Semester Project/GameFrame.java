	/*
	Programmer:	Thao Thai
   	Date:		12/8/2014
	Program:	Multiply in 60 seconds game
	*/



//package to import
import java.awt.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.*;

   public class GameFrame {
	   private int X = 1024, Y =468;
	   private JFrame frame =  new JFrame("Multiply in 30 Seconds");;
		JPanel layout = new JPanel();
		GamePanel game;
		GameMenu menu = new GameMenu();
		JButton button = new JButton("START");  //create start button
		CardLayout cd = new CardLayout();  //create cardlayout to show different JPanel children

//---------------------------------------------------------

  	 	public GameFrame(){
			layout.setLayout(cd);
			menu.add(button);
			layout.add(menu, "menu"); //add GameMenu panel to the JPanel Layout
			cd.show(layout,"menu");
			button.setFont(new Font("Georgia", Font.BOLD, 70)); //setup and edit the START button
			button.setForeground(Color.WHITE);
			button.setContentAreaFilled(false);
			button.setAlignmentX(layout.CENTER_ALIGNMENT);
			button.addActionListener(new ActionListener() { //add actionListener to the START button
				public void actionPerformed(ActionEvent e ) {
					game = new GamePanel();
					layout.add(game,"game"); //when START is clicked, make new instance of GamePanel, show it.
					cd.show(layout,"game");
				}
			});
			frame.setSize(X,Y);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.add(layout);
}
/////--------------////////-------------//////////---------------/////////////


    public static void main(String[] args) {
	   		 new GameFrame();

	}
}
