package Model;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Controller.GameBoardController;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;

public class GameMap {
	
    public enum CellValue {
        EMPTY, SMALLDOT, BIGDOT, WALL, Clyde, Pinky,Inky, PACMAN, PACMANLIVES, QUESTION
    };
    public enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE
    };
    @FXML private int rowCount;
    @FXML private int columnCount;
    private CellValue[][] grid;
    private int score;
    private int level;
    private int dotCount;
    private static boolean gameOver;
    private static boolean youWon;
    private static boolean ghostEatingMode;
    private Point2D pacmanLocation;
    private Point2D pacmanVelocity;
    private Point2D ClydeLocation;
    private Point2D ClydeVelocity;
    private Point2D PinkyLocation;
    private Point2D PinkyVelocity;
    private Point2D InkyLocation;
    private Point2D InkyVelocity;
    private static Direction lastDirection;
    private static Direction currentDirection;
    private static Integer livesCount = 3;

    /**
     * Start a new game upon initializion
     */
    public GameMap() {
        this.startNewGame();
    }

    /**
     * Configure the grid CellValues based on the txt file and place PacMan and ghosts at their starting locations.
     * "W" indicates a wall, "E" indicates an empty square, "B" indicates a big dot, "S" indicates
     * a small dot, "1" or "2" indicates the ghosts home, and "P" indicates Pacman's starting position.
     *
     * @param fileName txt file containing the board configuration
     */
    public void initializeLevel(String fileName) {
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                lineScanner.next();
                columnCount++;
            }
            rowCount++;
        }
        columnCount = columnCount/rowCount;
        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        grid = new CellValue[rowCount][columnCount];
        int row = 0;
        int pacmanRow = 0;
        int pacmanColumn = 0;
        int ClydeRow = 0;
        int ClydeColumn = 0;
        int PinkyRow = 0;
        int PinkyColumn = 0;
        int InkyRow = 0;
        int InkyColumn = 0;
        
        while(scanner2.hasNextLine()){
            int column = 0;
            String line= scanner2.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()){
                String value = lineScanner.next();
                CellValue thisValue;
                if (value.equals("W")){
                    thisValue = CellValue.WALL;
                }
                else if (value.equals("S")){
                    thisValue = CellValue.SMALLDOT;
                    dotCount++;
                }
                else if (value.equals("B")){
                    thisValue = CellValue.BIGDOT;
                    dotCount++;
                }
                else if (value.equals("1")){
                    thisValue = CellValue.Clyde;
                    ClydeRow = row;
                    ClydeColumn = column;
                }
                else if (value.equals("2")){
                    thisValue = CellValue.Pinky;
                    PinkyRow = row;
                    PinkyColumn = column;
                }
                else if (value.equals("3")){
                    thisValue = CellValue.Inky;
                    InkyRow = row;
                    InkyColumn = column;
                }
                //added code 
                else if (value.equals("A")){
                    thisValue = CellValue.PACMANLIVES;
                    InkyRow = row;
                    InkyColumn = column;
                }
                else if (value.equals("D")){
                    thisValue = CellValue.PACMANLIVES;
                    InkyRow = row;
                    InkyColumn = column;
                }
                else if (value.equals("C")){
                    thisValue = CellValue.PACMANLIVES;
                    InkyRow = row;
                    InkyColumn = column;
                } // added code
                else if (value.equals("P")){
                    thisValue = CellValue.PACMAN;
                    pacmanRow = row;
                    pacmanColumn = column;
                }
                else //(value.equals("E"))
                {
                    thisValue = CellValue.EMPTY;
                }
                grid[row][column] = thisValue;
                column++;
            }
            row++;
        }
        pacmanLocation = new Point2D(pacmanRow, pacmanColumn);
        pacmanVelocity = new Point2D(0,0);
        ClydeLocation = new Point2D(ClydeRow,ClydeColumn);
        ClydeVelocity = new Point2D(-1, 0);
        PinkyLocation = new Point2D(InkyRow,InkyColumn);
        PinkyVelocity = new Point2D(-1, 0);
        InkyLocation = new Point2D(InkyRow,InkyColumn);
        InkyVelocity = new Point2D(-1, 0);
        currentDirection = Direction.NONE;
        lastDirection = Direction.NONE;
    }

    /** Initialize values of instance variables and initialize level map
     */
    public void startNewGame() {
        this.gameOver = false;
        this.youWon = false;
        this.ghostEatingMode = false;
        dotCount = 0;
        rowCount = 0;
        columnCount = 0;
        this.score = 0;
        this.level = 1;
        Player.setPacLives(livesCount);
        this.initializeLevel(GameBoardController.getLevelFile(0));
        System.out.println("ggg");
    }
    public void openPortal() {
    	grid[9][0]=CellValue.EMPTY;
    	grid[9][18]=CellValue.EMPTY;

    }
    public void closePortal() {
    	grid[9][0]=CellValue.WALL;
    	grid[9][18]=CellValue.WALL;

    }
    public void GameWon() {
    	youWon = true;
        gameOver = true;
    }

    /** Initialize the level map for the next level
     *
     */
    public void startNextLevel() {
//        if (this.isLevelComplete()) {
            //this.level++;
            rowCount = 0;
            columnCount = 0;
            youWon = false;
            ghostEatingMode = false;
            try {
            	System.out.println("level: "+level);
            	grid[7][0]=CellValue.EMPTY;
            	grid[7][18]=CellValue.EMPTY;
                //this.initializeLevel(GameBoardController.getLevelFile(1));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                //if there are no levels left in the level array, the game ends
                youWon = true;
                gameOver = true;
                level--;
            }
  //      }
    }

    /**
     * Move PacMan based on the direction indicated by the user (based on keyboard input from the Controller)
     * @param direction the most recently inputted direction for PacMan to move in
     */
    public void movePacman(Direction direction) {
        Point2D potentialPacmanVelocity = changeVelocity(direction);
        Point2D potentialPacmanLocation = pacmanLocation.add(potentialPacmanVelocity);
        //if PacMan goes offscreen, wrap around
        potentialPacmanLocation = setGoingOffscreenNewLocation(potentialPacmanLocation);
        //determine whether PacMan should change direction or continue in its most recent direction
        //if most recent direction input is the same as previous direction input, check for walls
        if (direction.equals(lastDirection)) {
            //if moving in the same direction would result in hitting a wall, stop moving
            if (grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.WALL 
            		|| grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.PACMANLIVES){
                pacmanVelocity = changeVelocity(Direction.NONE);
                setLastDirection(Direction.NONE);
            }
            else {
                pacmanVelocity = potentialPacmanVelocity;
                pacmanLocation = potentialPacmanLocation;
            }
        }
        //if most recent direction input is not the same as previous input, check for walls and corners before going in a new direction
        else {
            //if PacMan would hit a wall with the new direction input, check to make sure he would not hit a different wall if continuing in his previous direction
            if (grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.WALL
            		|| grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.PACMANLIVES){
                potentialPacmanVelocity = changeVelocity(lastDirection);
                potentialPacmanLocation = pacmanLocation.add(potentialPacmanVelocity);
                //if changing direction would hit another wall, stop moving
                if (grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.WALL|| grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.PACMANLIVES){
                    pacmanVelocity = changeVelocity(Direction.NONE);
                    setLastDirection(Direction.NONE);
                }
                else {
                    pacmanVelocity = changeVelocity(lastDirection);
                    pacmanLocation = pacmanLocation.add(pacmanVelocity);
                }
            }
            //otherwise, change direction and keep moving
            else {
                pacmanVelocity = potentialPacmanVelocity;
                pacmanLocation = potentialPacmanLocation;
                setLastDirection(direction);
            }
        }
    }

    /**
     * Move ghosts to follow PacMan as established in moveAGhost() method
     */
    public void moveGhosts() {
        Point2D[] ClydeData = moveAGhost(ClydeVelocity, ClydeLocation);
        Point2D[] PinkyData = moveAGhost(PinkyVelocity, PinkyLocation);
        Point2D[] InkyData = moveAGhost(InkyVelocity, InkyLocation);
        ClydeVelocity = ClydeData[0];
        ClydeLocation = ClydeData[1];
        PinkyVelocity = PinkyData[0];
        PinkyLocation = PinkyData[1];
        InkyVelocity = InkyData[0];
        InkyLocation = InkyData[1];

    }

    /**
     * Move a ghost to follow PacMan if he is in the same row or column, or move away from PacMan if in ghostEatingMode, otherwise move randomly when it hits a wall.
     * @param velocity the current velocity of the specified ghost
     * @param location the current location of the specified ghost
     * @return an array of Point2Ds containing a new velocity and location for the ghost
     */
    public Point2D[] moveAGhost(Point2D velocity, Point2D location){
        Random generator = new Random();
        //if the ghost is in the same row or column as PacMan and not in ghostEatingMode,
        // go in his direction until you get to a wall, then go a different direction
        //otherwise, go in a random direction, and if you hit a wall go in a different random direction
        if (!ghostEatingMode) {
            //check if ghost is in PacMan's column and move towards him
            if (location.getY() == pacmanLocation.getY()) {
                if (location.getX() > pacmanLocation.getX()) {
                    velocity = changeVelocity(Direction.UP);
                } else {
                    velocity = changeVelocity(Direction.DOWN);
                }
                Point2D potentialLocation = location.add(velocity);
                //if the ghost would go offscreen, wrap around
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                //generate new random directions until ghost can move without hitting a wall
                while (grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL
                		||grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.PACMANLIVES) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
            //check if ghost is in PacMan's row and move towards him
            else if (location.getX() == pacmanLocation.getX()) {
                if (location.getY() > pacmanLocation.getY()) {
                    velocity = changeVelocity(Direction.LEFT);
                } else {
                    velocity = changeVelocity(Direction.RIGHT);
                }
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while (grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL
                		||grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.PACMANLIVES) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
            //move in a consistent random direction until it hits a wall, then choose a new random direction
            else{
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while(grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL
                		||grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.PACMANLIVES){
                    int randomNum = generator.nextInt( 4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
        }
        //if the ghost is in the same row or column as Pacman and in ghostEatingMode, go in the opposite direction
        // until it hits a wall, then go a different direction
        //otherwise, go in a random direction, and if it hits a wall go in a different random direction
        if (ghostEatingMode) {
            if (location.getY() == pacmanLocation.getY()) {
                if (location.getX() > pacmanLocation.getX()) {
                    velocity = changeVelocity(Direction.DOWN);
                } else {
                    velocity = changeVelocity(Direction.UP);
                }
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while (grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL
                		||grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.PACMANLIVES)
                ///// INTBHII KIZZ HON ZDNA TNAY 3SHAN ET3AML M3 L PACMAN LIVES LIKE WALL 
                	{
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            } else if (location.getX() == pacmanLocation.getX()) {
                if (location.getY() > pacmanLocation.getY()) {
                    velocity = changeVelocity(Direction.RIGHT);
                } else {
                    velocity = changeVelocity(Direction.LEFT);
                }
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while (grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL
                 		||grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.PACMANLIVES) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
            else{
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while(grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL
                 		||grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.PACMANLIVES){
                    int randomNum = generator.nextInt( 4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
        }
        Point2D[] data = {velocity, location};
        return data;

    }


    /**
     * Wrap around the gameboard if the object's location would be off screen
     * @param objectLocation the specified object's location
     * @return Point2D new wrapped-around location
     */
    public Point2D setGoingOffscreenNewLocation(Point2D objectLocation) {
        //if object goes offscreen on the right
        if (objectLocation.getY() >= columnCount) {
            objectLocation = new Point2D(objectLocation.getX(), 0);
        }
        //if object goes offscreen on the left
        if (objectLocation.getY() < 0) {
            objectLocation = new Point2D(objectLocation.getX(), columnCount - 1);
        }
        return objectLocation;
    }

    /**
     * Connects each Direction to an integer 0-3
     * @param x an integer
     * @return the corresponding Direction
     */
    public Direction intToDirection(int x){
    	if(x == -1) {
    		return Direction.NONE;
    	}
    	else if (x == 0){
            return Direction.LEFT;
        }
        else if (x == 1){
            return Direction.RIGHT;
        }
        else if(x == 2){
            return Direction.UP;
        }
        else{
            return Direction.DOWN;
        }
    }
    /**
     * Resets Pacman's location and velocity to its home state
     */
    public void sendPacmanHome() {
        for (int row = 0; row < this.rowCount; row++) {
            for (int column = 0; column < this.columnCount; column++) {
                if (grid[row][column] == CellValue.PACMAN) {
                    pacmanLocation = new Point2D(row, column);
                }
            }
        }
        pacmanVelocity = new Point2D(-1, 0);
    }

    /**
     * Resets Clyde's location and velocity to its home state
     */
    public void sendClydeHome() {
        for (int row = 0; row < this.rowCount; row++) {
            for (int column = 0; column < this.columnCount; column++) {
                if (grid[row][column] == CellValue.Clyde) {
                    ClydeLocation = new Point2D(row, column);
                }
            }
        }

        ClydeVelocity = new Point2D(-1, 0);
    }

    /**
     * Resets Pinky's location and velocity to its home state
     */
    public void sendPinkyHome() {
        for (int row = 0; row < this.rowCount; row++) {
            for (int column = 0; column < this.columnCount; column++) {
                if (grid[row][column] == CellValue.Pinky) {
                   PinkyLocation = new Point2D(row, column);
                }
            }
        }
        PinkyVelocity = new Point2D(-1, 0);
    }
    
    /**
     * Resets inky's location and velocity to its home state
     */
    public void sendInkyHome() {
        for (int row = 0; row < this.rowCount; row++) {
            for (int column = 0; column < this.columnCount; column++) {
                if (grid[row][column] == CellValue.Inky) {
                   InkyLocation = new Point2D(row, column);
                }
            }
        }
        InkyVelocity = new Point2D(-1, 0);
    }

    /**
     * Updates the model to reflect the movement of PacMan and the ghosts and the change in state of any objects eaten
     * during the course of these movements. Switches game state to or from ghost-eating mode.
     * @param direction the most recently inputted direction for PacMan to move in
     */
    public void step(Direction direction) {
        this.movePacman(direction);
        CellValue pacmanLocationCellValue = grid[(int) pacmanLocation.getX()][(int) pacmanLocation.getY()];
      //If PacMan is on a question mark
        if (pacmanLocationCellValue == CellValue.QUESTION) {
            grid[(int) pacmanLocation.getX()][(int) pacmanLocation.getY()] = CellValue.EMPTY;
            dotCount--;
//            Answer questionAnswer = new Answer();
//            score = GameBoardController.questionAnswered(questionAnswer);
        }
        //if PacMan is on a small dot, delete small dot
        if (pacmanLocationCellValue == CellValue.SMALLDOT) {
            grid[(int) pacmanLocation.getX()][(int) pacmanLocation.getY()] = CellValue.EMPTY;
            dotCount--;
            score += 1;
        }
        //if PacMan is on a big dot, delete big dot and change game state to ghost-eating mode and initialize the counter
        if (pacmanLocationCellValue == CellValue.BIGDOT) {
            grid[(int) pacmanLocation.getX()][(int) pacmanLocation.getY()] = CellValue.EMPTY;
            dotCount--;
            score += 1;
            ghostEatingMode = true;
            GameBoardController.setGhostEatingModeCounter();
        }
        //send ghost back to ghosthome if PacMan is on a ghost in ghost-eating mode
        if (ghostEatingMode) {
            if (pacmanLocation.equals(ClydeLocation)) {
                sendClydeHome();
                //score += 100;
            }
            if (pacmanLocation.equals(PinkyLocation)) {
                sendPinkyHome();
                //score += 100;
            }
            if (pacmanLocation.equals(InkyLocation)) {
                sendInkyHome();
                //score += 100;
            }
        }
        //game over if PacMan is eaten by a ghost
        else {
            if (pacmanLocation.equals(ClydeLocation)) {
//                gameOver = true;
            	lives_left();
                pacmanVelocity = new Point2D(0,0);
            }
            else
            if (pacmanLocation.equals(PinkyLocation)) {
//                gameOver = true;
            	lives_left();
                pacmanVelocity = new Point2D(0,0);
            }
            else
            if (pacmanLocation.equals(InkyLocation)) {
//                gameOver = true;
            	lives_left();
                pacmanVelocity = new Point2D(0,0);
            }
        }
        //move ghosts and checks again if ghosts or PacMan are eaten (repeating these checks helps account for even/odd numbers of squares between ghosts and PacMan)
        this.moveGhosts();
        if (ghostEatingMode) {
            if (pacmanLocation.equals(ClydeLocation)) {
                sendClydeHome();
                score += 100;
            }
            if (pacmanLocation.equals(PinkyLocation)) {
                sendPinkyHome();
                score += 100;
            }
            if (pacmanLocation.equals(InkyLocation)) {
                sendInkyHome();
                score += 100;
            }
        }
        else {
            if (pacmanLocation.equals(ClydeLocation)) {
            	livesCount--;
            	Player.setPacLives(livesCount);
            	if (Player.getPacLives()==0) {
            		gameOver = true;
            		 pacmanVelocity = new Point2D(0,0);
            		
            	}
            	sendClydeHome();
            	sendPacmanHome();
            	lives_left();
               
            }
            
            else
       
            	if (pacmanLocation.equals(PinkyLocation)) {
            		livesCount--;
                	Player.setPacLives(livesCount);
                	if (Player.getPacLives()==0) {
                		gameOver = true;
                		 pacmanVelocity = new Point2D(0,0);
                	}
                	sendPinkyHome();
                	sendPacmanHome();
                	lives_left();
            }
            else
            if (pacmanLocation.equals(InkyLocation)) {
            	
            	livesCount--;
            	Player.setPacLives(livesCount);
            	if (Player.getPacLives()==0) {
            		gameOver = true;
            		 pacmanVelocity = new Point2D(0,0);
            	}
            	sendInkyHome();
            	sendPacmanHome();
            	lives_left();
            
            }
        }
        }
        //TODO
        //start a new level if level is complete
//        if (this.isLevelComplete()) {
//            pacmanVelocity = new Point2D(0,0);
//            startNextLevel();
//        }
//    }
    
    
    public void lives_left()
    {
    	//Player.setPacLives(Player.getPacLives()-1);
    	if(Player.getPacLives()==0) {
    		gameOver=true;
    		grid[20][2]=CellValue.EMPTY;
    		grid[20][3]=CellValue.EMPTY;
    		grid[20][1]=CellValue.EMPTY;
    		return;
    	}
    	if(Player.getPacLives()==1)
    	{
    		grid[20][2]=CellValue.EMPTY;
    		grid[20][3]=CellValue.EMPTY;

    	}
    	else
    	   	if(Player.getPacLives()==2)
        	{
        		grid[20][3]=CellValue.EMPTY;
        	}
    }

    /**
     * Connects each direction to Point2D velocity vectors (Left = (-1,0), Right = (1,0), Up = (0,-1), Down = (0,1))
     * @param direction
     * @return Point2D velocity vector
     */
    public Point2D changeVelocity(Direction direction){
        if(direction == Direction.LEFT){
            return new Point2D(0,-1);
        }
        else if(direction == Direction.RIGHT){
            return new Point2D(0,1);
        }
        else if(direction == Direction.UP){
            return new Point2D(-1,0);
        }
        else if(direction == Direction.DOWN){
            return new Point2D(1,0);
        }
        else{
            return new Point2D(0,0);
        }
    }

    public static boolean isGhostEatingMode() {
        return ghostEatingMode;
    }

    public static void setGhostEatingMode(boolean ghostEatingModeBool) {
        ghostEatingMode = ghostEatingModeBool;
    }

    public static boolean isYouWon() {
        return youWon;
    }

    /**
     * When all dots are eaten, level is complete
     * @return boolean
     */
//    public boolean isLevelComplete() {
//        return this.dotCount == 0;
//    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public CellValue[][] getGrid() {
        return grid;
    }

    /**
     * @param row
     * @param column
     * @return the Cell Value of cell (row, column)
     */
    public CellValue getCellValue(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }

    public static Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    public static Direction getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(Direction direction) {
        lastDirection = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /** Add new points to the score
     *
     * @param points
     */
    public void addToScore(int points) {
        this.score += points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the total number of dots left (big and small)
     */
    public int getDotCount() {
        return dotCount;
    }

    public void setDotCount(int dotCount) {
        this.dotCount = dotCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public Point2D getPacmanLocation() {
        return pacmanLocation;
    }

    public void setPacmanLocation(Point2D pacmanLocation) {
        this.pacmanLocation = pacmanLocation;
    }

    public Point2D getClydeLocation() {
        return ClydeLocation;
    }

    public void setClydeLocation(Point2D ClydeLocation) {
        this.ClydeLocation = ClydeLocation;
    }

    public Point2D getPinkyLocation() {
        return PinkyLocation;
    }

    public void setPinkyLocation(Point2D PinkyLocation) {
        this.PinkyLocation = PinkyLocation;
    }
    
    public Point2D getInkyLocation() {
        return InkyLocation;
    }

    public void setInkyLocation(Point2D InkyLocation) {
        this.InkyLocation = InkyLocation;
    }

    public Point2D getPacmanVelocity() {
        return pacmanVelocity;
    }

    public void setPacmanVelocity(Point2D velocity) {
        this.pacmanVelocity = velocity;
    }

    public Point2D getClydeVelocity() {
        return ClydeVelocity;
    }

    public void setClydeVelocity(Point2D ClydeVelocity) {
        this.ClydeVelocity = ClydeVelocity;
    }

    public Point2D getPinkyVelocity() {
        return PinkyVelocity;
    }

    public void setPinkyVelocity(Point2D PinkyVelocity) {
        this.PinkyVelocity = PinkyVelocity;
    }
    
    public Point2D getInkyVelocity() {
        return InkyVelocity;
    }

    public void setInkyVelocity(Point2D InkyVelocity) {
        this.InkyVelocity = InkyVelocity;
    }
    

}
