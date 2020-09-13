import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the HiLo class
 * @author Jordan
 *
 */
public class HiLoTest {

	/**
	 * Tests the constructor method. Ensures that the dealer deck and user decks
	 * are the correct size, and the player balance is the proper amount.
	 */
	@Test
	public void testConstructor() {
		
		Player p = new Player();
		HiLo hL = new HiLo(p);
		
		assertEquals(hL.getDealerDeck().size(), 52);
		assertEquals(hL.getUserDeck().size(), 52);
		
		assertEquals(hL.getNewBalance(), p.getBalance());
		
	}
}


