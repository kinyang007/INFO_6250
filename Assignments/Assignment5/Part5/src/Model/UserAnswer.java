package Model;

import java.util.*;

public class UserAnswer {
    private Map<Question, String> userAnswer;

    public UserAnswer() {
        userAnswer = new HashMap<>();
    }

    public UserAnswer(QuestionList questionList) {
        userAnswer = new HashMap<>();
        for (Question question : questionList.getQuestionList()) {
            userAnswer.put(question, null);
        }
    }

    public Map<Question, String> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Map<Question, String> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public void modifyUserAnswer(Question question, String answer) {
        userAnswer.put(question, answer);
    }
}
