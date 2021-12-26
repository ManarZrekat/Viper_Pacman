package Controller;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.Random;

import Model.Difficulty;
import Model.GameMap;
import Model.Question;
import Model.SysData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopUpController{
    @FXML
    private Label QuestionText;
    @FXML
    private  RadioButton answer1;
    @FXML
    private RadioButton answer2;
    @FXML
    private  RadioButton answer3;
    @FXML
    private  RadioButton answer4;
    @FXML
    private Button submit;
    
	@FXML
    ToggleGroup group;
	private int correct_answer;
	private static boolean isCorrect;
	private static boolean isSubmit;
	private static  Difficulty level;
//	//private static Pane pane;
//	@FXML
//	//private static SplitPane content;
	@FXML
	private void submit(ActionEvent event) {
		System.out.println(correct_answer);
		isSubmit = true;
		
		 Stage stage = (Stage) submit.getScene().getWindow();
		 stage.close();
		 
		 if (this.answer1.isSelected() && correct_answer==1) {
			 System.out.println("a");
			 setCorrect(true);
		 }
		 else if (this.answer2.isSelected() && correct_answer==2) {
			 
			 System.out.println("a");
			 setCorrect(true);
		 }
		 else if (this.answer3.isSelected() && correct_answer==3) {
			 System.out.println("a");
			 setCorrect(true);
		 }
		 else if (this.answer4.isSelected() && correct_answer==4) {
			 System.out.println("a");
			 setCorrect(true);
		 }
		
		 else {
			 setCorrect(false);
		 }
		
	}
	
	public  void initialize() {
		isSubmit =false;
		Random rand = new Random();
	    int rand1 = rand.nextInt(14);
	    ObservableList<Question> questions  = SysData.getInstance().getQuestions();
	    Question q = questions.get(rand1);
	    correct_answer = q.getCorrectAnswer();
//	    System.out.println(q.getQuestionText());
	    String question = q.getQuestionText();
	    level = q.getLevel();
	   if(question != null) {
//		   System.out.println("************" + question);
		   this.QuestionText.setText(question);
		    if(QuestionText != null) {
		    	//System.out.println("***************!!!!!!!QUESTION TEXT!!!!!!!************" + QuestionText.getText());
		    	this.answer1.setText(q.getAnswers().get(0).getAnswerText());
			    this.answer2.setText(q.getAnswers().get(1).getAnswerText());
			    this.answer3.setText(q.getAnswers().get(2).getAnswerText());
			    this.answer4.setText(q.getAnswers().get(3).getAnswerText());
			    

		    }
	   }
	   

	   
	}

	public static boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public static boolean isSubmit() {
		return isSubmit;
	}

	public static void setSubmit(boolean isSubmit) {
		PopUpController.isSubmit = isSubmit;
	}

	public static Difficulty getLevel() {
		return level;
	}

	public static void setLevel(Difficulty level) {
		PopUpController.level = level;
	}
	}

	
//	public static void questionPopUp() {
//		pane = new Pane ();
//		 
//		// This is the content pane, here I am using a split pane but you can use any node.
//		content = new SplitPane ();
//		 
//		// The pref size of the content is bound to the actual size of the popup pane.
//		content.prefWidthProperty ().bind (pane.widthProperty ());
//		content.prefHeightProperty ().bind (pane.heightProperty ());
//		pane.getChildren ().add (content);
//		//get a random question 
//		Random rand = new Random();
//	    int rand1 = rand.nextInt(14);
//	    ObservableList<Question> questions  = SysData.getInstance().getQuestions();
//	    Question q = questions.get(rand1);
////	    System.out.println(q.getQuestionText());
//	    String question = q.getQuestionText();
//	   if(question != null) {
////		   System.out.println("************" + question);
//		   QuestionText.setText(question);
//		    if(QuestionText != null) {
//		    	System.out.println("***************!!!!!!!QUESTION TEXT!!!!!!!************" + QuestionText.getText());
//		    	answer1.setText(q.getAnswers().get(0).getAnswerText());
//			    answer2.setText(q.getAnswers().get(1).getAnswerText());
//			    answer3.setText(q.getAnswers().get(2).getAnswerText());
//			    answer4.setText(q.getAnswers().get(3).getAnswerText());
//			    
//			    FXMLLoader loader = new FXMLLoader();
//			    try {
//					loader = FXMLLoader.load(PopUpController.class.getResource("/View/PopUp.fxml"));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			    Parent root;
//				try {
//					root = loader.load();
//			        Stage stage = new Stage();
//			        stage.setScene(new Scene(root));
//			        stage.initStyle(StageStyle.UNDECORATED);
//			        stage.show();
//			        root.requestFocus();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			    
//
//			 	Scene scene = new Scene(pane);
////			 	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//			 	Stage stage = new Stage();
//			 	stage.setScene(scene);
//			 	stage.setTitle("Question");
//			 	stage.show();
//			    
//			    
//				GameMap.setQuestion(false); 
//		    }
//	   }
//		
//	}
	
	   

