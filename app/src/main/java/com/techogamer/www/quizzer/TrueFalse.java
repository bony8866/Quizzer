package com.techogamer.www.quizzer;

public class TrueFalse {
    int question_id;
    boolean answer;
    public TrueFalse (int question,boolean ans)
    {
        question_id=question;
        answer=ans;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
