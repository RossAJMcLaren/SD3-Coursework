import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by Ross on 27/11/2016.
 */
public class gameGrid
{
    private gameSquare[][] skyMap = new gameSquare[4][4];
    private ArrayList<Ship> currentSetup = new ArrayList<Ship>();
    private Stack<ArrayList<Ship>> previousGridSetup = new Stack<ArrayList<Ship>>();

    gameGrid()
    {
        for(int column = 0; column < 4; column++)
        {
            for(int row = 0; row < 4; row++)
            {
                skyMap[column][row] = new gameSquare();
            }
        }
    }

    public ArrayList<Ship> getCurrentSetup(){return currentSetup;}

    public void setCurrentSetup(ArrayList<Ship> currentOrientation){currentSetup = currentOrientation;}

    public gameSquare[][] getSkyMap(){return skyMap;}

    gameSquare getGameSquare(int column, int row)
    {
        return skyMap[column][row];
    }

    public ArrayList<Ship> getPreviousGridSetup()
    {
        return previousGridSetup.pop();
    }

    public void setPreviousGridSetup(ArrayList<Ship> previousSetup)
    {
        previousGridSetup.push(previousSetup);
    }


    String updateGUIGrid(int column, int row)
    {
        gameSquare visualSquare = skyMap[column][row];
        String infoForGUI = "";

        for(Ship s : visualSquare.getShipsOnSquare())
        {
            if(s.getClass() == masterSpaceShip.class)
            {
                infoForGUI = "Master Ship";
                if(visualSquare.getShipsOnSquare().size() > 1)
                {
                    infoForGUI +=  "+ Enemies X";
                    infoForGUI += visualSquare.getShipsOnSquare().size() -1;
                }
                return infoForGUI;
            }
        }
        if(visualSquare.getShipsOnSquare().size() > 0)
        {
            if(visualSquare.getShipsOnSquare().size() == 1)
            {
                if(visualSquare.getShipsOnSquare().get(0).getClass() == battleCruiser.class)
                {
                    infoForGUI = "Battle Cruiser";
                }
                if(visualSquare.getShipsOnSquare().get(0).getClass() == battleShooter.class)
                {
                    infoForGUI = "Battle Shooter";
                }
                if(visualSquare.getShipsOnSquare().get(0).getClass() == battleStar.class)
                {
                    infoForGUI = "Battle Star";
                }
                return infoForGUI;
            }
            else {
                infoForGUI += "Enemies X";
                infoForGUI += visualSquare.getShipsOnSquare().size();
                return infoForGUI;
            }
        }

        return infoForGUI;
    }

    Boolean UpdateGrids(ArrayList<Ship> ships)
    {
        for(int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                skyMap[i][j].getShipsOnSquare().clear();
            }
        }

        for(Ship ship : ships)
        {
            int x,y;
            x = (int)ship.currentPos.getX();
            y = (int)ship.currentPos.getY();
            skyMap[x][y].addShipToSquare(ship);
        }
        return true;
    }
}
