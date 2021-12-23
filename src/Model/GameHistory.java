package Model;

public class GameHistory {
	private String PlayerName;
	private Integer Score;

	public GameHistory(String name, int score2) {
		// TODO Auto-generated constructor stub
		super();
		this.PlayerName = name;
		this.Score = score2;
	}


	@Override
	public String toString() {
		return  PlayerName + "                 " + Score ;
	}


	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	public Integer getScore() {
		return Score;
	}

	public void setScore(Integer score) {
		Score = score;
	}
	

}
