package gr.unipi.MovieFX;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	// Stage
	static Stage primaryStage;
	// Scenes
	static Scene mainScene, movieScene;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		SceneCreator mainSceneCreator = new MainSceneCreator(650, 300);
		mainScene = mainSceneCreator.createScene();
		SceneCreator movieSceneCreator = new MovieSceneCreator(650, 300);
		movieScene = movieSceneCreator.createScene();
		
		primaryStage.setTitle("MainFX Window");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	

}