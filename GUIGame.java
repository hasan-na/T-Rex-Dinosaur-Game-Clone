import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class GUIGame extends Application {
	
	static Scene scene;
	Pane root;
	static World world = new World();
	static PlayerGUI player = new PlayerGUI();
	Obstacle obstacle = new Obstacle();
//	Ground ground = new Ground();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new Pane();
			
			//Add player, ground and obstacle layers.
			root.getChildren().add(player.getLayer(player));
			root.getChildren().add(obstacle.getLayer());
//			root.getChildren().add(ground.getLayer());
			
			scene = new Scene(root,DataProvider.getWINDOW_WIDTH(),DataProvider.getWINDOW_HEIGHT());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void renderGUI()
//	{
//		
//	}
	
	public static void main(String[] args) {
		launch();
		while(!player.checkCollision())
		{
			player.processInput(getScene());
			world.update();
//			renderGUI();
		}
	}

	public static Scene getScene() 
	{
		return scene;
	}

}
