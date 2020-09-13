import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the PuntaBanco game
 * @author Jordan Henkelman
 *
 */
public class PuntaBancoTest {

	/**
	 * Tests the constructor to ensure the deck is the correct size
	 * and the player balance is the correct amount.
	 */
	@Test
	public void testConstructor() {
		
		Player p = new Player();
		PuntaBanco pb = new PuntaBanco(p);
		
		assertEquals(pb.getDeck().size(), 52);
		
		assertEquals(pb.getNewBalance(), p.getBalance());
		
	}
	

	
	

}
