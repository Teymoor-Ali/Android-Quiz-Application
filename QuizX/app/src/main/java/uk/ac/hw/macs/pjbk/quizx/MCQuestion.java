package uk.ac.hw.macs.pjbk.quizx;

import java.util.ArrayList;
import java.util.List;

public class MCQuestion {
	private String question;
	private List<String> answers;
	private int correctAnswer = -1;
	
	public MCQuestion( String q) {
		question = q;
		answers = new ArrayList<String>();
	}
	public void addAnswer(String s, boolean correct)
	{
		answers.add(s);
		if (correct)
				correctAnswer = answers.size();
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getAnswer( int i)
	{
		return answers.get(i-1);
	}
	
	public String[] getAnswers()
	{
		return answers.toArray(new String[1]);
	}
	public boolean isCorrect( int i)
	{ 
		return i == correctAnswer;
	}

}
