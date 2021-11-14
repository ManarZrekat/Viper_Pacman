package Model;

import java.util.Arrays;

public class Question {
	private static int ID=1;                                  // a class counter to apply automatic id numbering.
	private int id;											  // specific question id.
	private Difficulty level;								  // enum indicating the difficulty level, which affects the score.
	private String questionText;							  // the text of the question.
	private Answer[] answers;								  // size 4 array of the answer options of this question.
	private int correctAnswer;
	public Question(Difficulty level, String questionText) {
		super();
		this.level = level;
		this.questionText=questionText;
		this.id=Question.ID++;
		this.answers=new Answer[4];
	}
	public Difficulty getLevel() {
		return level;
	}
	public void setLevel(Difficulty level) {
		this.level = level;
	}
	public int getId() {
		return id;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public Answer[] getAnswers() {
		return answers;
	}
	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}
	
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", level=" + level + "]";
	}
	/**
	 * adds a new answer for the question answers' array.
	 * @param isCorrect tells whether the added answer is the correct one or not.
	 * @param text the added answer text.
	 * @return true if answer was added or false otherwise.
	 */
	public boolean addAnswer(boolean isCorrect, String text) {
		if(this.answers.length<4) {
			Answer answer = new Answer(isCorrect, text, this.id);
			answers[answers.length] = answer;
			return true;
		}
		return false;
	}
	/**
	 * deletes an answer from the answers' array of the question
	 * @param answer the answer we want to remove.
	 * @return true if the answer was deleted or false otherwise.
	 */
	public boolean deleteAnswer(Answer answer) {
		if(Arrays.asList(getAnswers()).contains(answer)) {
			Arrays.asList(getAnswers()).remove(answer);
			return true;
		}
		return false;
	}
	/**
	 * change the answer text.
	 * @param answer defines which answer to change.
	 * @param text the new string to take place.
	 * @return true if the update was done or false otherwise.
	 */
	public boolean updateAnswer(Answer answer, String text) {
		if (Arrays.asList(getAnswers()).contains(answer)) {
			Arrays.asList(getAnswers()).get(answer.getId()).setAnswerText(text);
			return true;
		}
		return false;
	}
	
	/**
	 * change the answer text.
	 * @param answer defines which answer to change.
	 * @param isCorrect whether this answer is correct or not.
	 * @return true if the update was done or false otherwise.
	 */
	public boolean updateAnswer(Answer answer, boolean isCorrect) {
		if (Arrays.asList(getAnswers()).contains(answer)) {
			Arrays.asList(getAnswers()).get(answer.getId()).setCorrect(isCorrect);
			return true;
		}
		return false;
	}
	/**
	 * changes the question's correct answer.
	 * @param answer indicates the new correct answer number.
	 * @return true if the correct answer was changed, false otherwise.
	 */
	public boolean changeCorrectAnswer(int answer) {
		answers[this.getCorrectAnswer()].setCorrect(false);
		this.setCorrectAnswer(answer);
		answers[answer].setCorrect(true);
		return false;
	}
	

}
