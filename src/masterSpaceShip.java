public class masterSpaceShip extends Ship {
	protected shipCombatMode combatMode;
	// Basic constructor for a ship, by default the Master Space Ship is set into defensive mode
	public masterSpaceShip()
	{
		this.combatMode = new defensiveMode();
	}

	// Runs the combatMode.mode() method for whatever the current mode of the ship is.
	// The output returned from the combatMode.mode() method changes dynamically depending on what the current mode is.
	public void currentMode()
	{
		combatMode.mode();
	}
	// Allows users to set the combat mode of the ship dynamically during the game.
	public void setCurrentMode(shipCombatMode combatModeType)
	{
		this.combatMode = combatModeType;
	}
}
