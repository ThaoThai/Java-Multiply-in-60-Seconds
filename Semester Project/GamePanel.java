	/*
	Programmer:	Thao Thai
   	Date:		12/8/2014
	Program:	Multiply in 60 seconds game
	*/



//package to import
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.awt.Font;
import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.util.*;


// set private data
 public class GamePanel extends JPanel{
        private Timer countDown;
        private BufferedImage prest, background;
        private JButton one, two, restart, three;
        private int X = 450,  Y = 300, VelX = 0, VelY = 0,
        yPos = 0, numberX, numberY, first,second, answer, f1, f2,r1,r2,r3,score;
        public int count=30;


//-----------------------------------------------------------------------


public GamePanel() {
	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));  //JPanel will habe Boxlayout, set vertically
	setSize(1024,468);
	setVisible(true);
	//////////////////////////////
	drawGift();
    calculate();
	runGame();
	ActionListener timer = new ActionListener() { //timer countDown
		public void actionPerformed(ActionEvent time) {
			if (count > 0) {
				count--;
				repaint();
				}
			else {
				countDown.stop();}
		}
	};
	countDown = new Timer(1000,timer);
	countDown.start();
	gameRestart();

}
//-----------------------------------------------------------------------
public void gameRestart() {   //Restart the game once "Try Again" button is clicked, restart the time, repaint, set score = 0, set the Gift animate visible to True.
	  ActionListener again = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (count == 0){
					count = 30;
					countDown.start();
					calculate();
					runGame();
					repaint();
					score = 0;
		 restart.setVisible(false);
		 two.setVisible(true);
		 one.setVisible(true);
		 three.setVisible(true);}
				}
			};
 restart.addActionListener(again);
}

//-----------------------------------------------------------------------



public void runGame()   //run the actionListener for each ANSWER given
						// every time the button is clicked, new answer and question will popped up
						// if the selected gift is correct, score ++
{ActionListener run = new ActionListener() {
		public void actionPerformed (ActionEvent e){
			String temp = "" + answer;
			if (one.getText().equals(temp))
				{score++;}
				calculate();
		}
	};
one.addActionListener(run);
ActionListener run2 = new ActionListener() {
		public void actionPerformed (ActionEvent e){
			String temp = "" + answer;
			if (two.getText().equals(temp))
				{score++;}
				calculate();
		}
	};
two.addActionListener(run2);

ActionListener run3 = new ActionListener() {
		public void actionPerformed (ActionEvent e){
			String temp = "" + answer;
			if (three.getText().equals(temp))
				{score++;}
				calculate();
		}
	};
three.addActionListener(run3);
}
//-----------------------------------------------------------------------
public void calculate() {  // generate random questions, update the JPanel, set up answers
	if (count > 0 ) {
	first = myRandom();
	second = myRandom();
	answer = first * second;
	f1 = answer + fRandom();
	f2 = answer - fRandom();
	if (f1 < 0)
		{f1 *= -1;}
	else if (f2 < 0 ){
		 f2 *= -1;}
	int[] jj = {answer, f1, f2};
	Shuffle(jj);
	r1 = jj[0];
	r2 = jj[1];
	r3 = jj[2];
	updateJPanelText();
	repaint();}
	}
