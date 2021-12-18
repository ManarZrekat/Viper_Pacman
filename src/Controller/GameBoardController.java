package Controller;


import java.io.IOException;

import Model.Answer;
import Model.Difficulty;
import Model.Question;
import Model.Score;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameBoardController {
	@FXML
	Button backBtn;
	@FXML
	private void backToMain(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Main Screen");
		stage.show();
	}
	
	public void eatingDot(){
		scoreChange(1);
	}
	
	public void questionAnswered(Answer questionAnswer){
		SimpleIntegerProperty questionID = questionAnswer.getQuestionID();
		if(questionID != null) {
			Question question = new Question(questionID);
			if(question.getLevel().equals(Difficulty.easy)) {
				if(questionAnswer.getIsCorrect())
					scoreChange(1);
				else scoreChange(-10);
			}
			if(question.getLevel().equals(Difficulty.meduim)) {
				if(questionAnswer.getIsCorrect())
					scoreChange(2);
				else scoreChange(-20);
			}
			if(question.getLevel().equals(Difficulty.difficult)) {
				if(questionAnswer.getIsCorrect())
					scoreChange(3);
				else scoreChange(-30);
			}
		}
	}
	
	public int scoreChange(int score) {
		int currentScore = Score.getScore();
		currentScore += score;
		return currentScore;
	}
	
	public int level(int score) {
		if(0 <= score && score <= 50)
			return 1;
		if(51 <= score && score <= 100)
			return 2;
		if(101 <= score && score <= 150)
			return 3;
		return 4;
	}
	
	
	/*public int lives(int lives) {
		Player
	}*/
	
	public void startGame() {
		
	}

}
