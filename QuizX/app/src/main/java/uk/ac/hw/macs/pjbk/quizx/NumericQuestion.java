package uk.ac.hw.macs.pjbk.quizx;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumericQuestion extends Activity implements OnClickListener {

    private QuizApplication application;

    private TextView question;
    private TextView summary;
    private EditText answer;
    private Button answerButton;

    private int answerValue;
    private int operation;
    private String questionText;
    private int operand1Value;
    private int operand2Value;

    private int noQuestions = 3;
    private int noAnswered;
    private int noCorrect;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numeric_question);
        Log.i("ARITH", "Started");
        application = (QuizApplication) this.getApplication();
                question = (TextView) findViewById(R.id.maths_question_text);
        answer = (EditText)findViewById(R.id.maths_answer_text);
        summary = (TextView) findViewById(R.id.maths_summary);
        answerButton = (Button)findViewById(R.id.maths_answer_button);
        answerButton.setOnClickListener(this);
        noAnswered = 0;
        noCorrect = 0;
        generateSummary();
        generateQuestion();
    }

    private void generateSummary()
    {
        summary.setText( noCorrect + " answers our of " + noAnswered
                        + " attempted of " +noQuestions + " questions");
    }

    private void generateQuestion()
    {
        operation = (int)(Math.random()*3.0);
        operand1Value = (int) (Math.random()*19.0) + 1;
        operand2Value = (int) (Math.random()*19.0) + 1;
        switch (operation) {
        case 0:
            questionText = operand1Value + " + " + operand2Value;
            answerValue = operand1Value + operand2Value;
            break;
        case 1:
            questionText = operand1Value + " x " + operand2Value;
            answerValue = operand1Value * operand2Value;
            break;
        case 2:
            if ( operand1Value < operand2Value ) {
                answerValue = operand2Value;
                operand2Value = operand1Value;
                operand1Value = answerValue;
            }
            questionText = operand1Value + " - " + operand2Value;
            answerValue = operand1Value - operand2Value;
            break;
        default:
            break;
        }
        question.setText(questionText);
    }

	public void onClick(View v) {
       if ( v == answerButton )
        {
            int response = 0;
            noAnswered++;
            application.incrementQuestionsAttempted();
            try {
                response  = Integer.decode(answer.getText().toString());
                if ( response == answerValue ){
                    noCorrect++;
                    application.incrementQuestionsCorrect();
                }
            }
            catch (NumberFormatException nfe)
                {

                }
            answer .setText("");
            generateSummary();
            if ( noAnswered < noQuestions )
                generateQuestion();
            else
                finish();


	}

}
}
