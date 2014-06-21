
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PermutedStringsDetectorTest {
	@Test
	public void testThatPermutationsAreDetected() {
		assertTrue("Failed", PermutedStringsDetector.isPermutation("Lee", "eeL"));
	}

	@Test
	public void testThatNonPermutationsAreDetected() {
		assertEquals(PermutedStringsDetector.isPermutation("Le", "Lee"), false);
	}
}