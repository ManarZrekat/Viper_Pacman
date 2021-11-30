package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import Model.Answer;
import Model.Difficulty;
import Model.Question;
import javafx.collections.FXCollections;

class TestQuestion {
	static ArrayList<Question> questions;
	static Question q;
	/**
	 * first we have to setup the questions,
	 * it creates questions stub with answers in order to perform test on them,
	 * performed once before all tests.
	 */
	@BeforeAll
	static void setup() {
		questions = new ArrayList<>();
		q = new Question("Who let the dogs out", Difficulty.easy, FXCollections.observableArrayList());
		q.addAnswer(false, "Me");
		q.addAnswer(true, "You");
		q.addAnswer(false, "It escaped");
		q.addAnswer(false, "It is not out");
		questions.add(q);
	}
	/**
	 * it deletes all data and release memory, performed once after last test is done.
	 */
	@AfterAll
	static void teardown() {
		questions.clear();
	}
 
	/**
	 * Checks that a question has only one level of difficulty.
	 */
	@Test
	void oneLevel() {
		assertNotNull(q.getLevel());
	}
	/**
	 * Checks that a question has exactly four answers.
	 */
	@Test
	void fourAnswers() {
		assertEquals(4, q.getAnswers().size());
	}
	/**
	 * Checks that among the question's four answers, there is only one correct answer.
	 */
	@Test
	void oneCorrectAnswer() {
		int correct=0;
		for (Answer a: q.getAnswers()) {
			if (a.getIsCorrect() == true)
				correct++;
		}
		assertEquals(1, correct);
	}
	/**
	 * Checks that each question has a unique id.
	 */
	@Test
	void differentIds() {
		boolean good = true;
		for (int i=0; i<questions.size(); i++) {
			for (int j=1; j<questions.size(); j++) {
				if (questions.get(i).getId() == questions.get(j).getId())
					good = false;
			}
			assertTrue(good);
		}
	}
	/**
	 * Checks that question has text.
	 */
	@Test
	void questionHasText() {
		boolean good = true;
		for (Question q: questions) {
			if (q.getQuestionText().isBlank())
				good = false;
		}
		assertTrue(good);		
	}
	/**
	 * Checks that answer has text.
	 */
	@Test
	void answerHasText() {
		boolean good = true;
		for (Question q: questions) {
			for (Answer a: q.getAnswers()) {
				if (a.getAnswerText().isBlank())
					good = false;
			}
		}
		assertTrue(good);
	}

}

