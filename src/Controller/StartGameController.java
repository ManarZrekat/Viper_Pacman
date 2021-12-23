package Controller;

import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;

public class StartGameController {
	@FXML
	Button backBtn, enterBtn;
	@FXML
	TextField playerName;
	@FXML
	private void backToMain(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Main Screen");
		stage.show();
	}
	@FXML
	private void nameEntered(ActionEvent event) throws IOException {
		String name = playerName.getText();
		if(!name.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("player name entered: "+name);
			alert.show();
			BorderPane pane = FXMLLoader.load(getClass().getResource("/View/GameMap.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GameMap.fxml"));
			Parent root = loader.load();
			GameBoardController controller = loader.getController();
			
			   scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				   
				    public void handle(KeyEvent ke) {
				    	//System.out.println("key "+ke);
				        controller.handle(ke);
				    }
				});
			stage.setScene(scene);
			stage.setTitle("Game Screen");
			stage.show();

			
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("No player name");
			alert.setContentText("Must enter a player name");
			alert.show();
		}
	}

}
