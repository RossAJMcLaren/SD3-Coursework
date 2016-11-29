import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ross on 28/11/2016.
 */
public class Fleet {

    private ArrayList<Ship> fleetOfShips = new ArrayList<Ship>();

    public void addFleet(ArrayList<Ship> newFleet){fleetOfShips = newFleet;}

    public ArrayList<Ship> getFleetOfShips() {
        return fleetOfShips;
    }

    public void undo(){
        System.out.println("Undoing move");
    }
}
