import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import java.util.Random;


public class gameManager {
	/* 
	
	*Possible Class for handling all of the logic needed for the game
	
	*This will include :-
	*	Spawning Enemies ++
	*	Turn Counter ++
	*	Undoing / Making Moves +-
	*	Conflict Handler ++1
	*
	*	Player and Enemy Movement ++

	*/
	private Random randomGenerator = new Random();
	private gameGrid grid = new gameGrid();
	private List<Ship> enemyShipsOnGrid = new ArrayList<Ship>();
	private ArrayList<Ship> shipsOnGrid = new ArrayList<Ship>();
	private Stack<ArrayList<Ship>> historyOfGrid = new Stack<ArrayList<Ship>>();
	private List<Ship> enemyShipsOnPlayerSquare = new ArrayList<Ship>();
	private undoBroker gameHistory = new undoBroker();
	private Fleet newFleet = new Fleet();
	boolean shouldCombatOccur = false;
	int turnCounter = 0;

	public gameGrid getGrid() {return grid;}

	public undoBroker getGameHistory() {
		return gameHistory;
	}

	masterSpaceShip createPlayer() {
		masterSpaceShip playerShip = new masterSpaceShip();
		Point2D playerXY = new Point(randomGenerator.nextInt(grid.getSkyMap().length), randomGenerator.nextInt(grid.getSkyMap().length));
		if (playerXY.getX() == 0 && playerXY.getY() == 0) {
			playerXY = new Point(randomGenerator.nextInt(grid.getSkyMap().length), randomGenerator.nextInt(grid.getSkyMap().length));
		}
		playerShip.currentPos = playerXY;
		grid.getGameSquare((int)playerXY.getX(), (int)playerXY.getY()).addShipToSquare(playerShip);
		return playerShip;
	}

	void gameUpdate(masterSpaceShip playerShip)
	{
		shipsOnGrid = new ArrayList<Ship>();
		playerShip.previousMoves.push(playerShip.currentPos);
		playerShip.run();
		grid.getGameSquare((int)playerShip.currentPos.getX(), (int)playerShip.currentPos.getY()).addShipToSquare(playerShip);
		shipsOnGrid.add(playerShip);

		if (randomGenerator.nextInt(4) == 1) {
			Ship newEnemy = shipFactory.createShip();
			newEnemy.currentPos = new Point(0,0);
			enemyShipsOnGrid.add(newEnemy);
			grid.getGameSquare(0, 0).addShipToSquare(newEnemy);
		}

		for (Ship e : enemyShipsOnGrid) {
			if (e.dead == false) {
				e.previousMoves.push(e.currentPos);
				e.run();
				grid.getGameSquare((int)e.currentPos.getX(), (int)e.currentPos.getY()).addShipToSquare(e);
				shipsOnGrid.add(e);
				if (grid.getGameSquare((int)playerShip.currentPos.getX(), (int)playerShip.currentPos.getY()) == grid.getGameSquare((int)e.currentPos.getX(), (int)e.currentPos.getY())) {
					enemyShipsOnPlayerSquare.add(e);
					shouldCombatOccur = true;
				}
			}
		}

		if (shouldCombatOccur == true) {
			combatHandler(playerShip);
			shouldCombatOccur = false;
		}
		grid.setCurrentSetup(shipsOnGrid);
		grid.UpdateGrids(shipsOnGrid);
		newFleet.addFleet(shipsOnGrid);
		gameHistory.addToHistory(newFleet);
		System.out.println(playerShip.currentPos);
		turnCounter++;
		System.out.println(turnCounter);
	}

	void combatHandler(masterSpaceShip masterShip)
	{
		List<Ship> shouldBeDeleted = new ArrayList<Ship>();

		if(enemyShipsOnPlayerSquare.size() == 1)
		{
			Ship e = enemyShipsOnPlayerSquare.get(0);
			e.dead = true;
			e.turnDied = turnCounter;
			System.out.println("Tch, Nothing personal Kid");
		}

		if(enemyShipsOnPlayerSquare.size() == 2)
		{
			if(masterShip.combatMode.getClass() == new offensiveMode().getClass())
			{
				for(Ship e: enemyShipsOnPlayerSquare)
				{
					System.out.println("Tch, Nothing personal Kid");
					e.dead = true;
					e.turnDied = turnCounter;
				}
			}
			else
			{
				masterShip = null;
				System.out.println("GAME OVER MAAAN!");
			}
		}

		if(enemyShipsOnPlayerSquare.size() == 3)
		{
			masterShip = null;
			System.out.println("GAME OVER MAAAN!");
		}

		for(Ship e : enemyShipsOnGrid)
		{
			if(e.dead == true)
			{
				shouldBeDeleted.add(e);
				e.delete(e);
			}
		}

		for(Ship e : shouldBeDeleted)
		{
			shipsOnGrid.remove(e);
		}

		shouldBeDeleted.clear();
		enemyShipsOnPlayerSquare.clear();
	}

	void changeMode(masterSpaceShip playerShip)
	{
		if(playerShip.combatMode.getClass() == new defensiveMode().getClass())
		{
			playerShip.setCurrentMode(new offensiveMode());
			playerShip.currentMode();
		}
		else
		{
			playerShip.setCurrentMode(new defensiveMode());
			playerShip.currentMode();
		}

	}


}

