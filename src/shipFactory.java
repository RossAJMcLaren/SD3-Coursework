import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by Ross on 28/11/2016.
 */
public class shipFactory {

    private static Point2D startPos = new Point(0, 0);

    public static enemyShip createShip()
    {
        enemyShip Ship = null;

        Random randomGenerator = new Random();

        int typeOfShip = randomGenerator.nextInt(3);

        if (typeOfShip == 0)
        {
            Ship = new battleCruiser();
        }
        else if (typeOfShip == 1)
        {
            Ship = new battleShooter();
        }
        else if (typeOfShip == 2)
        {
            Ship = new battleStar();
        }

        return Ship;
    }
}
