package Controller;

import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;

import Model.Answer;
import Model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditAnswerController  {

	private Question question;
	private Answer editAnswer;
	@FXML private TextField answer;
	@FXML private Label id;
	@FXML RadioButton trueBtn, falseBtn;
	@FXML Button saveBtn;
	
	
	public void initData(Question q, Answer a) {
			this.editAnswer = a;
			this.question = q;
			answer.setText(a.getAnswerText());
			trueBtn.setSelected(a.getIsCorrect());
			id.setText(String.valueOf(a.getId()));
	}
	@FXML
	private void save(ActionEvent event) throws IOException {
		editAnswer.setAnswerText(answer.getText());
		editAnswer.setIsCorrect(trueBtn.isSelected());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/View/EditQuestion.fxml"));
		Parent root =loader.load();
		EditQuestionController control = loader.getController();
		control.initData(question);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setTitle("Edit question "+question);
		stage.setScene(scene);
		stage.show();
	}

}
