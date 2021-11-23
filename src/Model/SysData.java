package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SysData {
	private  ObservableList<Question> questions; 
	static SysData data = null;
	private SysData() {
		questions = FXCollections.observableArrayList();
	}
	public static SysData getInstance() {
		if(data == null)
			data = new SysData();
		return data;
	}
	
	
	public ObservableList<Question> getQuestions() {
		return getInstance().questions;
	}
	public void setQuestions(ObservableList<Question> questions) {
		getInstance().questions = questions;
	}
	/**
	 * add a new question to questions.
	 * @param text the String of the question
	 */
	public void addQuestion(String text, Difficulty level, ObservableList<Answer> answers) {
		Question q = new Question(text, level, answers);
		getInstance().questions.add(q);
	}
	/**
	 * remove a question from questions.
	 * @param id the ID of the question to be removed
	 * @return true if the wanted question was removed, false otherwise.
	 */
	public boolean removeQuestion(Question question) {
		for (Question q : questions) {
			if (q.getId()== question.getId()) {
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
