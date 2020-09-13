/**
 * Parent class to both games. Has inside it the shared methods
 * @author Jordan Henkelman
 *
 */
public abstract class Game {
	
	protected Player p;
	protected ConsoleComunicationController comm;
	protected boolean endProgram;
	protected boolean outOfMoney;
	protected int newBalance;
	protected Menu continueMenu;
	
	/**
	 * constructor for a game. 
	 * @param p takes the reference to the object of the player who will be playing the games. 
	 */
	public Game(Player p) {
		this.p=p;
		comm = new ConsoleComunicationController();
	}
	
	// main method for running each game. is over ridden in all cases.
	public abstract void playGame();
	
	public void outOfMoney() {
		
		if(newBalance<=0) {
			System.out.println("You have no coins left to play with. Returning to menu.");
			endProgram=true;
			outOfMoney=true;
		}
	}
	
	/**
	 * getter method 
	 * @return returns the players updated balance
	 */
	public int getNewBalance() {
		return newBalance;
	}
	
	/**
	 * method used to prompt the user to play again
	 */
	public void playAgain() {

		char keepPlaying;

		System.out.println("Would you like to play again? ");
		continueMenu();
		keepPlaying = continueMenu.getUserChoice();
		continueOptions(keepPlaying);

	}
	
	/**
	 * method used to make sure the game is exited if the user selected option 'N'
	 * @param userSelection takes in the users continue selection 
	 */
	public void continueOptions(char userSelection) {


		switch(userSelection)
		{

		case 'Y': 

			break;

		case 'N': 
			System.out.println("Exiting game");
			endProgram = true;
			break;

		}
	}
	
	/**
	 * Adds items to a list that then prints, giving the user options of whether to continue or not 
	 */
	public void continueMenu() {

		continueMenu = new Menu(comm);

		continueMenu.addMenuOption( new MenuOption('Y',"Yes") );
		continueMenu.addMenuOption( new MenuOption('N',"No") );

	}
}
