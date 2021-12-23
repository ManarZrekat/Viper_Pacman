package Controller;

import java.io.IOException;

import Model.GameHistory;
import Model.SysData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LastGamesController {
	@FXML
	Button backBtn;
	@FXML
	private ListView<GameHistory> listView;
	@FXML
	private void backToMain(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Main Screen");
		stage.show();
	}
	public void initialize() {
		this.listView.setItems(SysData.getInstance().getHistory()); 
		
	}
	
	 

}
