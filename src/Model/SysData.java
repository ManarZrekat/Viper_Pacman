package Model;

import java.util.ArrayList;

public class SysData {
	private static ArrayList<Question> questions; 
	/**
	 * add a new question to questions.
	 * @param text the String of the question
	 */
	public void addQuestion(String text, Difficulty level) {
		questions.add(new Question(level, text));
	}
	/**
	 * remove a question from questions.
	 * @param id the ID of the question to be removed
	 * @return true if the wanted question was removed, false otherwise.
	 */
	public boolean removeQuestion(int id) {
		for (Question q : questions) {
			if (q.getId()== id) {
				questions.remove(q);
				return true;
			}
		}
		return false;
	}
	public int getQuestionIdByText(String text) {
		for (Question q: questions) {
			if(q.getQuestionText().equals(text))
				return q.getId();
		}
		return 0;
	}


}
