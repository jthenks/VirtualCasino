import java.io.FileNotFoundException;

/**
 * Main used to run the program for testing the basic required features
 * @author jkidney/Jordan Henkelman
 * 
 * There was a basic template given by professor jkidney for this class (the structure of runner and main),
 * but 90% of the code written below is my own. - Jordan Henkelman
 * 
 */

public class Runner 
{
	private Menu deckMenu;
	private boolean endProgram;
	private ConsoleComunicationController comm;
	private Player p;
	private int newBalance;

	/**
	 * Default constructor 
	 */
	public Runner()
	{
		comm = new ConsoleComunicationController();
		endProgram = false;
		
		setUpMenu();
	}
	
	/**
	 * Sets up the menu that will be used just for part one of the program
	 */
	private void setUpMenu()
	{
		deckMenu = new Menu(comm);
		

		deckMenu.addMenuOption( new MenuOption('P',"Punta Banco") );
		deckMenu.addMenuOption( new MenuOption('H',"Hi-Lo") );
		deckMenu.addMenuOption( new MenuOption('Q',"Quit") );
	}

	/**
	 * runs the selected user choice 
	 * @param userSelection the validated selection given by the user
	 */
	private void runSelection(char userSelection)
	{
		
		switch(userSelection)
		{
		
		case 'P': PuntaBanco pB = new PuntaBanco(p);
			System.out.format("Reminder: Your balance is %d coins", p.getBalance());
			System.out.println();
			pB.playGame();
			newBalance = pB.getNewBalance();
			p.setBalance(newBalance);
			
			
			break;
		
		case 'H': HiLo hL = new HiLo(p);
			System.out.format("Reminder: Your balance is %d coins", p.getBalance());
			System.out.println();
			hL.playGame();
			newBalance = hL.getNewBalance();
			p.setBalance(newBalance);
			
			break;
			

		case 'Q': endProgram = true;
			
			break;
		}
	}
	

	/**
	 * Main Startup method for the part one of the program. It will
	 * run the entire interaction with the user.
	 * @throws FileNotFoundException 
	 */
	public void run() throws FileNotFoundException
	{
		char selection = ' ';

		p = new Player();
		p.findFile();
		System.out.println(p.toString());
		System.out.println();
		
		while(!endProgram)
		{
			System.out.println("Which game would you like to play? ");
			selection = deckMenu.getUserChoice();
			runSelection(selection);
		}
		p.save();
		System.out.println("Program closing. Thanks for playing.");
		
		
	}
	/**
	 * Main
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException 
	{
		Runner main = new Runner();
		main.run();
	}

}
