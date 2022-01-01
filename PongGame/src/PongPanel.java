import java.awt.Font;
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
    //add variable BAAL_MOVEMENT_SPEED to set the speed of the ball
    private final static int BALL_MOVEMENT_SPEED = 2;
    
    //With the basic game play now complete the next step is to add win conditions for the game. 
    //We need some method to track the score, a method to increase scores, and a method to detect if the game has been won and display this.
    private final static int POINTS_TO_WIN = 11; //indicate how many points a player must have to win the game.
    int player1Score = 0, player2Score = 0;
    Player gameWinner; //Hold the player who has won
    
    //set the font of winner display  
    //use static final variables to define this rather than hiding these settings within this method. 
    private final static int SCORE_TEXT_X = 100;
    private final static int SCORE_TEXT_Y = 100;
    private final static int SCORE_FONT_SIZE = 50;
    private final static String SCORE_FONT_FAMILY = "Serif";
    
    //display winner and set font
    private final static int WINNER_TEXT_X = 200;
    private final static int WINNER_TEXT_Y = 200;
    private final static int WINNER_FONT_SIZE = 40;
    private final static String WINNER_FONT_FAMILY = "Serif";
    private final static String WINNER_TEXT = "WIN!";
    
	//add constructor to the class
	public PongPanel() {
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer (TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);//add paddle movement 1 to know what key the player presses (call the keyPressed() method when a key is pressed and the keyReleased()method when a key is released.)
		setFocusable(true);	//add paddle movement 2,we need to set focusable because the JPanel (PongPanel) must have focus to receive keyboard events.
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
                  ball.setxVelocity(BALL_MOVEMENT_SPEED);
                  ball.setyVelocity(BALL_MOVEMENT_SPEED);
                  break;
    	      }
              case PLAYING: {
            	  moveObject(ball);            // Move ball
                  checkWallBounce();            // Check for wall bounce
                  checkPaddleBounce();          //Check for collision detection
                  checkWin();                   //Check if the game has been won
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
             paintScores(g);
             paintWinner(g);
	     }
	 }

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
    
	//This code below in keyPressed and keyRealeased will allow the movement of paddle2 with the up and down arrow keys. 
	//Update these methods to allow movement of paddle1 using the w and s keys.
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP) {
            paddle2.setyVelocity(-1);
        } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
            paddle2.setyVelocity(1);
        }	
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
            paddle2.setyVelocity(0);
        }
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
	}
	
	//when finish all class settings, Phase 3 is moving the ball.
	//P3-1 add moveObject method
	private void moveObject(Sprite obj) {
	      obj.setxPosition(obj.getxPosition() + obj.getxVelocity(),getWidth());
	      obj.setyPosition(obj.getyPosition() + obj.getyVelocity(),getHeight());
	}
	//P3-2 Add the method checkWallBounce(). Use conditional statements to test if the ball is at the edge of the screen. 
	//If it is, reverse the velocity.
	//Then, set the velocity of the ball in the initialising condition in the update()method. 
    //Add a BALL_MOVEMENT_SPEED variable to set the speed of the ball.
    //Add calls to moveObject(ball) and checkWallBounce() in the update()method.
	private void checkWallBounce() {
		if(ball.getxPosition() <= 0) {
	           // Hit left side of screen
	           ball.setxVelocity(-ball.getxVelocity());
	           addScore(Player.Two);
	           resetBall();
	       } else if(ball.getxPosition() >= getWidth() - ball.getWidth()) {
	           // Hit right side of screen
	           ball.setxVelocity(-ball.getxVelocity());
	           addScore(Player.One);
	           resetBall();
	       }
	       if(ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight()) {
	           // Hit top or bottom of screen
	           ball.setyVelocity(-ball.getyVelocity());
	       }
	}
	
	//reset the ball when the ball touches the left or right side and stop bouncing but get score
	//Add a new method named resetBall() that resets the ball to the initial position.
    // Update the checkWallBounce() method to reset the ball when the ball touches the left or sides.
	private void resetBall() {
		ball.resetToInitialPosition();
	}
	
	//Collision detection--Add a new method called checkPaddleBounce() in the PongPanel.
	// After calling checkWallBounce() in the update()method call checkPaddleBounce().
	private void checkPaddleBounce() {
		 if(ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
	          ball.setxVelocity(BALL_MOVEMENT_SPEED);
	      }
	     if(ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
	          ball.setxVelocity(-BALL_MOVEMENT_SPEED);
	     }
	}
	
	//add a method to take player as a parameter. Increase either player1Score or player2Score depending on the parameter.
	private void addScore(Player player) {
		if(player == Player.One) {
            player1Score++;
        } else if(player == Player.Two) {
            player2Score++;
        }
    }
	
	//Add a method named checkWin() to check if either player has scored enough points to win. 
	//If they have, set the gameWinner and then change the gameState to GameOver.
	//In the checkWallBounce() method, we need to increase the player score if it hits the left or right side of the screen. Include addScore(Player.One) and addScore(Player.Two) in the appropriate place.
    //Finally, add checkWin() after checkPaddleBounce() in the update() method.
	private void checkWin() {
        if(player1Score >= POINTS_TO_WIN) {
            gameWinner = Player.One;
            gameState = GameState.GAMEOVER;
        } else if(player2Score >= POINTS_TO_WIN) {
            gameWinner = Player.Two;
            gameState = GameState.GAMEOVER;
        }
    }
	
	//Display score--paint the scores
	//create paintScore() method and call it in the paintComponent() method
	 private void paintScores(Graphics g) {
         Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
         String leftScore = Integer.toString(player1Score);
         String rightScore = Integer.toString(player2Score);
         g.setFont(scoreFont);
         g.drawString(leftScore, SCORE_TEXT_X, SCORE_TEXT_Y);
         g.drawString(rightScore, getWidth()-SCORE_TEXT_X, SCORE_TEXT_Y);
    }
	 
	 //display the winner 
	 private void paintWinner(Graphics g) {
         if(gameWinner != null) {
             Font winnerFont = new Font(WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
             g.setFont(winnerFont);
             int xPosition = getWidth() / 2;
             if(gameWinner == Player.One) {
                xPosition -= WINNER_TEXT_X;
             } else if(gameWinner == Player.Two) {
                xPosition += WINNER_TEXT_X;
             }
             g.drawString(WINNER_TEXT, xPosition, WINNER_TEXT_Y);
        }
    }
}