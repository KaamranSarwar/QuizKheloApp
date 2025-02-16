package com.example.quizkhelo;

public class Question {
    private final String question,option1,option2,option3,option4,correctOption;
    private String selectedOption;
    Question(String q,String op1,String op2,String op3,String op4,String cO)
    {
        question = q;
        option1 = op1;
        option2 = op2;
        option3 = op3;
        option4 = op4;
        correctOption = cO;
        selectedOption = "";
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getSelectedOption() {
        return selectedOption;
    }
}
