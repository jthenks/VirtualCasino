import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Game class
 * @author Jordan Henkelman
 *
 */
public class GameTest {

	/**
	 * This method ensures that the constructor method is working properly. Checks that the deck
	 * size is the correct one, and checks that the player balance is the correct amount. 
	 */
	@Test
	public void testConstructor() {
		
		Player p = new Player();
		PuntaBanco pb = new PuntaBanco(p);
		
		assertEquals(pb.getDeck().size(), 52);
		
		assertEquals(pb.getNewBalance(), p.getBalance());
		
	}
	

}
