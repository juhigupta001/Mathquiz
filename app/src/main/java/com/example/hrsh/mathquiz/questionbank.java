package com.example.hrsh.mathquiz;

/**
 * Created by hrsh on 6/7/2018.
 */

public class questionbank
        {
    private int question;
    private boolean truequestion;
    public questionbank(int question,boolean truequestion)
    {
        this.question=question;
        this.truequestion=truequestion;
    }
    public int getquestion()
    {
        return question;
    }
    public void setQuestion(int question)
    {
        this.question=question;

    }
    public boolean isTruequestion()
    {
        return truequestion;
    }
    public void settruequestion(boolean truequestion)
    {
        this.truequestion=truequestion;
    }

}

