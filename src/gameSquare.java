import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Ross on 27/11/2016.
 */
public class gameSquare
{
    private ArrayList<Ship> shipsOnSquare;

    gameSquare()
    {
        shipsOnSquare = new ArrayList<Ship>();
    }

    public void addShipToSquare(Ship ship) {shipsOnSquare.add(ship);}

    public ArrayList<Ship> getShipsOnSquare(){return shipsOnSquare;}
}
