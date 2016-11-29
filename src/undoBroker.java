import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Ross on 28/11/2016.
 */
public class undoBroker {

    private Stack<Fleet> previousMoves = new Stack<Fleet>();

    public void addToHistory(Fleet newMove) {
        previousMoves.push(newMove);
    }

    public void undoLastMove(gameGrid grid, int turnCounter) {
        if (!previousMoves.empty()) {
            turnCounter--;
            Fleet newFleet = previousMoves.pop();
            ArrayList<Ship> previousOrientation = newFleet.getFleetOfShips();
            ArrayList<Ship> nonExsistant = new ArrayList<Ship>();

                for (Ship s : previousOrientation) {
                    if (s.previousMoves.size() == 0 && s.getClass() != masterSpaceShip.class) {
                        s.dead = true;
                        s.remove(s);
                        nonExsistant.add(s);
                    }

                    if (!s.dead) {
                        s.currentPos = s.previousMoves.pop();
                        grid.getGameSquare((int) s.currentPos.getX(), (int) s.currentPos.getY()).addShipToSquare(s);
                    } else if (s.dead && s.turnDied == turnCounter) {
                        s.currentPos = s.previousMoves.pop();
                        System.out.println("I'M PONGO!");
                    }
                    if (s.currentPos.getX() == 5 && s.currentPos.getY() == 5) {
                        s.currentPos = s.previousMoves.pop();
                        grid.getGameSquare((int) s.currentPos.getX(), (int) s.currentPos.getY()).addShipToSquare(s);
                    }
                }
                previousOrientation.removeAll(nonExsistant);
                grid.UpdateGrids(previousOrientation);
        }
    }
}
