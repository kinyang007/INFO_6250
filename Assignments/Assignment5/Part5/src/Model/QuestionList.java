package Model;

import java.util.*;

public class QuestionList {
    private List<Question> questionList;
    private int size;
    public QuestionList() {
        questionList = new ArrayList<>();
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
        size = questionList.size();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Question getQuestion(int i) {
        return questionList.get(i);
    }

    public void addQuestion(Question question) {
        questionList.add(question);
        size += 1;
    }
}
