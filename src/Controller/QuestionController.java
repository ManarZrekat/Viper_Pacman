package Controller;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class QuestionController implements Initializable {
	
	ObservableList<Question> questions;
	@FXML
	private TableView<Question> tableQuestions;
	@FXML
	private TableColumn<Question, Integer> id;
	@FXML
	private TableColumn<Question, String> question;
	@FXML
	private TableColumn<Question, Difficulty> difficulty;
	@FXML
	Button addBtn, deleteBtn, editBtn, backBtn;
	@FXML
	private void backToMain(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Main Screen");
		stage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		questions  = FXCollections.observableArrayList(SysData.getInstance().getQuestions());
		id.setCellValueFactory(new PropertyValueFactory<Question, Integer>("id"));
		question.setCellValueFactory(new PropertyValueFactory<Question, String>("questionText"));
		difficulty.setCellValueFactory(new PropertyValueFactory<Question, Difficulty>("level"));
		tableQuestions.setItems(questions);
		System.out.println("Questions: "+questions);
		
	}
	@FXML
	private void addQuestion(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/View/AddQuestion.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("Add Question");
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void deleteQuestion() {
		SysData.getInstance().getQuestions().remove(tableQuestions.getSelectionModel().getSelectedItem());
		questions.remove(tableQuestions.getSelectionModel().getSelectedItem());
		System.out.println("Questions after delete: "+SysData.getInstance().getQuestions());
		
	}
	@FXML
	private void editQuestion(ActionEvent event) throws IOException {
		System.out.println("Selected question: "+tableQuestions.getSelectionModel().getSelectedItem());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/View/EditQuestion.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		EditQuestionController control = loader.getController();
		control.initData(tableQuestions.getSelectionModel().getSelectedItem());
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Edit Question "+tableQuestions.getSelectionModel().getSelectedItem().getQuestionText());
		stage.show();
	}
//	public void initData(ObservableList<Question> list) {
//		questions = list;
//	}

}
