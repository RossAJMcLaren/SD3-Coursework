import java.awt.*;
import java.awt.geom.Point2D;

public class enemyShip extends Ship {

	public boolean dead = false;
	Point2D currentPos = new Point(0,0);
	void whenSpawned()
	{
		System.out.println("YOLO! I'm here lads! xD!");
	}
}
