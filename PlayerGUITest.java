

import org.junit.*;
import static org.junit.Assert.assertEquals;
import javafx.geometry.Bounds;

/**
 * 
 * A Test class for the Player class
 *
 */
public class PlayerGUITest {
	/**
	 * Testing if collision returns true when collision occurs
	 */
	@Test
	public void test_checkCollision() {
		
		PlayerGUI player = new PlayerGUI();
		World world = new World();
		world.getGame().set(0, player);
		world.getGame().get(0).getSprite().setHitbox(0, 200);
		world.getGame().add(new ObstacleGUI());
		boolean expected = false;
		for (DataProvider g: world.getGame())
		{
			if (g instanceof ObstacleGUI && g != null)
			{
				g.getSprite().setHitbox(0, 200);
				Bounds obstacleBounds = g.getSprite().getHitbox().localToScene(g.getSprite().getHitbox().getBoundsInParent());
				Bounds playerBounds = world.getGame().get(0).getSprite().getHitbox().localToScene(world.getGame().get(0).getSprite().getHitbox().getBoundsInParent(), true);
				if(obstacleBounds.intersects(playerBounds))
						{
						expected = true;
						}
			}
			
		}
		/**
		 * check to see if expected and actual match
		 */
		assertEquals("Testing Collsion Detection", expected , player.checkCollision(world));
	}

}







