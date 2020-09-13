import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the Card class to ensure it is working properly 
 * @author Jordan Henkelman
 *
 */

public class CardTest {

	/**
	 * Tests the Card constructor to ensure a card is created with the proper suit and number,
	 * both of which are assigned to numerical values. Tests against the numbers entered in card
	 * creation. 
	 */
	@Test
	public void testConstructor() {
		Card c = new Card(5, 3);

		assertEquals("Card Constructor - proper rank", c.getRank(), 5);
		assertEquals("Card constructor - proper suit", c.getSuit(), 3);
	}
	
	/**
	 * Tests the suit class. This method checks to make sure the proper suit is being assigned to the card,
	 * and that the suit does not have the wrong capitalization. 
	 */
	@Test 
	public void testSuit() {
		Card c = new Card(5,3);
		
		String card = c.toString();
		assertTrue(card.contains("Clubs"));
		assertFalse(card.contains("clubs"));
		
		Card c1 = new Card(1,1);
		
		String card1 = c1.toString();
		assertTrue(card1.contains("Hearts"));
		assertFalse(card1.contains("hearts"));
		
		Card c2 = new Card(9,0);
		
		String card2 = c2.toString();
		assertTrue(card2.contains("Diamonds"));
		assertFalse(card2.contains("diamonds"));
		
		Card c3 = new Card(12,2);
		
		String card3 = c3.toString();
		assertTrue(card3.contains("Spades"));
		assertFalse(card3.contains("spades"));
	}
	
	/**
	 * Tests the Rank class to ensure the proper titles for cards over 10 are being assigned.
	 * For example Queen, King, etc. This class also ensures that the correct capitalization is being 
	 * assigned. 
	 */
	@Test
	public void testRank() {
		
		Card c = new Card(12,3);
		
		String card = c.toString();
		assertTrue(card.contains("Queen"));
		assertFalse(card.contains("queen"));
		
		Card c1 = new Card(13,1);
		
		String card1 = c1.toString();
		assertTrue(card1.contains("King"));
		assertFalse(card1.contains("king"));
		
		Card c2 = new Card(14,0);
		
		String card2 = c2.toString();
		assertTrue(card2.contains("Ace"));
		assertFalse(card2.contains("ace"));
	}
	
	
	/**
	 * Tests the toString method to ensure the information stored within each card
	 * can be printed in the correct format. 
	 */
	@Test 
	public void testToString() {
		
		Card c = new Card(5, 3);
		String expectedString = "5 of Clubs";
		assertEquals("Match toString", expectedString, c.toString());
	}

}
