import static org.junit.Assert.*;

import org.junit.Test;



/**
 * This class tests the CardHand class to ensure it work properly
 * @author Jordan Henkelman
 *
 */

public class CardHandTest {

	/**
	 * Tests to ensure the constructor is working. Creates a cardhand with no cards in, 
	 * and tests to ensure the card hand size really is 0. 
	 */
	@Test
	public void testConstructor() {
		
		CardHand c = new CardHand();
		
		assertEquals(c.size(), 0);
	}
	
	/**
	 * Tests the addCard method. A cardhand is created, and two cards 
	 * are added to the hand. This is then checked to ensure there are really two cards in the hand. 
	 */
	@Test
	public void testAddCards() {
		
		CardHand c = new CardHand();
		Card card = new Card(5,2);
		Card card1 = new Card(8,0);
		
		c.addCard(card);
		c.addCard(card1);
		
		assertEquals(c.size(), 2);
	}
	
	/**
	 * Tests the getCards method. Cards are created, added to the hand, and tested against 
	 * test cards of the same value. 
	 */
	@Test
	public void testGetCards() {
		
		CardHand c = new CardHand();
		Card card = new Card(5,2);
		Card card1 = new Card(8,0);
		
		c.addCard(card);
		c.addCard(card1);
		
		Card cardTest = c.get(0);
		Card cardTest2 = c.get(0);
		
		assertEquals(cardTest.toString(), card.toString());
		assertEquals(cardTest2.toString(), card.toString());
	}

}
