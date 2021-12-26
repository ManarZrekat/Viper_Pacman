package Model;

import java.awt.Image;


import javax.swing.ImageIcon;

public class GameEntity {
	private int pozX;
	private int pozY;
	protected static int cellDim;
	protected static int cellOffset = 0;
	private static int mazeWidth;
	private static int mazeHeight;
	private char direction = ' ';
	private char nextDirection = ' ';
	private static int k = 0;
	
	private static boolean frightened = false;
	
	/////////////////////////////////////// Pinky, Inky , Clyde
	private static boolean[] eatenGhosts = { false, false, false};	//stores for each ghost whether it is in its EATEN state
	
	
	protected ImageIcon upImg;
	protected ImageIcon downImg;
	protected ImageIcon leftImg;
	protected ImageIcon rightImg;
	protected ImageIcon pausedImg;
	protected Image currentImg;
	
	protected static ImageIcon eyesUp = new ImageIcon("Sprites/ghostEyesUp.png");
	protected static ImageIcon eyesDown = new ImageIcon("Sprites/ghostEyesDown.png");
	protected static ImageIcon eyesLeft = new ImageIcon("Sprites/ghostEyesLeft.png");
	protected static ImageIcon eyesRight = new ImageIcon("Sprites/ghostEyesRight.png");
	
}
