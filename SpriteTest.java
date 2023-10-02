import static org.junit.Assert.assertEquals;
import javafx.scene.shape.*;


import org.junit.*;
/**
 * 
 * A test class for the Sprite class
 *
 */
public class SpriteTest {
	/**
	 * Testing to see if the height of the hitbox is accurate
	 */
	@Test
	public void test_getHitboxHeight() {
		Sprite c = new Sprite(10,20,30);
		Rectangle hitbox = new Rectangle(15, 35, 10 , 10);
		assertEquals("Testing Hitbox height" , hitbox.getHeight(), c.getHitbox().getHeight(), 0.001);
	}
	/**
	 * Testing to see if the width of the hitbox is accurate
	 */
	@Test
	public void test_getHitboxWidth() {
		Sprite c = new Sprite(10,20,30);
		Rectangle hitbox = new Rectangle(15, 35, 10 , 10);
		assertEquals("Testing Hitbox width" , hitbox.getWidth(), c.getHitbox().getWidth(), 0.001);
	}
	/**
	 * Testing to see if the x-coordinate of the hitbox is accurate
	 */
	@Test
	public void test_getHitboxX() {
		Sprite c = new Sprite(10,20,30);
		Rectangle hitbox = new Rectangle(15, 35, 10 , 10);
		assertEquals("Testing Hitbox x-coordinate" , hitbox.getX(), c.getHitbox().getX(), 0.001);
	}
	/**
	 * Testing to see if the y-coordinate of the hitbox is accurate
	 */
	@Test
	public void test_getHitboxY() {
		Sprite c = new Sprite(10,20,30);
		Rectangle hitbox = new Rectangle(15, 35, 10 , 10);
		assertEquals("Testing Hitbox y-coordinate" , hitbox.getY(), c.getHitbox().getY(), 0.001);
	}
}
