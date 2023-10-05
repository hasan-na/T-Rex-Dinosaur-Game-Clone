
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * 
 * A test class for the Obstacle class
 *
 */

 	public class ObstacleTest {
 		/**
 		 * Testing to see if the generate() method in the Obstacle class randomly generates obstacles
 		 */
	@Test
	public void test_generate() {
		ObstacleGUI obstacle = new ObstacleGUI();
		ObstacleGUI actual = obstacle.generate();
		if(actual != null) {
			ObstacleGUI expected = new ObstacleGUI();
			assertEquals("Testing Obstacle Generation",  expected, actual);				
			}
		else {
			Object expected = null;
			assertEquals("Testing Obstacle Generation", expected, actual);
		}
		
		


	}

}


