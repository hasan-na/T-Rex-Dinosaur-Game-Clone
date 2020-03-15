import java.awt.Label;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;



public class GUIGame extends Application implements KeyListener {
	

	static Scene scene;
	
	@Override
	public void start(Stage primaryStage) {		
		try {
			 Pane root = new Pane();
			 Pane layers = new Pane();
			 PlayerGUI player = new PlayerGUI();
//			 World world = new World();
			 
		 
//				while(!player.checkCollision())
//				{
//					world.update();
					//root.getChildren().add(renderGUI(w, layers, player));
//				}
			 
					
		//SCENE
			scene = new Scene(root,DataProvider.getWINDOW_WIDTH(),DataProvider.getWINDOW_HEIGHT());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		//EVENT HANDLER
			 scene.setOnKeyPressed(new EventHandler<KeyEvent>()
				{
					@Override
					public void handle(KeyEvent event)
					{
						if (event.getCode() == KeyCode.UP)
						{
							player.jump();
							
						}
					}
				});
		//STAGE
			//Give the stage a title.
			primaryStage.setTitle("T-Rex Run");
			//Set the scene on primary stage and show stage.
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pane renderGUI(World w, Pane toAdd, PlayerGUI player)
	{
//		world.getGame().forEach((object)-> toAdd.getChildren().add(object.getLayer()));
		for(GameObject object : w.getGame()) {
			toAdd.getChildren().add(object.getLayer());
		}
		return toAdd;
	}
	
	public static void main(String[] args) {
		launch();		
	}
	
	public void main() {
		World w = new World();
		ArrayList<GameObject> game = w.getGame();
		game.set(0, new PlayerGUI());
		
		PlayerGUI p = new PlayerGUI();
		while(!p.checkCollision()) {
			w.update();
			renderGUI(w, p.getLayer(), p);
		}
	}

	public static Scene getScene() 
	{
		return scene;
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
