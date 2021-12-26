package Controller;


import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Model.Answer;
import Model.Difficulty;
import Model.Question;
import Model.Score;
import Model.GameMap;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import View.PacManView;
import Model.*;

public class GameBoardController {
     public static double FRAMES_PER_SECOND =3.0;

    @FXML 
    private Label scoreLabel;
    @FXML 
    private Label levelLabel;
    @FXML
    private Label gameOverLabel;
    @FXML 
    PacManView pacManView;
    @FXML
    private Label highestScore;
    @FXML
    Label LivesCount;
//    @FXML
//    private Label QuestionText;
//    @FXML
//    private RadioButton answer1;
//    @FXML
//    private RadioButton answer2;
//    @FXML
//    private RadioButton answer3;
//    @FXML
//    private RadioButton answer4;
//    @FXML
//    private Button submit;
    
    
    
    private static GameMap GameMap;
    private String dir = new String(System.getProperty("user.dir"));
    private static final String[] levelFiles = {"levels//level1.txt", "levels//level2.txt", "levels//level3.txt"};

    private Timer timer;
    private static int ghostEatingModeCounter;
    private boolean paused;
    private String Name;
    
    public void getPlayerName(String name) {
    	//StartGameController c = new StartGameController();
    	this.Name = name;
    	System.out.println(Name);
    }
    

    public GameBoardController() {
        this.paused = false;
    }
	@FXML
	Button backBtn;
	@FXML
	private Pane pane;
	private SplitPane content;
//	private void backToMain(ActionEvent event) throws IOException {
//		AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
//		Scene scene = new Scene(pane);
//		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//		stage.setScene(scene);
//		stage.setTitle("Main Screen");
//		stage.show();
//	}
	
//	public void eatingDot(){
//		scoreChange(1);
//	}
	
	public static int questionAnswered(Answer questionAnswer){
		int currentScore;
		SimpleIntegerProperty questionID = questionAnswer.getQuestionID();
		if(questionID != null) {
			Question question = new Question(questionID);
			if(question.getLevel().equals(Difficulty.easy)) {
				if(questionAnswer.getIsCorrect())
					return scoreChange(1);
				else return scoreChange(-10);
			}
			if(question.getLevel().equals(Difficulty.medium)) {
				if(questionAnswer.getIsCorrect())
					return scoreChange(2);
				else return scoreChange(-20);
			}
			if(question.getLevel().equals(Difficulty.difficult)) {
				if(questionAnswer.getIsCorrect())
					return scoreChange(3);
				else return scoreChange(-30);
			}
		}
		return 0;
	}
	
	public static int scoreChange(int score) {
			if(51 == score)
				GameMap.openPortal();
			if(101==score) {
				GameMap.closePortal();
				FRAMES_PER_SECOND = 10.0;
				//speed
				//this.startTimer();
			}
			if(200==score) {
				GameMap.GameWon();
				
			}
				
				

		return score;
	}
	
//	public int level(int score) {
//		if(0 <= score && score <= 50)
//			return 1;
//		if(51 <= score && score <= 100)
//			return 2;
//		if(101 <= score && score <= 150)
//			return 3;
//		return 4;
//	}
	
	
	/*public int lives(int lives) {
		Player
	}*/
	
//	public void startGame() {
//		
//	}
	
    /**
     * Initialize and update the model and view from the first txt file and starts the timer.
     */
    public void initialize() {
    	//System.out.println(pacManView);
        String file = GameBoardController.getLevelFile(0);
        this.GameMap = new GameMap();
        //GameMap.initializeLevel(file);
        //this.update(GameMap.Direction.None);
        this.update(GameMap.intToDirection(-1));
        ghostEatingModeCounter = 25;
        this.startTimer();
        this.highestScore.setText(String.format("%d",SysData.getInstance().getHighestScore()));

    }

    /**
     * Schedules the model to update based on the timer.
     */
    private void startTimer() {
    	
        this.timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        update(GameMap.getCurrentDirection());
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }
    private void AddScoreToHistory(int score) {
    	System.out.println("score "+score + " name "+this.Name);
        if (this.Name!=null) {
            GameHistory gh = new GameHistory(this.Name,score);
            if(!SysData.getInstance().getHistory().contains(gh)) {
    			SysData.getInstance().getHistory().add(gh);
    		}
            SysData.getInstance().writeHistory();
        }

    }

