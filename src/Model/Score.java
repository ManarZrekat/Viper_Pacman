package Model;

public class Score {
	private static int score;

	@SuppressWarnings("static-access")
	public Score(int score) {
		super();
		this.score = score;
	}

	public static int getScore() {
		try {
			return score;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score;
	}

	@SuppressWarnings("static-access")
	public void setScore(int score) {
		this.score = score;
	}
}