//-----------------------------------------------------------------------
public void drawGift()  // draw Try Again button, Gift Box images (3) total
{	  restart = new JButton("Try Again");
	  restart.setFont(new Font("", Font.BOLD, 20));
	  restart.setForeground(Color.BLACK);
	  restart.setIcon(new ImageIcon("bell.png"));
	  restart.setBackground(Color.WHITE);
	  add(restart);
	  restart.setVisible(false);
	  add(Box.createRigidArea(new Dimension(130,0)));

	  ///////////////////////////////////////////////

	  one = new JButton("");
	  one.setIcon(new ImageIcon("bag.png"));
	  one.setVerticalTextPosition(JButton.TOP);
	  one.setHorizontalTextPosition(JButton.CENTER);
	  one.setFont(new Font("Cooper Black", Font.BOLD, 70));
	  one.setForeground(Color.GREEN);
	  one.setContentAreaFilled(false);
	  one.setRolloverIcon(new ImageIcon("gift.png"));
	  one.setRolloverEnabled(true);
	  one.setBorder(BorderFactory.createEmptyBorder());

	  ///////////////////////////////////////////////
	  two = new JButton("");
	  two.setIcon(new ImageIcon("bag.png"));
	  two.setVerticalTextPosition(JButton.TOP);
	  two.setFont(new Font("Cooper Black", Font.BOLD, 70));
	  two.setForeground(Color.GREEN);
	  two.setHorizontalTextPosition(JButton.CENTER);
	  two.setContentAreaFilled(false);
	  two.setBorder(BorderFactory.createEmptyBorder());
	  two.setRolloverIcon(new ImageIcon("gift.png"));
	  two.setRolloverEnabled(true);

	  ////////////////////////////////////////////////
	  three = new JButton("");
	  three.setIcon(new ImageIcon("bag.png"));
	  three.setVerticalTextPosition(JButton.TOP);
	  three.setHorizontalTextPosition(JButton.CENTER);
	  three.setFont(new Font("Cooper Black", Font.BOLD, 70));
	  three.setContentAreaFilled(false);
	  three.setBorder(BorderFactory.createEmptyBorder());
	  three.setRolloverIcon(new ImageIcon("gift.png"));
	  three.setRolloverEnabled(true);
	  three.setForeground(Color.GREEN);
	  add(three);

	  //////////////////////////////////////////////
	  add(one);
	  add(Box.createRigidArea(new Dimension(146,0)));
	  add(two);
	  add(Box.createRigidArea(new Dimension(146,0)));
	  add(three);
}
//-----------------------------------------------------------------------
public void updateJPanelText()  //update the JButton text everything the new random number is generated
{
	one.setText("" + r1);
	two.setText("" + r2);
	three.setText("" + r3);
}
//-----------------------------------------------------------------------

public int myRandom() {  //generate the random number for QUESTION problem
	int[] n = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	Random random = new Random();
	return n[random.nextInt(n.length)];
}
//----------------------------------------------------------------------

public int fRandom() {  //generate the random differences for the ANSWER portion
	Random random = new Random();
	int diff = random.nextInt(5) + 4;
	return diff;
}
//----------------------------------------------------------------------



private void Shuffle(int[] array)   //shuffle up the answer between the three Gift animate, so the correct answer will be placed in different position every time.
//using Fisher-Yate algorithm
{
    int ind, temp;
    Random r = new Random();
    for (int x = array.length - 1; x > 0; x--)
    {
        ind = r.nextInt(x + 1);
        temp = array[ind];
        array[ind] = array[x];
        array[x] = temp;
    }
}


///----------------------------------------------------------------------

public void paintComponent(Graphics g) {   //paint the background, QUESTION, Left score, left timer, ending "Time's up!" and "Your score is "
	   //fetch all the images using readFile
	 try {
	   	  background = ImageIO.read(new File("bg.jpg"));
	   		}
	   	catch (IOException ex) {
	   		    ex.printStackTrace();
        }
        super.paintComponent(g);

		//----------///------------------///-------------------///

        g.drawImage(background, 0, 0, this); //draw background image
        	g.setFont(new Font("Cooper Black", Font.BOLD,30));
			g.setColor(Color.RED);
		if (count == 0)
		{g.setFont(new Font("Cooper Black", Font.BOLD,60));
		 g.setColor(Color.RED);
		 g.drawString(" TIME'S UP! ", 350,200);
		 int temp = score;
		 g.setColor(Color.YELLOW);
		 g.drawString("Your score : " + temp ,300,270);
		 two.setVisible(false);
		 one.setVisible(false);
		 restart.setVisible(true);
		 three.setVisible(false);
	 	}
		 g.setFont(new Font("Cooper Black", Font.BOLD,30));
		 g.setColor(Color.RED);
       g.drawString("Score : " + score, 30,50);

	   g.drawString("Time : " + count, 30,90);
       		g.setFont(new Font("Cooper Black", Font.BOLD,100));
			g.setColor(Color.WHITE);
		g.drawString(first+ " x  " + second + " = " , 300,86); // question

	}

}

