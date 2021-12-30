import java.awt.Color;


//1.Create a new class named “Paddle” that inherits from the Sprite class. 
//2.Add static final variables for the width, height, colour and distance from edge. Set values for each of these

public class Paddle extends Sprite {
	private static final int PADDLE_WIDTH= 10;
	private static final int PADDLE_HEIGHT =100;
	private static final Color PADDLE_COLOR=Color.WHITE;
	private static final int DISTANCE_FROM_EDGE = 40;
	
	//3.Add a constructor that takes the Player enum as a parameter, as well as PanelWidth and PanelHeight.
	//Then, set the width, height, and colour.
	//4.set the initial starting position by using a conditional statement and operators.   
	//You will also need to access the enum constant you created previously. use dot syntax (e.g.Player.Two). 
    //5.call resetToInitialPosition().
	public Paddle(Player player, int panelWidth, int panelHeight) {
	       setWidth(PADDLE_WIDTH);
	       setHeight(PADDLE_HEIGHT);
	       setColour(PADDLE_COLOR);
	       int xPos;
	          if(player == Player.One) {
	              xPos = DISTANCE_FROM_EDGE;
	          } else {
	              xPos = panelWidth - DISTANCE_FROM_EDGE - getWidth();
	          }
	       setInitialPosition(xPos, panelHeight / 2 - (getHeight() / 2));
	       resetToInitialPosition();
	   }

}


