package View;

import Controller.GameBoardController;
import Model.GameMap;
import Model.Player;

import Model.GameMap.CellValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PacManView extends Group{
    public final static double CELL_WIDTH = 20.0;

    @FXML private int rowCount;
    @FXML private int columnCount;
    private ImageView[][] cellViews;
    private Image pacmanRightImage;
    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image ClydeImage;
    private Image PinkyImage;
    private Image InkyImage;
    private Image wallImage;
    private Image bombImage;
    private Image smallDotImage;
    private Image mazeImage;
    private Image blueGhost;
    private Image pacLife;
	//private final Map map;
    
    public PacManView() {
        this.pacmanRightImage = new Image(getClass().getResourceAsStream("/Images/pacRight.gif"));
        this.pacmanUpImage = new Image(getClass().getResourceAsStream("/Images/pacUp.gif"));
        this.pacmanDownImage = new Image(getClass().getResourceAsStream("/Images/pacDown.gif"));
        this.pacmanLeftImage = new Image(getClass().getResourceAsStream("/Images/pacLeft.gif"));
        this.ClydeImage = new Image(getClass().getResourceAsStream("/Images/Clyde.gif"));
        this.PinkyImage = new Image(getClass().getResourceAsStream("/Images/Pinky.gif"));
        this.InkyImage = new Image(getClass().getResourceAsStream("/Images/Inky.gif"));
        
        this.pacLife = new Image(getClass().getResourceAsStream("/Images/pacc.jpg"));
        this.blueGhost = new Image(getClass().getResourceAsStream("/Images/frightenedGhost.png"));
        
        
        this.wallImage = new Image(getClass().getResourceAsStream("/Images/gray.jpg"));
        this.bombImage = new Image(getClass().getResourceAsStream("/Images/Bomb.png"));
        this.smallDotImage = new Image(getClass().getResourceAsStream("/Images/dot.png"));
        //this.mazeImage = new Image(getClass().getResourceAsStream("/Images/Maze.gif"));
    }

    /**
     * Constructs an empty grid of ImageViews
     */
    private void initializeGrid() {
        if (this.rowCount > 0 && this.columnCount > 0) {
            this.cellViews = new ImageView[this.rowCount][this.columnCount];
            for (int row = 0; row < this.rowCount; row++) {
                for (int column = 0; column < this.columnCount; column++) {
                    ImageView imageView = new ImageView();
                    imageView.setX((double)column * CELL_WIDTH);
                    imageView.setY((double)row * CELL_WIDTH);
                    imageView.setFitWidth(CELL_WIDTH);
                    imageView.setFitHeight(CELL_WIDTH);
                    this.cellViews[row][column] = imageView;
                    this.getChildren().add(imageView);
                }
            }
        }
    }

    /** Updates the view to reflect the state of the model
     *
     * @param model
     */
    public void update(GameMap map) {
        assert map.getRowCount() == this.rowCount && map.getColumnCount() == this.columnCount;
        //for each ImageView, set the image to correspond with the CellValue of that cell
        for (int row = 0; row < this.rowCount; row++){
            for (int column = 0; column < this.columnCount; column++){
                CellValue value = map.getCellValue(row, column);
                if (value == CellValue.WALL) {
                    this.cellViews[row][column].setImage(this.wallImage);
                }
                else if (value == CellValue.BIGDOT) {
                    this.cellViews[row][column].setImage(this.bombImage);
                }
                else if (value == CellValue.SMALLDOT) {
                    this.cellViews[row][column].setImage(this.smallDotImage);
                }
                else if (value == CellValue.PACMANLIVES) {
                    this.cellViews[row][column].setImage(this.pacLife);
                }
                else {
                    this.cellViews[row][column].setImage(null);
                }
                //check which direction PacMan is going in and display the corresponding image
                if (row == map.getPacmanLocation().getX() && column == map.getPacmanLocation().getY() && (GameMap.getLastDirection() == GameMap.Direction.RIGHT || GameMap.getLastDirection() == GameMap.Direction.NONE)) {
                    this.cellViews[row][column].setImage(this.pacmanRightImage);
                }
                else if (row == map.getPacmanLocation().getX() && column == map.getPacmanLocation().getY() && GameMap.getLastDirection() == GameMap.Direction.LEFT) {
                    this.cellViews[row][column].setImage(this.pacmanLeftImage);
                }
                else if (row == map.getPacmanLocation().getX() && column == map.getPacmanLocation().getY() && GameMap.getLastDirection() == GameMap.Direction.UP) {
                    this.cellViews[row][column].setImage(this.pacmanUpImage);
                }
                else if (row == map.getPacmanLocation().getX() && column == map.getPacmanLocation().getY() && GameMap.getLastDirection() == GameMap.Direction.DOWN) {
                    this.cellViews[row][column].setImage(this.pacmanDownImage);
                }
                //make ghosts "blink" towards the end of ghostEatingMode (display regular ghost images on alternating updates of the counter)
                if (GameMap.isGhostEatingMode() && (GameBoardController.getGhostEatingModeCounter() == 6 ||GameBoardController.getGhostEatingModeCounter() == 4 || GameBoardController.getGhostEatingModeCounter() == 2)) {
                    if (row == map.getClydeLocation().getX() && column == map.getClydeLocation().getY()) {
                        this.cellViews[row][column].setImage(this.ClydeImage);
                    }
                    if (row == map.getPinkyLocation().getX() && column == map.getPinkyLocation().getY()) {
                        this.cellViews[row][column].setImage(this.PinkyImage);
                    }
                    if (row == map.getInkyLocation().getX() && column == map.getInkyLocation().getY()) {
                        this.cellViews[row][column].setImage(this.InkyImage);
                    }
                }
                //display blue ghosts in ghostEatingMode
                else if (GameMap.isGhostEatingMode()) {
                    if (row == map.getClydeLocation().getX() && column == map.getClydeLocation().getY()) {
                        this.cellViews[row][column].setImage(this.blueGhost);
                    }
                    if (row == map.getPinkyLocation().getX() && column == map.getPinkyLocation().getY()) {
                        this.cellViews[row][column].setImage(this.blueGhost);
                    }
                    if (row == map.getInkyLocation().getX() && column == map.getInkyLocation().getY()) {
                        this.cellViews[row][column].setImage(this.blueGhost);
                    }
                }
                //dispaly regular ghost images otherwise
                else {
                    if (row == map.getClydeLocation().getX() && column == map.getClydeLocation().getY()) {
                        this.cellViews[row][column].setImage(this.ClydeImage);
                    }
                    if (row == map.getPinkyLocation().getX() && column == map.getPinkyLocation().getY()) {
                        this.cellViews[row][column].setImage(this.PinkyImage);
                    }
                    if (row == map.getInkyLocation().getX() && column == map.getInkyLocation().getY()) {
                        this.cellViews[row][column].setImage(this.InkyImage);
                    }
                }
            }
        }
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.initializeGrid();
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        this.initializeGrid();
    }

	public static void updateLivesDisplay() {
		// TODO Auto-generated method stub
		
	}

	public static void updatePointsDisplay(Player player1) {
		// TODO Auto-generated method stub
		
	}


}
