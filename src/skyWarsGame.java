import java.io.IOException;

public class skyWarsGame {


	public static void main(String[] args) throws IOException {
		gameManager gameController = new gameManager();
		masterSpaceShip masterShip = gameController.createPlayer();
		gameWindow letsPlay = new gameWindow(masterShip, gameController);
		letsPlay.show();
	}
}
