import static org.junit.Assert.assertEquals;
import org.junit.*;
/**
 * 
 * A Test class for the Data Provider class
 *
 */
public class DataProviderTest {
/**
 * Testing to see if the getGROUND_SPEED method returns 2000 when called
 */
	@Test
	public void test_getGROUND_SPEED() {
		int expected = 2000;
		int actual = DataProvider.getGROUND_SPEED();
		assertEquals("Testing getGROUND_SPEED",  expected, actual);
	}
	/**
	 * Testing to see if the getBIRD_SPEED returns 450 when called
	 */
	@Test
	public void test_getBIRD_SPEED() {
		int expected = 450;
		int actual = DataProvider.getBIRD_SPEED();
		assertEquals("Testing getBIRD_SPEED",  expected, actual);
	}
	/**
	 * Testing to see if the getWINDOW_WIDTH method returns 600 when called
	 */
	@Test
	public void test_getWINDOW_WIDTH() {
		int expected = 600;
		int actual = DataProvider.getWINDOW_WIDTH();
		assertEquals("Testing getWINDOW_WIDTH",  expected, actual);
	}
	/**
	 * Testing to see if the getWINDOW_HEIGHT method returns 400 when called
	 */
	@Test
	public void test_getWINDOW_HEIGHT() {
		int expected = 400;
		int actual = DataProvider.getWINDOW_HEIGHT();
		assertEquals("Testing getWINDOW_HEIGHT",  expected, actual);
	}
	
	@Test
	public void test_getACCELERATION() {
		double expected = 1.0;
		double actual = DataProvider.getACCELERATION();
		assertEquals("Testing getACCELERATION", expected, actual, 0.01);
	}

}

