//*Why create this class? 
//*The inheritance relationship between Pong and JFrame class is not obvious. 
//*If we want access to the JFrame functionality, we must create our own class and inheriting from it. 
//*We need to inherit the JFrame class to gain the window functionality.
//*Ball and Paddle are both a Sprite.

//*1.declare Sprite class variables as private.
//*2.provide public get and set methods, so these variables can be accessed via these methods.
//getter()returns a variable value, setter()sets it's value

import java.awt.Rectangle;
import java.awt.Color;

public class Sprite {
	private int xPosition, yPosition; //declare variables-position
	private int xVelocity, yVelocity; //declare variables-velocity
    private int width, height; // declare width and height
    private Color colour;
    private int initialXPosition,initialYPosition;
    
    //getter method--return the variables
    public int getxPosition() {
    	return xPosition;
    } 
    public int getyPosition() {
    	return yPosition;
    } 
    public int getxVelocity() {
    	return xVelocity;
    } 
    public int getyVelocity() {
    	return yVelocity;
    } 
    public int getWidth() {
    	return width;
    } 
    public int getHeight() {
    	return height;
    } 
    public Color getColour() {
		return colour;
	}
    
    //setter method--takes the parameter and assigns it to the variables
    //The 'this' keyword is used to refer to the current object
    public void setxPosition(int xPosition) {
    	this.xPosition = xPosition;
    }
    public void setyPosition(int yPosition) {
    	this.yPosition = yPosition;
    }
    public void setxVelocity(int xVelocity) {
    	this.xVelocity = xVelocity;
    }
    public void setyxVelocity(int yVelocity) {
    	this.yVelocity = yVelocity;
    }
    public void setWidth(int width) {
    	this.width = width;
    }
    public void setHeight(int height) {
    	this.height = height;
    }
	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	// use the setXPosition() and setYPosition() methods to keep the Sprite within the bounds of the screen.
	// update the new copies to accept a second parameter, integer panelWidth.
	// use if statement to check if the new position is outside the screen
	public void setxPosition(int newX, int panelWidth) {
	     xPosition = newX;
	     if (xPosition < 0) {
	    	 xPosition =0;
	     }else if (xPosition +width > panelWidth) {
	    	 xPosition = panelWidth-width;	 
	     }
	 }
	 public void setyPosition(int newY, int panelHeight) {
	     yPosition = newY;	   
	     if (yPosition < 0) {
	    	 yPosition =0;
	     }else if (yPosition +height > panelHeight) {
	    	 yPosition = panelHeight-height;	 
	     }
	 }
	 //Add two new integer variables, initialXPosition and initialYPosition. 
	 //Create a method to set both of these since this will only happen once
	 //call this setInitialPosition() with two parameters. We do not need a method to get these values.
	 public void setInitialPosition(int initialX, int initialY) {
		 initialXPosition = initialX;
		 initialYPosition = initialY;
	 }
	 //Add a new method called resetToInitialPosition(). 
	 //This method should set the x and y position of the object to the initial x and y positions.
     public void resetToInitialPosition() {
    	 setxPosition(initialXPosition);
         setyPosition(initialYPosition);
     }
     
     //create rectangle object that can be used for both ball and paddles
     //import a new class from library
     //add a get method that returns a new rectangle with a few parameters
     public Rectangle getRectangle() {
         return new Rectangle(getxPosition(), getyPosition(), getWidth(), getHeight());
     }

}
