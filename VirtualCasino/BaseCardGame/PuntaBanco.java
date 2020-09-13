import java.util.*;


/**
 * This class has everything the player needs to continuously play Punta Banco
 * @author Jordan Henkelman
 *
 */
public class PuntaBanco extends Game {

	private Menu betMenu;
	private Deck gameDeck;
	CardHand playerHand = null;
	CardHand bankerHand = null;
	private char userSelection;
	private double payout;
	private int pTotal;
	private int bTotal;
	private char winner;
	private int bet;
	Scanner kb = new Scanner (System.in);
	Card temp = new Card(0, 0);

	/**
	 * Constructor method
	 * @param p takes in a reference to the object of the player who is playing
	 */
	public PuntaBanco(Player p) {
		super(p);
		newBalance = p.getBalance();
		gameDeck = new Deck();
		continueMenu();

	}

	/**
	 * This method uses all other methods to run the entirety of the game Punta Banco
	 */
	public void playGame() {
		
		outOfMoney();

		while(!endProgram && !outOfMoney) {

			playerHand = new CardHand();
			bankerHand = new CardHand();
			betMenu = new Menu();
			continueMenu = new Menu();
			int playerDrawRank = 0;

			endProgram = false;
			
			// 1. when a deck runs out, we need a new deck 
			if (gameDeck.size()<=0) {
				gameDeck = new Deck();
			}
			
			System.out.println();
			System.out.println("Now playing: Punta Banco");

			gameDeck.shuffle();
			System.out.println("Deck shuffled.");

			placeBet();
			
			//deal one to player
			dealToPlayer();

			//deal one to banker
			dealToBanker();

			//deal another to player and display hand
			dealToPlayer();

			//deal another to banker and display hand
			dealToBanker();

			System.out.println("Player hand: " + playerHand);
			System.out.println("Banker hand: " + bankerHand);

			playerTotal();
			bankerTotal();

			// if neither the player or banker have an 8 or 9. skips to step 8
			if(pTotal < 8 && bTotal < 8) {

				if(pTotal <= 5) {
					temp = gameDeck.dealCard();
					playerHand.addCard(temp);
					System.out.println("Dealing card to player: " + temp.toString());
					playerDrawRank = temp.getRank();

				}

				// i. If the Player did not draw a card, the Banker draws if he (the Banker) has 0-5, and stands if he has 6-7.
				else {

					if(bTotal <= 5) {
						dealToBanker();
					}
				}

				// ii. If the Player drew a 2 or 3, the Banker draws if he (the Banker) has 0-4, and stands if he has 5-7.
				if(playerDrawRank == 2 || playerDrawRank == 3 && bTotal <= 4) {
					dealToBanker();
				}

				// iii. If the Player drew a 4 or 5, the Banker draws if he has 0-5, and stands if he has 6-7.
				else if(playerDrawRank == 4 || playerDrawRank == 5 && bTotal <= 5) {
					dealToBanker();
				}

				// iv. If the Player drew a 6 or 7, the Banker draws if he has 0-6, and stands if he has 7.
				else if(playerDrawRank == 6 || playerDrawRank == 7 && bTotal <= 6) {
					dealToBanker();
				}

				// v. If the Player drew an 8, the Banker draws if he has 0-2, and stands if he has 3-7.
				else if(playerDrawRank == 8 && bTotal <= 2) {
					dealToBanker();
				}

				// vi. If the Player drew an Ace, 9, 10, or face card (Jack, Queen or King), the Banker draws if he has 0-3, and stands if he has 4-7.
				else if(playerDrawRank > 8 && bTotal <= 3) {
					dealToBanker();
				}

				//calculating each hand value mod 10
				playerTotal();
				bankerTotal();

			}
			
			//Step 8 At this point, the result of the game are determined: the Player wins if his total beats the Banker, and the Banker wins if his total beats the Player.
			//Or, if the totals are the same, the result is a tie (Egalite).
			determineWinner();

			if(userSelection==(winner)) {
				System.out.println("Congratulations, you've won! ");
				newBalance =(int) (newBalance + bet * payout);
			}
			else {
				System.out.format("You've lost %d coints", bet);
				System.out.println();
				newBalance = newBalance - bet;
			}
			System.out.println("Your balance is: " + newBalance);

			playAgain();
			
			if(newBalance == 0) {
				System.out.println("You have no coins left to play with. Returning to menu.");
				endProgram = true;
			}
		}

	}
	

