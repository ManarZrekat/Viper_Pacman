package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Answer;
import Model.Difficulty;
import Model.Question;
import Model.SysData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EditQuestionController implements Initializable {
	ObservableList<Difficulty> levels = FXCollections.observableArrayList();
	ObservableList<Answer> answers;
	Question selectedQuestion;
	@FXML private Button backBtn, saveBtn, editAnswerBtn;
	@FXML private TextField question;
	@FXML private ComboBox<Difficulty> diffLevel;
	@FXML private TableView<Answer> tableAnswers;
	@FXML private TableColumn<Answer, String> answer;
	@FXML private TableColumn<Answer, Boolean> isCorrect;
	@FXML private TableColumn<Answer, Integer> id;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		levels.addAll(Difficulty.easy, Difficulty.meduim, Difficulty.difficult);
//		diffLevel.setItems(levels);
//		answer.setCellValueFactory(new PropertyValueFactory<Answer, String>("answerText"));
//		correct.setCellValueFactory(new PropertyValueFactory<Answer, Boolean>("isCoreect"));
//		tableAnswers.setItems(answers);
		//answers = FXCollections.observableArrayList(selectedQuestion.getAnswers());
//		question.setText(selectedQuestion.getQuestionText());
//		diffLevel.setValue(selectedQuestion.getLevel());
		
	}
	public void initData(Question q) {
		this.selectedQuestion = q;
		question.setText(q.getQuestionText());
		diffLevel.setValue(q.getLevel());
		answers = FXCollections.observableArrayList(selectedQuestion.getAnswers());
		levels.addAll(Difficulty.easy, Difficulty.meduim, Difficulty.difficult);
		diffLevel.setItems(levels);
		answer.setCellValueFactory(new PropertyValueFactory<Answer, String>("answerText"));
		isCorrect.setCellValueFactory(new PropertyValueFactory<Answer, Boolean>("isCorrect"));
		id.setCellValueFactory(new PropertyValueFactory<Answer, Integer>("id"));
		tableAnswers.setItems(answers);
	}
	@FXML
	private void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/View/Questions.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setTitle("Questions Manager");
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void save() {
		if (!question.getText().equals(selectedQuestion.getQuestionText()))
			selectedQuestion.setQuestionText(question.getText());
		if (!diffLevel.getValue().equals(selectedQuestion.getLevel()))
			selectedQuestion.setLevel(diffLevel.getValue());
		
	}
	@FXML
	private void editAnswer(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/View/EditAnswer.fxml"));
		Parent root = loader.load();
		EditAnswerController control = loader.getController();
		control.initData(selectedQuestion, tableAnswers.getSelectionModel().getSelectedItem());
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setTitle("Edit answer");
		stage.setScene(scene);
		stage.show();
	}

}
