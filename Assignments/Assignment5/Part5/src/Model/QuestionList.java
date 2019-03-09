package Model;

import java.util.*;

public class QuestionList {
    private List<Question> questionList;

    public QuestionList() {
        questionList = new ArrayList<>();
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Question getQuestion(int i) {
        return questionList.get(i);
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }
}
