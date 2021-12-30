import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;


public class PongPanel extends JPanel implements ActionListener, KeyListener {
	private final static Color BACKGROUND_COLOUR = Color.BLACK;
	private final static int TIMER_DELAY = 5;
	//after creating ball class, add ball variable to the PongPanel class and then:
	//Create a new method called createObjects() that creates the ball object.
	//Create a new Boolean variable called 'gameInitialised' set to false. 
	//This gameInitialised variable will be removed and replaced with the new gameState variable.
	//This variable will be set to true when the game has finished initialisation & the objects have been successfully created.
	Ball ball;
	//* remove this variable when creating GameState: boolean gameInitialised = false;
	//add a GameState variable named 'gameState'.
	//Set the default value of gameState to GameState.Initialising.
	//then modify the update method, errors will happen because we have more game state instead of one(initialing)
	//we also need to update the paintComponent() method
	GameState gameState = GameState.INITIALISING;
	
	//Your last task is to update PongPanel to include the two paddles. 
	//--Declare variables named paddle1 and paddle2.
    //--Create the paddles in createObjects()
    //--Paint this paddle in paintComponent(). Hint: Look at what's been done here with the ball component already.
    Paddle paddle1, paddle2;

	//add constructor to the class
	public PongPanel() {
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer (TIMER_DELAY, this);
		timer.start();
	}
	
	public void createObjects() {
        ball = new Ball(getWidth(), getHeight());
        paddle1 = new Paddle(Player.One, getWidth(), getHeight());
        paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	}
	
	//after crate a boolean variable gameInitialised, then
	//In the update()method, if the game has not been initialised, call createObjects() and set gameInitialised to true. 
	//This is our set up of the game – it will only happen once.
	//then create a new method called paintSprite()
	//Call paintSprite() for the ball in the paintComponent() method BELOW
    private void update() {
    	switch(gameState) {
    	      case INITIALISING: {
              createObjects();
              gameState = GameState.PLAYING;
              break;
    	      }
              case PLAYING: {
              break;
              }
              case GAMEOVER: {
              break;
              }
    	}
    }
	
    private void paintSprite(Graphics g, Sprite sprite) {
        g.setColor(sprite.getColour());
        g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
    }
    
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setPaint(Color.WHITE);
	    g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2d.dispose();//*Dispose this object when we do not need to use it. It can release more space.
        //*By using the width and height of the window as parameters for drawing the line,
        //*we avoid having to change these values if someone decides the width or height needs to change.
	} 
	      
	@Override
	public void paintComponent(Graphics g) {
	     super.paintComponent(g);
	     paintDottedLine(g);
	     if(gameState != GameState.INITIALISING) {
             paintSprite(g, ball);
             paintSprite(g, paddle1);
             paintSprite(g, paddle2);
	     }
	 }

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
	}
 }