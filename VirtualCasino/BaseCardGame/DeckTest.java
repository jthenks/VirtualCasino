import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Deck class to ensure it is working properly 
 * @author Jordan Henkelman
 *
 */
public class DeckTest {

	/**
	 * Tests the constructor to ensure the deck is being created with the 
	 * correct amount of cards inside.
	 */
	@Test
	public void testConstructor() {
		Deck d = new Deck();

		assertEquals("Deck size - 52", d.size(), 52);

	}
	
	/**
	 * Tests the Shuffle method to ensure the cards are being rearranged. 
	 * Creates a deck, copies it, shuffles the original, then compares the 
	 * shuffled deck to the copy to ensure they are different. 
	 */
	@Test
	public void testShuffle() {
		Deck d = new Deck();
		
		String beforeShuffle = d.toString();
		
		d.shuffle();
		
		assertNotEquals(beforeShuffle, d.toString());
		
	}
	
	/**
	 * Tests the TopCard method. Prints the entire deck so that we can see what the 
	 * top card is, then compares that card to the one given from the TopCard method
	 * to ensure they match.
	 */
	@Test 
	public void testTopCard() {
		
		Deck d = new Deck();
		
		System.out.println(d);
		
		String topCard = d.topCard().toString();
		
		assertEquals(topCard, "2 of Diamonds");
	}
	
	/**
	 * Tests the DealCard method. Since we know the topcard method works,
	 * we can use the same tactic of printing the deck, comparing the top card,
	 * then dealing the card and comparing that card to the one printed in the deck. 
	 */
	@Test
	public void testDealCard() {
		
		Deck d = new Deck();
		
		System.out.println(d);
		
		String topCard = d.dealCard().toString();
		
		assertEquals(topCard, "2 of Diamonds");
		
	}
	
	/**
	 * Tests the DealHand method. This method creates a card hand and a deck, deals the top two
	 * cards of the deck to the card hand, and compares that hand to another card hand with those 
	 * same top two cards to ensure they match.
	 */
	@Test
	public void testDealHand() {
	
     CardHand c = new CardHand();
     Deck d = new Deck();
     CardHand c2 = new CardHand();
     Card card1 = new Card(2, 0);
     Card card2 = new Card(3, 0);
     
     c2.addCard(card1);
     c2.addCard(card2);
     
    c = d.dealHand(2);
    
    assertEquals(c.toString(), c2.toString());
	}
	
	

}
