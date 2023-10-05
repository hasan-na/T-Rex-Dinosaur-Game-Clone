import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.layout.Pane;

/**
 * 
 * A class which contains the jump feature for the player class which updates hitboxes and the image coordinates
 *
 */
public class Player extends DataProvider
{
	/*
	 * Instance variable for Player class
	 */
	double velocityFinal = DataProvider.getFINAL_VELOCITY();
	
	/**
	 * JUMP METHOD
	 */
	public void jump()
	{
		double acceleration = DataProvider.getACCELERATION();
		double velocityInitial = DataProvider.getINITIAL_VELOCITY();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			/*
			 * Increment for each second that passed and print it to console
			*/
			public void run() {
				if (velocityFinal >=0)
				{
					setY((int) (DataProvider.getJUMP_CONSTANT() + velocityFinal));
					getSprite().setHitbox(getX(), getY());
				}
				else if (velocityFinal <0)
				{
					setY((int) (DataProvider.getJUMP_CONSTANT() - velocityFinal));
					getSprite().setHitbox(getX(), getY());
				}
				velocityFinal = velocityFinal - acceleration;
				
				if (velocityFinal <= (velocityInitial))
				{
					cancel();
				}
			}
		};
		
			timer.scheduleAtFixedRate(task, 0, 2);
	}
	
	/**
	 * CHECKCOLLISION METHOD:
	 * @param world in which to check for a collision between players and obstacles
	 * @return boolean if there has been a collision (true) and false otherwise
	 */
	public boolean checkCollision(World world)
	{
		for (DataProvider g: world.getGame())
		{
			if (g instanceof ObstacleGUI && g != null)
			{
				if(g.getSprite().getHitbox().getLayoutBounds().intersects(world.getGame().get(0).getSprite().getHitbox().getLayoutBounds()))
						{
						return true;
						}
			}

		}
		return false;
		
	}

	
	/**
	 *Override of getLayer() and getSprite() Methods from abstract parent class Game Object
	 */
	@Override
	public Pane getLayer() {
		return null;
	}

	@Override
	public Sprite getSprite() {
		return null;
	}
}

