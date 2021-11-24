package Controller;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MainController {
	
	@FXML
	Button startGameBtn, lastGamesBtn, questionsBtn;
	@FXML
	private void LastGames(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/View/LastGames.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Last Games Screen");
		stage.show();
	}
	@FXML
	private void startGame(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		Parent pane = FXMLLoader.load(getClass().getResource("/View/StartGame.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Start Game Screen");
		stage.show();
	}
	@FXML
	private void questions(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Questions Screen");
		stage.show();
	}

}
