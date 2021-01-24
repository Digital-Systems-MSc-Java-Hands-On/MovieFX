package gr.unipi.MovieFX;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {
		// Flow Pane (root node)
		FlowPane rootFlowPane;
		// Main scene buttons
		Button movieBtn, tvBtn, peopleBtn;

	public MainSceneCreator(double width, double height) {
		super(width, height);
		rootFlowPane = new FlowPane();
		movieBtn = new Button("Movie");
		tvBtn = new Button("TV");
		peopleBtn = new Button("People");
		
		//attach handle event to movieBtn
		movieBtn.setOnMouseClicked(this);
		
		//setup flow pane
		rootFlowPane.setHgap(10);
		rootFlowPane.setAlignment(Pos.CENTER);
		
		movieBtn.setMinSize(120, 30);
		tvBtn.setMinSize(120, 30);
		peopleBtn.setMinSize(120, 30);
		
		//add buttons to rootflowpane
		rootFlowPane.getChildren().add(movieBtn);
		rootFlowPane.getChildren().add(tvBtn);
		rootFlowPane.getChildren().add(peopleBtn);
	}

	@Override
	Scene createScene() {
		return new Scene(rootFlowPane, width, height);
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (event.getSource() == movieBtn) {
			App.primaryStage.setTitle("Movie Window");
			App.primaryStage.setScene(App.movieScene);
		}
		
	}

	
}