    /**
     * Steps the PacManModel, updates the view, updates score and level, displays Game Over/You Won, and instructions of how to play
     * @param direction the most recently inputted direction for PacMan to move in
     * @throws IOException 
     */
    private void update(GameMap.Direction direction) {
    	//System.out.println(direction);
    	
        this.GameMap.step(direction);
        this.pacManView.update(GameMap);
        scoreChange(this.GameMap.getScore());
        this.scoreLabel.setText(String.format("Score: %d", this.GameMap.getScore()));
        this.levelLabel.setText(String.format("Level: %d", this.GameMap.getLevel()));
        if (GameMap.isGameOver()) {
        	//TODO
            this.gameOverLabel.setText(String.format("GAME OVER"));
            pause();
          //add score to score history
            AddScoreToHistory(this.GameMap.getScore());
        }
        if (GameMap.isYouWon()) {
            this.gameOverLabel.setText(String.format("YOU WON!"));
            AddScoreToHistory(this.GameMap.getScore());
        }
        
        //when PacMan is in ghostEatingMode, count down the ghostEatingModeCounter to reset ghostEatingMode to false when the counter is 0
        if (GameMap.isGhostEatingMode()) {
            ghostEatingModeCounter--;
        }
        
        if(GameMap.isQuestion()) {
        	//present a question
        	//FXMLLoader loader = new FXMLLoader();
		     pause();
		     GameMap.setQuestion(false);
		    AnchorPane loader;
			try {
				loader = FXMLLoader.load(getClass().getResource("/View/PopUp.fxml"));
				Scene scene = new Scene(loader);
		        Stage stage = new Stage();
		        stage.setScene(scene);
		        stage.initStyle(StageStyle.UNDECORATED);
		        stage.show();
		        //root.requestFocus();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    AnchorPane root;
		    
			
				//loader.setLocation(getClass().getResource("/View/PopUp.fxml"));
				 //pane = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
				//root = loader.load();


	
        	}
        
        if (ghostEatingModeCounter == 0 && Model.GameMap.isGhostEatingMode()) {
        	GameMap.setGhostEatingMode(false);
        }
        if (PopUpController.isSubmit()) {
			//TODO
		    // check if value of PopUpController.isCorrect() is true and update the score accordingly 
			//call update score 
			Difficulty level = PopUpController.getLevel();
			int questionScore;
			int currentScore = GameMap.getScore();
//		    System.out.println("correct answr:"+ PopUpController.isCorrect());
		    //call method and send the values level and PopUpController.isCorrect()
		    questionScore = questionAnswered(PopUpController.isCorrect(), level);
		    GameMap.setScore(currentScore+questionScore);
		    PopUpController.setSubmit(false);
		    unpause();
			
		}
    }
    
	public static int questionAnswered(Boolean isCorrect, Difficulty level){
			if(level.equals(Difficulty.easy)) {
				if(isCorrect)
					return 1;
				else return -10;
			}
			if(level.equals(Difficulty.medium)) {
				if(isCorrect)
					return 2;
				else return -20;
			}
			if(level.equals(Difficulty.difficult)) {
				if(isCorrect)
					return 3;
				else return -30;
			}
		return 0;
	}

    /**
     * Takes in user keyboard input to control the movement of PacMan and start new games
     * @param keyEvent user's key click
     */
    public void handle(KeyEvent keyEvent) {
    	//System.out.println("keys");
        boolean keyRecognized = true;
        KeyCode code = keyEvent.getCode();
        GameMap.Direction direction = GameMap.intToDirection(-1);
        if (code == KeyCode.LEFT) {
            direction = GameMap.intToDirection(0);
        } else if (code == KeyCode.RIGHT) {
            direction = GameMap.intToDirection(1);
        } else if (code == KeyCode.UP) {
            direction = GameMap.intToDirection(2);
        } else if (code == KeyCode.DOWN) {
            direction = GameMap.intToDirection(3);
        } else if (code == KeyCode.G) {
        	//System.out.println("pause");
            pause();
            this.GameMap.startNewGame();
            this.gameOverLabel.setText(String.format(""));
            paused = false;
            this.startTimer();
        } else {
            keyRecognized = false;
        }
        if (keyRecognized) {
            keyEvent.consume();
            GameMap.setCurrentDirection(direction);
        }
    }

    /**
     * Pause the timer
     */
    // timer pause
    public void pause() {
            this.timer.cancel();
            this.paused = true;
    }
    
    public void unpause() {
    	this.timer.purge();
    	startTimer();
    	this.paused = false;
    }

    public double getBoardWidth() {
        return PacManView.CELL_WIDTH * this.pacManView.getColumnCount();
    }

    public double getBoardHeight() {
        return PacManView.CELL_WIDTH * this.pacManView.getRowCount();
    }

    public static void setGhostEatingModeCounter() {
        ghostEatingModeCounter = 25;
    }

    public static int getGhostEatingModeCounter() {
        return ghostEatingModeCounter;
    }

    public static String getLevelFile(int x)
    {
        return levelFiles[x];
    }

    public boolean getPaused() {
        return paused;
    }

}
