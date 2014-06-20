
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AllUniqueNoExtraDataStructuresTest {
	@Test
	public void TestThatRepeatsAreDetected() {
		assertTrue("Failed", AllUnique.containsRepeatedCharacters("Lee"));
	}

	@Test
	public void TestThatUniqueStringsAreProcessedCorrectly() {
		assertEquals(AllUnique.containsRepeatedCharacters("Le"), false);
	}
}