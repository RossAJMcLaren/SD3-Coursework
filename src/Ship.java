import java.awt.geom.Point2D;
import java.util.*;
import java.awt.Point;

public class Ship extends Thread implements java.io.Serializable{

    Stack<Point2D> previousMoves = new Stack<Point2D>();
	Random randomGenerator = new Random();
	boolean dead;
	boolean noMorePastMoves;
	public int turnDied;
	Point2D currentPos;

	//Checks if move made is valid
	void moveCheck()
	{
		boolean validMovement = false;
		int xPos =0, yPos =0;

		while(!validMovement)
		{
			validMovement = true;
			//Ensures that only values within the grid can be selected.
			xPos = randomGenerator.nextInt(3) - 1;
			yPos = randomGenerator.nextInt(3) - 1;
			//Ensure that the ship has moved.
			if(xPos == 0 && yPos == 0)
			{
				validMovement = false;
			}
			// Add the movement to the ships current position.
			if(validMovement)
			{
				xPos += this.currentPos.getX();
				yPos += this.currentPos.getY();
			}
			//Ensure ship is still in grid
			if(xPos < 0 || xPos > 3 || yPos < 0 || yPos > 3)
			{
				validMovement = false;
			}
		}
		//Move Ship
		this.currentPos = new Point(xPos, yPos);
	}

	void delete(Ship shipToDestroy)
	{
		shipToDestroy.currentPos.setLocation(5,5);
	}

	void remove(Ship shipToRemove) { shipToRemove.currentPos.setLocation(7 , 7);}

	public void run()
	{
		// Delay and output message to prove proper threading.
		try
		{
			Thread.sleep(60);
			System.out.println(this.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		moveCheck();
	}

}
