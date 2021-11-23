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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddQuestionController implements Initializable {
	Question selected;
	ObservableList<Question> questions;
	ObservableList<Answer> answers = FXCollections.observableArrayList();
	@FXML private Button addAnswerBtn, addQuestionBtn, backBtn;
	@FXML private TableView<Answer> tableAnswers;
	@FXML private TableColumn<Answer, String> answer;
	@FXML private TableColumn<Answer, Boolean> isCorrect;
	@FXML private TableColumn<Answer, Integer> id;
	@FXML private TextField question, newanswer;
	@FXML private ComboBox<Difficulty> diffLevel;
	@FXML private RadioButton trueBtn, falseBtn;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		questions = FXCollections.observableArrayList(SysData.getInstance().getQuestions());
		//answers = FXCollections.observableArrayList();
		ObservableList<Difficulty> levels = FXCollections.observableArrayList();
		levels.addAll(Difficulty.easy, Difficulty.meduim, Difficulty.difficult);
		diffLevel.setItems(levels);
		addQuestionBtn.setDisable(true);
		answer.setCellValueFactory(new PropertyValueFactory<Answer, String>("answerText"));
		isCorrect.setCellValueFactory(new PropertyValueFactory<Answer, Boolean>("isCorrect"));
		id.setCellValueFactory(new PropertyValueFactory<Answer, Integer>("id"));
		tableAnswers.setItems(answers);
		checkAnswers();
		
	}
	private void checkAnswers() {
		if (answers.size() ==4) {
			addQuestionBtn.setDisable(false);
			addAnswerBtn.setDisable(true);
		}
	}
	@FXML
	private void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/View/Questions.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
//		QuestionController control = loader.getController();
//		control.initData(questions);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("Questions Manager");
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void addQuestion() {
		Question q =new Question(question.getText(), diffLevel.getValue(), answers); 
		SysData.getInstance().getQuestions().add(q);
		//questions.add(q);
		question.clear();
		diffLevel.setValue(null);
		backBtn.fire();
	}
	@FXML
	private void addAnswer(ActionEvent event) throws IOException {
//		if (trueBtn.isPressed())
//			answers.add(new Answer(newanswer.getText(), true));
//		else
//			answers.add(new Answer(newanswer.getText(), false));
		answers.add(new Answer(answers.size()+1, newanswer.getText(), trueBtn.isSelected()));
		newanswer.clear();
		checkAnswers();
		
		
	}
	public void initData(Question q) {
		selected =q;
		tableAnswers.setItems(q.getAnswers());
		question.setText(q.getQuestionText());
		diffLevel.setValue(q.getLevel());
	}
	

}