	// method for dealing a card to the banker 
	public void dealToBanker() {
		temp = gameDeck.dealCard();
		bankerHand.addCard(temp);
		System.out.println("Dealing card to banker: " + temp.toString());
	}

	//method for dealing a card to the player 
	public void dealToPlayer() {
		temp = gameDeck.dealCard();
		playerHand.addCard(temp);
		System.out.println("Dealing card to player: " + temp.toString());
	}

	//determines the winner 
	public void determineWinner() {

		System.out.println("Determining winner.");
		System.out.println();

		if(pTotal>bTotal) {
			winner = 'P';
			System.out.println("The Punto (Player) wins.");

		}

		else if(pTotal<bTotal) {
			winner = 'B';
			System.out.println("The Banco (Banker) wins.");
		}

		else {
			winner = 'T';
			System.out.println("It's an Egalite (tie).");
		}
	}


	/**
	 * lets the user choose how much they would like to bet, and choose who/ what they would like to bet on. 
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

				menu();
				System.out.print("Who/what would you like to bet on? ");
				userSelection = betMenu.getUserChoice();
				calcPayout(userSelection);
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
	 * calculates how much money the user will win depending on their bet choice 
	 * @param userSelection takes in the users bet selection 
	 */
	private void calcPayout(char userSelection)
	{
		switch(userSelection)
		{

		case 'P': payout = 1.0;

		break;

		case 'B': payout = 0.95;

		break;

		case 'T': payout = 8.0;

		break;

		default: System.out.println("Invalid input");

		}

	}

	/**
	 * Adds items to a list of options that the user can bet on
	 */
	public void menu() {
		betMenu = new Menu(comm);

		betMenu.addMenuOption( new MenuOption('P',"Player (Punto) wins") );
		betMenu.addMenuOption( new MenuOption('B',"Banker (Banco) wins") );
		betMenu.addMenuOption( new MenuOption('T',"Tie game (Egalite)") );
	}

	/**
	 * calculates the players total points mod 10
	 */
	public void playerTotal() {
		pTotal = 0;

		for(int i=0; i < playerHand.size(); i++) 
		{

			int tempRank= playerHand.get(i).getRank();
			
			if(tempRank == 14) {
				tempRank = 11;
			}
			else if(tempRank > 10) {
				tempRank = 10;
			}
			pTotal += tempRank;
		}

		while(pTotal >= 10) {
			pTotal -= 10;
		}
		System.out.println("Player Total: " + pTotal);

	}

	/**
	 * calculates the bankers total points mod 10
	 */
	public void bankerTotal() {
		bTotal = 0;

		for(int i=0; i < bankerHand.size(); i++) 
		{

			int tempRank= bankerHand.get(i).getRank();
			bTotal += tempRank;
		}

		while(bTotal >= 10) {
			bTotal -= 10;
		}
		System.out.println("Banker Total: " + bTotal);

	}
	
	/**
	 * This method is just for testing to ensure the constructor is working 
	 * @return the game deck
	 */
	public Deck getDeck() {
		return gameDeck;
	}
	
	/**
	 * This method is for testing other methods 
	 * @return the cards in the bankers hand
	 */
	public CardHand getBankerHand() {
		return bankerHand;
	}
	
	/**
	 * this method is for testing other methods 
	 * @return the cards in the players hand
	 */
	public CardHand getPlayerHand() {
		return playerHand;
	}
	
	/**
	 * This method is for testing other methods 
	 * @return the number to be multiplied by the users bet 
	 */
	public double getPayout() {
		return payout;
	}
	


}
