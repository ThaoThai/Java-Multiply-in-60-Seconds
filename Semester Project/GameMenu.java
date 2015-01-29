	/*
	Programmer:	Thao Thai
   	Date:		12/8/2014
	Program:	Multiply in 60 seconds game
	*/

//package to import
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Dimension;
import java.awt.event.*;

public class GameMenu extends JPanel {
	public JButton quit;
    private BufferedImage mbg;
	public GameMenu() {
		drawGift();
		runGame();
	}

	//----------------------------------------------------------

	public void drawGift() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));    //box Layout
		setSize(1024,800);
		quit = new JButton("QUIT"); //new button "Quit"
		quit.setFont(new Font("Georgia", Font.BOLD, 70));
		quit.setForeground(Color.WHITE);
		quit.setContentAreaFilled(false);
		quit.setAlignmentX(this.CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(50,190)));
		add(quit);
}

	//----------------------------------------------------------
	public void runGame()
	{
		quit.addActionListener(new ActionListener() { //if Quit is clicked, exit game
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);}
			});
		}


	//----------------------------------------------------------

	public void paintComponent(Graphics g) {  //background of Intro
		   //fetch all the images using readFile
		 try {
		   	  mbg = ImageIO.read(new File("mBackground.jpg"));
		   		}
		   	catch (IOException ex) {
		   		    ex.printStackTrace();
	        }
        super.paintComponent(g);
        g.drawImage(mbg,0,0,this);
	}
}

