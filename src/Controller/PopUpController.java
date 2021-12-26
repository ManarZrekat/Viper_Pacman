package Controller;

import java.io.IOException;
import java.util.Random;

import Model.GameMap;
import Model.Question;
import Model.SysData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopUpController {
    @FXML
    private static Label QuestionText;
    @FXML
    private static RadioButton answer1;
    @FXML
    private static RadioButton answer2;
    @FXML
    private static RadioButton answer3;
    @FXML
    private static RadioButton answer4;
    @FXML
    private Button submit;
	@FXML
	private static Pane pane;
	@FXML
	private static SplitPane content;
	
	public static void questionPopUp() {
		pane = new Pane ();
		 
		// This is the content pane, here I am using a split pane but you can use any node.
		content = new SplitPane ();
		 
		// The pref size of the content is bound to the actual size of the popup pane.
		content.prefWidthProperty ().bind (pane.widthProperty ());
		content.prefHeightProperty ().bind (pane.heightProperty ());
		pane.getChildren ().add (content);
		//get a random question 
		Random rand = new Random();
	    int rand1 = rand.nextInt(14);
	    ObservableList<Question> questions  = SysData.getInstance().getQuestions();
	    Question q = questions.get(rand1);
//	    System.out.println(q.getQuestionText());
	    String question = q.getQuestionText();
	   if(question != null) {
//		   System.out.println("************" + question);
		   QuestionText.setText(question);
		    if(QuestionText != null) {
		    	System.out.println("***************!!!!!!!QUESTION TEXT!!!!!!!************" + QuestionText.getText());
		    	answer1.setText(q.getAnswers().get(0).getAnswerText());
			    answer2.setText(q.getAnswers().get(1).getAnswerText());
			    answer3.setText(q.getAnswers().get(2).getAnswerText());
			    answer4.setText(q.getAnswers().get(3).getAnswerText());
			    
			    FXMLLoader loader = new FXMLLoader();
			    try {
					loader = FXMLLoader.load(PopUpController.class.getResource("/View/PopUp.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    Parent root;
				try {
					root = loader.load();
			        Stage stage = new Stage();
			        stage.setScene(new Scene(root));
			        stage.initStyle(StageStyle.UNDECORATED);
			        stage.show();
			        root.requestFocus();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			    

			 	Scene scene = new Scene(pane);
//			 	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			 	Stage stage = new Stage();
			 	stage.setScene(scene);
			 	stage.setTitle("Question");
			 	stage.show();
			    
			    
				GameMap.setQuestion(false); 
		    }
	   }
		
	}
	
	
}
