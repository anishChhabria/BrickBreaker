import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import javafx.event.EventHandler;
//import javafx.scene.Node;

class BrickBreaker extends JPanel implements KeyListener,ActionListener,Runnable
{
                // movement keys..
                static boolean right = false;
                static boolean left = false;
                // ..............
                // variables declaration for ball.................................
                int ballx = 160;
                int bally = 218;
                // variables declaration for ball.................................
                // ===============================================================
                // variables declaration for bat..................................
                int batx = 160;
                int baty = 245;
                // variables declaration for bat..................................
                // ===============================================================
                // variables declaration for brick...............................
                int brickx = 50;
                int bricky = 50;

                int brickBreadth = 30;
                int brickHeight = 20;
                // variables declaration for brick...............................
                // ===============================================================
                // declaring ball, paddle,bricks
                Rectangle Ball = new Rectangle(ballx, bally, 5, 5);
                Rectangle Bat = new Rectangle(batx, baty, 40, 5);
                // Rectangle Brick;// = new Rectangle(brickx, bricky, 30, 10);
                Rectangle[] Brick = new Rectangle[20];

                //reverses......==>
                int movex = -1;
                int movey = -1;
                boolean ballFallDown = false;
                boolean bricksOver = false;
				int count = 0;
				static int c1=0;
				String status;
				String score="0";
				String s1="Score:-";
                BrickBreaker()
                 {

                }

                public static void main(String[] args)
                 {
                                  JFrame frame = new JFrame();
                                  BrickBreaker game = new BrickBreaker();
                                  frame.setSize(350, 350);//just the frame changes
                                  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                  JButton button = new JButton("Restart");
                                  JButton button1 = new JButton("Pause");
                                  JButton button2 = new JButton("Resume");
                                  frame.add(game);
                                  frame.add(button, BorderLayout.SOUTH);
                                  //pause
                                  /*button1.setBounds(50,50,400,50) ;
                                  frame.add(button1);*/


                                  frame.setLocationRelativeTo(null);
                                  frame.setResizable(false);
                                  frame.setVisible(true);
                                  button.addActionListener(game);

                                  game.addKeyListener(game);
                                  game.setFocusable(true);
                                  Thread t = new Thread(game);
                                  t.start();

                }

                // declaring ball, paddle,bricks

                public void paint(Graphics g)
                 {
                                  g.setColor(Color.yellow);
                                  g.fillRect(0, 0, 350, 350);//screen colour
                                  g.setColor(Color.BLACK);
                                  g.fillOval(Ball.x, Ball.y, Ball.width, Ball.height);
                                  g.setColor(Color.blue);//bat
                                  g.fill3DRect(Bat.x, Bat.y, Bat.width, Bat.height, true);
                                  g.setColor(Color.red);
                                  g.fillRect(0, 251, 350, 200);
                                  g.setColor(Color.gray);//brick
                                  g.drawRect(0, 0, 343, 250);
                                  for (int i = 0; i < Brick.length; i++)
                                  {
                                                if (Brick[i] != null)
                                                {
                                                                g.fill3DRect(Brick[i].x, Brick[i].y, Brick[i].width,
                                                                Brick[i].height, true);
                                                }
          }
								  g.setColor(Color.black);
								  g.drawString(s1,15,15);
								  g.drawString(score,55,15);
                  if (ballFallDown == true || bricksOver == true)
                  {
                                   Font f = new Font("Arial", Font.BOLD, 20);
                                   g.setFont(f);
                                   g.drawString(status, 70, 120);
                                   ballFallDown = false;
                                   bricksOver = false;
                  }
                }

                // /...Game Loop...................

                // /////////////////// When ball strikes borders......... it

