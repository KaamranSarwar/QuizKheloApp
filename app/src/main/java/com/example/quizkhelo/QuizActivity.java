package com.example.quizkhelo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView QNum,questionText;
    RadioGroup Options,Options2;
    Button btnPrevious,btnNext;
    RadioButton option1,option2,option3,option4,currentlySelectedRadioButton;

    int currentQuestionNo, score,totalQuestions;
    List<Question> questions;
    Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        loadQuestions();
        totalQuestions = questions.size();

        currentQuestion = questions.get(currentQuestionNo - 1);
        setQuestion(currentQuestion);
        setQuestionNumberHeading();
        btnNext.setOnClickListener((v)->{
            handleNext();
        });
        btnPrevious.setEnabled(false);
        btnPrevious.setOnClickListener((v)->{
            handlePrevious();

        });
        Options.setOnCheckedChangeListener((group, checkedID) -> {
            if (checkedID != -1) { // Ensure a valid selection
                currentlySelectedRadioButton = findViewById(checkedID);

            }
        });



    }
    private void init()
    {
        QNum = findViewById(R.id.questionNumber);
        questionText = findViewById(R.id.questionText);
        Options = findViewById(R.id.optionsRadioGroup);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        currentQuestionNo = 1;
        currentQuestion = null;
        currentlySelectedRadioButton = null;
        score = 0;
    }
    private void setQuestion(Question q)
    {

        questionText.setText(q.getQuestion());
        String selected = questions.get(currentQuestionNo - 1).getSelectedOption();

        if(!(selected.isEmpty()))
        {
            if(q.getOption1().equals(selected))
                option1.setChecked(true);
            if(q.getOption2().equals(selected))
                option2.setChecked(true);
            if(q.getOption3().equals(selected))
                option3.setChecked(true);
            if(q.getOption4().equals(selected))
                option4.setChecked(true);
        }
        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());


    }
    private void setQuestionNumberHeading()
    {
        String QuestionNumber = currentQuestionNo +"/"+ totalQuestions;
        QNum.setText(QuestionNumber);

    }

    private void handleNext()
    {
        if(btnNext.getText().toString().equals("Finish"))
        {
            if(currentlySelectedRadioButton != null)
            {
                questions.get(currentQuestionNo - 1).setSelectedOption(currentlySelectedRadioButton.getText().toString());
                currentlySelectedRadioButton = null;
            }
            score = calculateScore();

            if(score == -1)
            {
                Toast.makeText(QuizActivity.this,"Please Attempt All Questions",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
                intent.putExtra("personName",getIntent().getStringExtra("personName"));
                intent.putExtra("score",score);
                intent.putExtra("totalQuestions",totalQuestions);
                startActivity(intent);
                finish();
            }
        }
        btnPrevious.setEnabled(true);
        currentQuestion = questions.get(currentQuestionNo);
        if(currentlySelectedRadioButton != null)
        {
            questions.get(currentQuestionNo - 1).setSelectedOption(currentlySelectedRadioButton.getText().toString());
            Options.clearCheck();
            currentlySelectedRadioButton = null;
        }
        currentQuestionNo+=1;
        if(currentQuestionNo == totalQuestions)
        {
            btnNext.setText("Finish");

        }
        setQuestionNumberHeading();
        setQuestion(currentQuestion);
    }
    private void handlePrevious()
    {

        currentQuestionNo-=1;
        if(currentlySelectedRadioButton != null)
        {
            questions.get(currentQuestionNo).setSelectedOption(currentlySelectedRadioButton.getText().toString());
            Options.clearCheck();
            currentlySelectedRadioButton = null;
        }
        if(currentQuestionNo == 1)
             btnPrevious.setEnabled(false);

        if(currentQuestionNo != totalQuestions)
            btnNext.setText("Next");

        currentQuestion = questions.get(currentQuestionNo-1);
        setQuestionNumberHeading();
        setQuestion(currentQuestion);
    }





    private void loadQuestions()
    {
        questions = new ArrayList<>();
        questions.add(new Question("Who has scored the highest individual score in an ODI match?","Rohit Sharma","Martin Guptill","Chris Gayle","Virender Sehwag","Rohit Sharma"));
        questions.add(new Question("How many players are there in a standard cricket team?","9","10","11","12","11"));
        questions.add(new Question("Which country won the first-ever Cricket World Cup in 1975?","Australia","West Indies","India","England","West Indies"));
        questions.add(new Question("What is the length of a standard cricket pitch between the stumps?","20 yards","22 yards","24 yards","18 yards","22 yards"));
        questions.add(new Question("Which bowler has taken the most wickets in Test cricket?","Muttiah Muralitharan","Shane Warne","Anil Kumble","James Anderson","Muttiah Muralitharan"));
        questions.add(new Question("Who was the captain of the Indian cricket team when they won the 2011 World Cup?","Virat Kohli","Rahul Dravid","Sourav Ganguly","MS Dhoni","MS Dhoni"));
        questions.add(new Question("What is a 'Hat-trick' in cricket?","Scoring 100 runs in a match","Taking three wickets in three consecutive balls","Hitting three sixes in a row","Taking three catches in an innings","Taking three wickets in three consecutive balls"));
        questions.add(new Question("Which country hosts the Ashes series with England?","South Africa","Australia","New Zealand","West Indies","Australia"));
        questions.add(new Question("Who holds the record for the fastest century in T20 Internationals?","Rohit Sharma","Chris Gayle","David Miller","Suryakumar Yadav","David Miller"));
        questions.add(new Question("Which team won the ICC T20 World Cup 2022?","India","Australia","England","Pakistan","England"));
    }

    private int calculateScore()
    {
        int score = 0;
        for(Question question: questions)
        {
            if(question.getSelectedOption().isEmpty())
            {
                score = -1;
                return score;
            }
            if(question.getSelectedOption().equals(question.getCorrectOption()))
                score+=1;


        }
        return score;

    }

}
