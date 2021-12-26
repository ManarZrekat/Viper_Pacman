package Model;

import javafx.collections.ObservableList;

public class QuestionFactory {
	
	
	public Question createQuestion(String text, Difficulty level, ObservableList<Answer> answers) {
		//check index of the correct answer
		int index = 0;
		for(int j=0;j<answers.size();j++) {
    		if(answers.get(j).getIsCorrect()){
    			index = j+1;
    		}
    	}
		
		Question q = new Question(text, level, answers,index);
		return q;
		
	}

}
