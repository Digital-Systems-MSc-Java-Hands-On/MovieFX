package gr.unipi.MovieFX;

import javafx.scene.Scene;

public abstract class SceneCreator {
	double width;
	double height;
	
	public SceneCreator(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	abstract Scene createScene();
	
}
