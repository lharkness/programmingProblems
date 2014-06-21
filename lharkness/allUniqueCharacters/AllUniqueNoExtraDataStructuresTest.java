
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AllUniqueNoExtraDataStructuresTest {
	@Test
	public void testThatRepeatsAreDetected() {
		assertTrue("Failed", AllUnique.containsRepeatedCharacters("Lee"));
	}

	@Test
	public void testThatUniqueStringsAreProcessedCorrectly() {
		assertEquals(AllUnique.containsRepeatedCharacters("Le"), false);
	}
}