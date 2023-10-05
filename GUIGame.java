import java.awt.event.KeyListener;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * 
 * A class which contains the major game loops, and adds all graphical features to the interface
 * Manages upkeep of score, colision detection, adding of layers, and jump input
 *
 */
public class GUIGame extends Application implements KeyListener {
	
	/*
	 * Instance variables used in GUIGAME class
	 */
	static Scene scene;
	Timeline obstacleGenerationTimeline = new Timeline();
	
	/**
	 * Override of the start method of the application
	 * @param Stage primaryStage where application will be shown.
	 */
	@Override
	public void start(Stage primaryStage) {		
		try {
			/*
			 * Create root Pane, layers to insert every Game Object, and world to store each Game Object.
			 */
			Pane root = new Pane();
			Pane layers = new Pane();
			PlayerGUI player = new PlayerGUI();
			World world = new World();
			Label scoreLabel = new Label();
			ScoreManager gameScore = new ScoreManager();
			ArrayList<DataProvider> objects = world.getGame();
			objects.set(0, player);
			/*
			 * Create new text game
			 */

			TextGame tg = new TextGame(objects);
			tg.toPrint(); 
			/*
			 * Add the layers that contain each game object to root Pane.
			 */
			root.getChildren().add(renderGUI(world, layers));
			root.getChildren().add(scoreLabel);
			/*
			 * Start the score count and update the label when score changes using a Timeline.
			 */
			gameScore.start();
			Timeline scoreTimeline = new Timeline(new KeyFrame(Duration.seconds(0.0001), ev -> {
			scoreLabel.setText("Score: " + gameScore.gettime()/10);}));
			scoreTimeline.setCycleCount(Animation.INDEFINITE);
			scoreTimeline.play();

			
		    /**
		     * Timeline used to manage the constant generation of obstacles
		     */
			obstacleGenerationTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), ev -> {
			ObstacleGUI newObstacle = new ObstacleGUI().generate();
			ArrayList<Object> parameters = new ArrayList<>();
    		parameters.add(player);
    		parameters.add(world);
    		parameters.add(scoreTimeline);
    		parameters.add(newObstacle);
    		parameters.add(root);
    		parameters.add(tg);
			update(parameters);
			}));		
			obstacleGenerationTimeline.setCycleCount(Animation.INDEFINITE);
		    obstacleGenerationTimeline.play();
		/**
		 * SCENE
		 */
			scene = new Scene(root,DataProvider.getWINDOW_WIDTH(),DataProvider.getWINDOW_HEIGHT());
		/**
		 * EVENT HANDLER:
		 * If either the up-arrow key or space bar is clicked, then the player jumps
		 */
			handleInput(player, tg);
		/**
		 * STAGE
		 */
			setStage(primaryStage);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updateTextGame(TextGame textGame){
		for (int i = 0; i < 3; i++) 
			{
				textGame.updateByFrame();
			}
			textGame.toPrint();
	}


	public void update(ArrayList<Object> params){
		PlayerGUI player = (PlayerGUI) params.get(0);
		World world = (World) params.get(1);
		Timeline scoreTimeline = (Timeline) params.get(2);
		ObstacleGUI newObstacle = (ObstacleGUI) params.get(3);
		Pane root = (Pane) params.get(4);
		TextGame tg = (TextGame) params.get(5);
		updateTextGame(tg);

		if (player.checkCollision(world)) {
			obstacleGenerationTimeline.stop();
			scoreTimeline.stop();
			if(newObstacle != null)
				newObstacle.stop(true);
			root.getChildren().add(gameOver(world));
			System.out.print("Game Over");
			}
		
		if (newObstacle != null)	
		{
			root.getChildren().add(newObstacle.getLayer());
			world.add(newObstacle);
			tg.generateType(newObstacle.getIsBird());
		}
	}

	public void handleInput(PlayerGUI player, TextGame tg){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
		 @Override
		public void handle(KeyEvent event)
		{
		 if ((event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.UP) && (player.getY() > 195))
			{
				player.jump();
				player.setY(200);
				player.getSprite().setHitbox(player.getX(), 200);
				player.velocityFinal = DataProvider.getFINAL_VELOCITY();
				tg.jump();
			}
		}
	    });
	}

	public void setStage(Stage primaryStage)
	{
		primaryStage.setTitle("T-Rex Run");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * RENDER_GUI METHOD
	 * @param World from which to obtain the Game Objects.
	 * @param Pane to which the layers that contain each Game Object will be added.
	 * @return -Pane to which each Game Object has been added.
	 */
	public Pane renderGUI(World w, Pane toAdd)
	{		
		for(DataProvider object : w.getGame()) {
			toAdd.getChildren().add(object.getLayer());
		}
		return toAdd;
	}
	
	
	/**
	 * GAME OVER METHOD
	 * @param World w to access the world's ArrayList
	 * @return Pane endScreen that replaces the running game Pane
	 */
	public Pane gameOver(World w) {
		Pane endScreen = new Pane();
		((PlayerGUI) w.getGame().get(0)).end();
		((GroundGUI) w.getGame().get(1)).stopAnimation();
		Image endImage = new Image(getClass().getResourceAsStream(DataProvider.getGAME_OVER()));
		ImageView endImageView = new ImageView(endImage);
		//Want the 'GAME OVER' to be perfectly centered
		endImageView.setX(DataProvider.getWINDOW_WIDTH()/2 - endImage.getWidth()/2);
		endImageView.setY(DataProvider.getWINDOW_HEIGHT()/2 - endImage.getHeight()/2);
		endScreen.getChildren().add(endImageView);
		return endScreen;
	}
	

	/**
	 * MAIN METHOD
	 * Launches the GUIGame Application
	 */
	public static void main(String[] args) {
		launch();		
	}

	/**
	 * GETTER FOR SCENE
	 * @return scene shown on GUIGame stage
	 */
	public static Scene getScene() 
	{
		return scene;
	}

	/**
	 * OVERRIDE FOR KEY LISTENER METHODS
	 */
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
	}
}
