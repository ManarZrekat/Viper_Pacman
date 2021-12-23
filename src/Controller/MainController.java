package Controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


public class MainController {
	private MediaView media;
	private MediaPlayer mediaplayer;
	private static final String click = "click.wav";
	 private String dir = new String(System.getProperty("user.dir"));
	
	@FXML
	Button startGameBtn, lastGamesBtn, questionsBtn;
	@FXML
	private void LastGames(ActionEvent event) throws IOException {
		music();
		Parent pane = FXMLLoader.load(getClass().getResource("/View/LastGames.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Last Games Screen");
		stage.show();
	}
	@FXML
	private void startGame(ActionEvent event) throws IOException {
		music();
		@SuppressWarnings("unused")
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
		music();
		Parent pane = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Questions Screen");
		stage.show();
	}
	private void music() throws IOException {
//		String loc = new String(System.getProperty("user.dir")+"//click.wav");
//        File file = new File(loc);
//       
//        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
//        
//		AudioClip note = new AudioClip(content);
//		note.play();
		Media media = new Media(Paths.get("click.wav").toUri().toString());  
		MediaPlayer mediaPlayer = new MediaPlayer(media);  
		mediaPlayer.setAutoPlay(true);  
	}

}
