package Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Answer {
//	private static int ID=1;
	private SimpleIntegerProperty id;
	private SimpleBooleanProperty isCorrect; //=new SimpleBooleanProperty(false);
	private SimpleStringProperty answerText;
//	private int questionID;
	public Answer( int id, String answerText, boolean isCorrect) {
		super();
//		if (Answer.ID >4)
//			Answer.ID=1;
		this.id=new SimpleIntegerProperty(id);
		this.isCorrect = new SimpleBooleanProperty(isCorrect);
		this.answerText =new SimpleStringProperty( answerText);
		
	}
	public boolean getIsCorrect() {
		return isCorrect.get();
	}
	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = new SimpleBooleanProperty(isCorrect);
	}
	public String getAnswerText() {
		return answerText.get();
	}
	public void setAnswerText(String answerText) {
		this.answerText = new SimpleStringProperty(answerText);
	}
	public int getId() {
		return id.get();
	}
	
//	public int getQuestionID() {
//		return questionID;
//	}
//	public void setQuestionID(int questionID) {
//		this.questionID = questionID;
//	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id.get();
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
		Answer other = (Answer) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", isCorrect=" + isCorrect + ", answerText=" + answerText + "]";
	}
	
	

}
