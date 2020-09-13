import java.util.Scanner;

/**
 * This class has everything the player needs to continuously play Hi-Lo
 * @author Jordan Henkelman
 *
 */

public class HiLo extends Game {

	private int bet;
	private char userSelection;
	private Menu betMenu;
	private int payout;
	private Card playerCard;
	private Card dealerCard;
	private Deck userDeck;
	private Deck dealerDeck;
	
	Scanner kb = new Scanner (System.in);

	
	/**
	 * constructor. creates object of hi-lo
	 * @param p takes in a reference to the player playing the game
	 */
	public HiLo(Player p) {
		super(p);
		newBalance = p.getBalance();
		dealerDeck = new Deck();
		userDeck = new Deck();
		continueMenu();
	}
	
	//this method uses all other methods to run the entirety of the game Hi-Lo
	public void playGame() {
		
		outOfMoney();
		
		while(!endProgram && !outOfMoney) {
			
			System.out.println();
			System.out.println("Now playing: High-Lo");
			
			betMenu = new Menu();
			continueMenu = new Menu();
			endProgram = false;
			
			// 1. when a deck runs out, we need a new deck 
			if (userDeck.size()<=0) {
				userDeck = new Deck();
			}
			if (dealerDeck.size()<=0) {
				dealerDeck = new Deck();
			}
			
			userDeck.shuffle();
			dealerDeck.shuffle();
			System.out.println("Decks shuffled.");
			
			// At the start of the game, the user is asked for the bet they want to use for the entire game play.
			placeBet();
	
			// 3. One card is delta from the players deck and displayed to the screen
			dealToPlayer();
			
			// 2. The user is asked if the dealers card will be higher, lower or same rank (2 throuh Ace) than the current card they hav
			higherOrLower();
			
			//A card is dealt from the dealer's deck and compared to the user's card. Both cards and the result of the play are displayed to the user and they payout is calculated as follows:
			dealToDealer();
			
				// i. If the dealer's card is higher, and the user's guess was Higher, they win what was bet (100 in gets them 200 back).
			if(dealerCard.getRank() > playerCard.getRank() && userSelection == 'H') {
				calcWinnings();
			}
			
				// ii. If the dealer's card is lower, and the user's guess was Lower, they also win what was bet (100 in gets them 200 back).
			else if(dealerCard.getRank() < playerCard.getRank() && userSelection == 'L') {
				calcWinnings();
			}
			
			// iv. If the cards match in both rank and suit, then they tie and no one wins or loses.
			else if(dealerCard.getRank() == playerCard.getRank() && dealerCard.getSuit() == playerCard.getSuit()) {
				System.out.println("The cards match in both rank and suit. Nobody wins.");
			}
			
				// iii. If the cards match in just rank, and the user guessed Same, they win double what was bet (100 in gets 300 back).
			else if (dealerCard.getRank() == playerCard.getRank() && userSelection == 'S') {
				calcWinnings();
			}
				// v. All other outcomes result in the player losing their bet.
			else {
				System.out.format("You've lost %d coins", bet);
				newBalance = newBalance - bet;
				System.out.println();
			}
			
			//Based upon the results, the user's balance is updated.
			
			System.out.format("Your new balance is: %d coins" , newBalance);
			System.out.println();
			
			//They then are asked if they want to play again or go back to the main menu.
			playAgain();
			
			if(newBalance == 0) {
				System.out.println("You have no coins left to play with. Returning to menu.");
				endProgram = true;
			}
			
		}
		
	}
	
	/**
	 * calculates the users winnings if they win
	 */
	public void calcWinnings() {
		
		newBalance = newBalance + bet * payout;
		System.out.println("You win!");
		System.out.println();
	}
	
	/**
	 * prompts the user to make a bet 
	 */
	public void placeBet() {
		boolean validBet = false;
		bet = 0;
		userSelection = ' ';
		
		while(!validBet) {

			System.out.print("How many coins would you like to bet? ");
			bet = kb.nextInt();
			System.out.println();

			if(bet<=newBalance && newBalance>0) {

				System.out.println("Bet placed");
				validBet = true;
			}
			else if(bet>newBalance) {
				System.out.println("Invalid. Bet amount cannot exceed player balance.");

			}
			else {
				System.out.println("Invalid input.");
			}
		}
	}
	
	/**
	 * assigns a value to the users payout depending on which case they bet on
	 * @param userSelection calls the users selection of which case they bet on 
	 */
	private void calcPayout(char userSelection)
	{
		switch(userSelection)
		{

		case 'H': payout = 1;

		break;

		case 'L': payout = 1;

		break;

		case 'S': payout = 2;

		break;

		default: System.out.println("Invalid input");

		}

	}
	
	/**
	 * Adds items to a menu of choices of what the user can bet on 
	 */
	public void menu() {
		betMenu = new Menu(comm);

		betMenu.addMenuOption( new MenuOption('H',"The dealers card will be higher (pays 1-1)") );
		betMenu.addMenuOption( new MenuOption('L',"The dealers card will be lower (pays 1-1)"));
		betMenu.addMenuOption( new MenuOption('S',"The dealers card will be the same (pays 2-1)"));
	}
	
	/**
	 * deals a card to the player
	 */
	public void dealToPlayer() {
		playerCard = userDeck.dealCard();
		System.out.println("Dealing card to user: " + playerCard.toString());
	}
	
	// method for dealing a card to the dealer
	public void dealToDealer() {
		dealerCard = dealerDeck.dealCard();
		System.out.println("Dealing card to dealer: " + dealerCard.toString());
	}
	
	/**
	 * prompts the user to choose if they think the dealers card will be higher or lower than the users
	 */
	public void higherOrLower() {

		userSelection = ' ';
		menu();
		System.out.print("Do you think the dealers card will be higher, lower, or the same?");
		System.out.println();
		userSelection = betMenu.getUserChoice();
		calcPayout(userSelection);
		
	}
	
	/**
	 * This method is used to test the constructor
	 * @return return the users deck
	 */
	public Deck getUserDeck() {
		return userDeck;
	}
	
	/**
	 * This method is used to test the constructor 
	 * @return returns the dealers deck 
	 */
	public Deck getDealerDeck() {
		return dealerDeck;
	}
	

}