                public void run()
                 {

                                  // //////////// =====Creating bricks for the game===>.....
                                                createBricks();
                                  // ===========BRICKS created for the game new ready to use===

                                  // ====================================================
                                  // == ball reverses when touches the brick=======
                                //ballFallDown == false && bricksOver == false
                                  while (true)
                                  {
                                                // if(gameOver == true){return;}
                                                   for (int i = 0; i < Brick.length; i++)
                                                   {
                                                                                if (Brick[i] != null)
                                                                                {
                                                                                                if (Brick[i].intersects(Ball))
                                                                                                 {
																													c1++;
																													score=String.valueOf(c1);
                                                                                                                  Brick[i] = null;
                                                                                                                  // movex = -movex;
                                                                                                                  movey = -movey;
                                                                                                                  count++;
                                                                                                }// end of 2nd if..
                                                                                }// end of 1st if..
                                                   }// end of for loop..

                                                   // /////////// =================================

                                                   if (count == Brick.length)
                                                   {// check if ball hits all bricks
                                                                                bricksOver = true;
                                                                                status = "YOU WON THE GAME";
                                                                                Ball.y=110;
                                                                                Ball.x=100;
                                                                                repaint();
                                                   }
                                                   // /////////// =================================
                                                   repaint();
                                                   Ball.x += movex;
                                                   Ball.y += movey;

                                                   if (left == true)
                                                   {
                                                                                Bat.x -= 3;
                                                                                right = false;
                                                   }
                                                   if (right == true)
                                                   {
                                                                                Bat.x += 3;
                                                                                left = false;
                                                   }
                                                   if (Bat.x <= 4)
                                                   {
                                                                                Bat.x = 4;
                                                   } else if (Bat.x >= 298)
                                                   {
                                                                                Bat.x = 298;
                                                   }
                                                   // /===== Ball reverses when strikes the bat
                                                   if (Ball.intersects(Bat))
                                                   {
                                                                                movey = -movey;
                                                                                // if(Ball.y + Ball.width >=Bat.y)
                                                   }
                                                   // //=====================================
                                                   // ....ball reverses when touches left and right boundary
                                                   if (Ball.x <= 0 || Ball.x + Ball.height >= 343)
                                                   {
                                                                                movex = -movex;
                                                   }// if ends here
                                                   if (Ball.y <= 0)
                                                   {
                                                                                // ////////////////|| bally + Ball.height >= 250
                                                                                movey = -movey;
                                                   }// if ends here.....
                                                   if (Ball.y >= 250)
                                                   {
                                                                                // when ball falls below bat game is over...
                                                                                ballFallDown = true;
                                                                                status = "YOU LOST THE GAME";
                                                                                repaint();
                                                                                // System.out.print("game");
                                                   }
                                                   try
                                                   {
                                                                                Thread.sleep(10);
                                                   } catch (Exception ex)
                                                   {
                                                   }// try catch ends here

                                  }// while loop ends here

                }

		// loop ends here

		// ///////..... HANDLING KEY EVENTS................//
		@Override
		public void keyPressed(KeyEvent e)
		{
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT) {
				left = true;
				// System.out.print("left");
				}

				if (keyCode == KeyEvent.VK_RIGHT)
				{
					right = true;
					// System.out.print("right");
				}
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_LEFT)
			{
							left = false;
			}

			if (keyCode == KeyEvent.VK_RIGHT)
			{
							right = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0)
			{

		}

		@Override
		public void actionPerformed(ActionEvent e)
			{
			String str = e.getActionCommand();
			if (str.equals("Restart"))
			{

							this.restart();

			}
			/*if(str.equals("Pause"))
			{
							@Override
							public void handle(ActionEvent event)   //pause
							{
											run.pause();
							}
			}*/
		}

		public void restart()
		{

			requestFocus(true);
			initializeVariables();
			createBricks();
			repaint();
		}

		public void initializeVariables()
		{

			// variables declaration for ball.................................
			ballx = 160;
			bally = 218;
			// variables declaration for ball.................................
			// ===============================================================
			// variables declaration for bat..................................
			batx = 160;
			baty = 245;
			// variables declaration for bat..................................
			// ===============================================================
			// variables declaration for brick...............................
			brickx = 50;
			bricky = 50;
			// variables declaration for brick...............................
			// ===============================================================
			// declaring ball, paddle,bricks
			Ball = new Rectangle(ballx, bally, 5, 5);
			Bat = new Rectangle(batx, baty, 40, 5);
			// Rectangle Brick;// = new Rectangle(brickx, bricky, 30, 10);
			Brick = new Rectangle[20];

			movex = -1;
			movey = -1;
			ballFallDown = false;
			bricksOver = false;
			count = 0;
			c1=0;
			score="0";
			status = null;


			}
		public void createBricks()
		{
		// //////////// =====Creating bricks for the game===>.....
			/*
			* creating bricks again because this for loop is out of while loop in
			* run method
			*/
			for (int i = 0; i < Brick.length; i++)
			{
										Brick[i] = new Rectangle(brickx, bricky, brickBreadth, brickHeight);
										if (i == 7)
										{
														brickx = 50;
														bricky = (bricky + brickHeight + 2);
										}
										if (i == 13)
										{
														brickx = 80;
														bricky = (bricky + brickHeight + 2);
										}
										if (i == 17)
										{
														brickx = 110;
														bricky = (bricky + brickHeight + 2);
										}

										brickx += (brickBreadth+1);
			}
		}

}

