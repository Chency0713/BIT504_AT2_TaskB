//1.Create a new class named 'Ball' that inherits from the Sprite class.

import java.awt.Color;

public class Ball extends Sprite {
	//2.Add final static variables for the colour, width and height of the ball. 
	//Make the width and height 25. Colour should be white.
	private final static Color BALL_COLOUR = Color.RED;
	private final static int BALL_WIDTH = 25;
	private final static int BALL_HEIGHT = 25;
	
	//3.Add a constructor that sets the color, width, and height of the ball; 
	//a width and height parameter of the panel size should be provided.
	//4.Set the initial starting position
	//5.Call resetToInitialPosition(), which will set the position of the ball to the initial position we provided. 
	//This method was conveniently defined in the Sprite class.
	public Ball(int panelWidth, int panelHeight) {
       setWidth(BALL_WIDTH);
       setHeight(BALL_HEIGHT);
       setColour(BALL_COLOUR);
       setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
       resetToInitialPosition();
   }
}
