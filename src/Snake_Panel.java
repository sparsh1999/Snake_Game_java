import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Snake_Panel extends JPanel implements KeyListener , ActionListener
{
	// implements keylistener for up ,down etc keys to move our snake
	
	private int snakelengx[] = new int[750];
	private int snakelengy[] = new int[750];
	int length = 3,moves =0,scores=0;
	
	// below are the food locations 
	int [] foodx = new int[] {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	int [] foody = new int[] {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	Random random = new Random();
	int xpos = random.nextInt(34);
	int ypos = random.nextInt(23);
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon upmouth ,downmouth,rightmouth,leftmouth,snakebody,food;
	
	// to manage the speed of the snake
    javax.swing.Timer timer;
	int delay = 100;
	
	private ImageIcon img;
    public Snake_Panel()
    {
    	setFocusable(true);
    	addKeyListener(this);
    	setFocusTraversalKeysEnabled(false);
    	timer = new javax.swing.Timer(delay, this);
    	timer.start();
    }
    public void paint(Graphics g)
    {
    	// this is the title image borderg
    	g.setColor(Color.BLACK);
    	g.drawRect(24, 10, 851, 55);
    	
    	if (moves ==0)
    	{
    		snakelengx[2]=50;
    		snakelengx[1]=75;
    		snakelengx[0]=100;
    		
    		snakelengy[2]=100;
    		snakelengy[1]=100;
    		snakelengy[0]=100;
    		
    		right = true;
    	}
    	// set the title
    	img = new ImageIcon("snaketitle.jpg");
    	img.paintIcon(this, g, 25, 11);
    	
    	// set the border 
    	g.setColor(Color.WHITE);
    	g.drawRect(24, 74, 851, 577);
    
    	// set the background of the gameplay
    	g.setColor(Color.BLACK);
    	g.fillRect(25, 75, 850, 575);
    	
    	// drawing the scores
    	g.setColor(Color.white);
    	g.setFont(new Font("arial", Font.PLAIN, 14));
    	g.drawString("Scores : "+scores, 780, 30);
    	g.drawString("Length : "+length , 780, 50);
    	
    	for (int a =0;a<length;a++)
    	{
    		if (a==0&&right)
    		{
    			rightmouth = new ImageIcon("rightmouth.png");
    			rightmouth.paintIcon(this, g, snakelengx[a], snakelengy[a]);
    		}
    		if (a==0&&left)
    		{
    			leftmouth = new ImageIcon("leftmouth.png");
    			leftmouth.paintIcon(this, g, snakelengx[a], snakelengy[a]);
    		}
    		if (a==0&&up)
    		{
    			upmouth = new ImageIcon("upmouth.png");
    			upmouth.paintIcon(this, g, snakelengx[a], snakelengy[a]);
    		}
    		if (a==0&&down)
    		{
    			downmouth = new ImageIcon("downmouth.png");
    			downmouth.paintIcon(this, g, snakelengx[a], snakelengy[a]);
    		}
    		if (a!=0)
    		{
    			snakebody = new ImageIcon("snakeimage.png");
    			snakebody.paintIcon(this, g, snakelengx[a], snakelengy[a]);
    		}
    	}
    	food = new ImageIcon("enemy.png");
    	food.paintIcon(this, g, foodx[xpos], foody[ypos]);
    	if (snakelengx[0]==foodx[xpos]&&snakelengy[0]==foody[ypos])
    	{
    		scores++;
    		length++;
    		xpos = random.nextInt(34);
    		ypos = random.nextInt(23);
    	}
    	
    	// to check if a collision occurs
    	for (int b=1;b<length;b++)
    	{
    		if (snakelengx[b]==snakelengx[0]&&snakelengy[b]==snakelengy[0])
    		{
    			right = left = up = down = false;
    			g.setColor(Color.white);
    			g.setFont(new Font("arial",Font.BOLD,50));
    			g.drawString("Game Over", 300,300);
    			
    			g.setFont(new Font("arial",Font.BOLD,20));
    			g.drawString("Space to Restart",350, 340);
    		}
    	}
    	g.dispose();
    }
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		timer.start();
		if (right)
		{
			for (int r = length-1;r>=0;r--)
			{
				snakelengy[r+1]=snakelengy[r];
			}
			for (int r = length;r>=0;r--)
			{
				if (r==0)
				{
					snakelengx[r] = snakelengx[r]+25;
				}
				else
				{
					snakelengx[r]=snakelengx[r-1];
				}
				if (snakelengx[r]>850)
				{
					snakelengx[r]=25;
				}
			}
		}
		if (left)
		{
			for (int r = length-1;r>=0;r--)
			{
				snakelengy[r+1]=snakelengy[r];
			}
			for (int r = length;r>=0;r--)
			{
				if (r==0)
				{
					snakelengx[r] = snakelengx[r]-25;
				}
				else
				{
					snakelengx[r]=snakelengx[r-1];
				}
				if (snakelengx[r]<25)
				{
					snakelengx[r]=850;
				}
			}
		}
		if (down)
		{
			for (int r = length-1;r>=0;r--)
			{
				snakelengx[r+1]=snakelengx[r];
			}
			for (int r = length;r>=0;r--)
			{
				if (r==0)
				{
					snakelengy[r] = snakelengy[r]+25;
				}
				else
				{
					snakelengy[r]=snakelengy[r-1];
				}
				if (snakelengy[r]>625)
				{
					snakelengy[r]=75;
				}
			}
		}
		if (up)
		{
			for (int r = length-1;r>=0;r--)
			{
				snakelengx[r+1]=snakelengx[r];
			}
			for (int r = length;r>=0;r--)
			{
				if (r==0)
				{
					snakelengy[r] = snakelengy[r]-25;
				}
				else
				{
					snakelengy[r]=snakelengy[r-1];
				}
				if (snakelengy[r]<75)
				{
					snakelengy[r]=625;
				}
			}
		}
		repaint();
		
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyChar()==KeyEvent.VK_SPACE)
		{
			moves =0;
			scores =0;
			length=3;
			repaint();
			
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			moves ++;
			if (!left)
			{
			   right = true ;
			}
			else
			{
				right = false ;
				left = true ;
			}
			up = false ;
			down = false ;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moves ++;
			if (!right)
			{
			   left = true ;
			}
			else
			{
				left = false ;
				right = true ;
			}
			up = false ;
			down = false ;
		}
		if (e.getKeyCode()==KeyEvent.VK_UP)
		{
			moves ++;
			if (!down)
			{
			   up = true ;
			}
			else
			{
				up = false ;
				down = true ;
			}
			left = false ;
			right = false ;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moves ++;
			if (!up)
			{
			   down = true ;
			}
			else
			{
				down = false ;
				up = true ;
			}
			right = false ;
			left = false ;
		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
