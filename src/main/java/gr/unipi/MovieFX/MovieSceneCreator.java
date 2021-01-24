package gr.unipi.MovieFX;

import java.util.ArrayList;
import java.util.List;

import gr.unipi.movieapi.model.MovieInfo;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class MovieSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {

	// List of movies
	ArrayList<MovieInfo> movieList;
	// Flow pane
	FlowPane buttonFlowPane;
	// Grid Panes
	GridPane rootGridPane, inputFieldsPane;
	// second scene buttons
	Button newMovieBtn, updateMovieBtn, deleteMovieBtn, backBtn;
	// second scene labels
	Label titleLbl, descriptionLbl, ratingLbl, releaseDateLbl;
	// second scene text fields
	TextField titleField, descriptionField, ratingField, releaseDateField;
	// Table View
	TableView<MovieInfo> movieTableView;

	public MovieSceneCreator(double width, double height) {
		super(width, height);
		// initialize things
		movieList = new ArrayList<MovieInfo>();
		rootGridPane = new GridPane();
		buttonFlowPane = new FlowPane();

		titleLbl = new Label("Title: ");
		descriptionLbl = new Label("Description: ");
		ratingLbl = new Label("Rating: ");
		releaseDateLbl = new Label("Release Date: ");

		titleField = new TextField();
		descriptionField = new TextField();
		ratingField = new TextField();
		releaseDateField = new TextField();

		newMovieBtn = new Button("New Movie");
		updateMovieBtn = new Button("Update Movie");
		deleteMovieBtn = new Button("Delete Movie");
		backBtn = new Button("Go Back");

		inputFieldsPane = new GridPane();
		movieTableView = new TableView<MovieInfo>();

		// Customize Flow Pane
		buttonFlowPane.setHgap(10);
		buttonFlowPane.setAlignment(Pos.CENTER);
		buttonFlowPane.getChildren().add(newMovieBtn);
		buttonFlowPane.getChildren().add(updateMovieBtn);
		buttonFlowPane.getChildren().add(deleteMovieBtn);
//		buttonFlowPane.getChildren().add(backBtn);

		// Customize input field Grid Pane
		inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
		inputFieldsPane.setVgap(10);
		inputFieldsPane.setHgap(10);
		inputFieldsPane.add(titleLbl, 0, 0);
		inputFieldsPane.add(titleField, 1, 0);
		inputFieldsPane.add(descriptionLbl, 0, 1);
		inputFieldsPane.add(descriptionField, 1, 1);
		inputFieldsPane.add(ratingLbl, 0, 2);
		inputFieldsPane.add(ratingField, 1, 2);
		inputFieldsPane.add(releaseDateLbl, 0, 3);
		inputFieldsPane.add(releaseDateField, 1, 3);

		// Customize Root Grid Pane
		rootGridPane.setVgap(10);
		rootGridPane.setHgap(10);
		rootGridPane.add(inputFieldsPane, 1, 0);
		rootGridPane.add(movieTableView, 0, 0);
		rootGridPane.add(buttonFlowPane, 0, 1);
		rootGridPane.add(backBtn, 1, 1);

		// Customize TableView
		TableColumn<MovieInfo, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		movieTableView.getColumns().add(titleColumn);

		TableColumn<MovieInfo, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		movieTableView.getColumns().add(descriptionColumn);

		TableColumn<MovieInfo, String> ratingColumn = new TableColumn<>("Rating");
		ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
		movieTableView.getColumns().add(ratingColumn);

		TableColumn<MovieInfo, String> releaseDateColumn = new TableColumn<>("Release Date");
		releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));
		movieTableView.getColumns().add(releaseDateColumn);

		// Attach events
		backBtn.setOnMouseClicked(this);
		newMovieBtn.setOnMouseClicked(this);
		updateMovieBtn.setOnMouseClicked(this);
		deleteMovieBtn.setOnMouseClicked(this);
		movieTableView.setOnMouseClicked(this);

	}

	@Override
	Scene createScene() {
		return new Scene(rootGridPane, width, height);
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getSource() == backBtn) {
			App.primaryStage.setTitle("MainFX Window");
			App.primaryStage.setScene(App.mainScene);
		} else if (event.getSource() == newMovieBtn) {
			String title = titleField.getText();
			String description = descriptionField.getText();
			String rating = ratingField.getText();
			String releaseDate = releaseDateField.getText();

			createMovie(title, description, rating, releaseDate);
			tableSync();
			clearTextFields();

		} else if (event.getSource() == updateMovieBtn) {
			String title = titleField.getText();
			String description = descriptionField.getText();
			String rating = ratingField.getText();
			String releaseDate = releaseDateField.getText();
			
			updateMovie(title, description, rating, releaseDate);
			
			tableSync();
			clearTextFields();
		} else if (event.getSource() == deleteMovieBtn) {
			deleteMovie(titleField.getText());
			tableSync();
			clearTextFields();
		} else if (event.getSource() == movieTableView ) {
			MovieInfo selectedMovie = movieTableView.getSelectionModel().getSelectedItem();
			if (selectedMovie != null) {
				titleField.setText(selectedMovie.getTitle());
				descriptionField.setText(selectedMovie.getDescription());
				ratingField.setText(selectedMovie.getRating());
				releaseDateField.setText(selectedMovie.getRelease_date());
			}
		}
		
	}

	public void createMovie(String title, String description, String rating, String releaseDate) {
		MovieInfo movie = new MovieInfo(title, description, rating, releaseDate);
		movieList.add(movie);
	}
	
	public void updateMovie(String title, String description, String rating, String releaseDate) {
		for (MovieInfo movie : movieList) {
			if (movie.getTitle().equals(title)) {
				movie.setDescription(description);
				movie.setRating(rating);
				movie.setRelease_date(releaseDate);
			}
		}
	}
	
	public void deleteMovie(String title) {
		for (int i = 0; i < movieList.size(); i ++) {
			if (movieList.get(i).getTitle().equals(title)) {
				movieList.remove(i);
				break;
			}
		}
	}

	public void tableSync() {
		List<MovieInfo> items = movieTableView.getItems();
		items.clear();

		for (MovieInfo movie : movieList) {
			if (movie instanceof MovieInfo) {
				items.add(movie);
			}
		}

	}
	
	public void clearTextFields() {
		titleField.setText("");
		descriptionField.setText("");
		ratingField.setText("");
		releaseDateField.setText("");
	}

}
