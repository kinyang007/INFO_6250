package Controller;

import org.springframework.validation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;

import java.util.*;
import javax.servlet.http.*;
import Model.*;

public class QuizController extends AbstractWizardFormController {

    @Override
    protected Map referenceData(HttpServletRequest request, int page) {
        HttpSession session = request.getSession();
        QuestionList questionList;
        if (session.getAttribute("questionList") == null) {
            questionList = initializeQuestionList();
            session.setAttribute("questionList", questionList);
        } else {
            questionList = (QuestionList)session.getAttribute("questionList");
        }

        UserAnswer userAnswer;
        if (session.getAttribute("userAnswer") == null) {
            userAnswer = new UserAnswer(questionList);
            session.setAttribute("userAnswer", userAnswer);
        } else {
            userAnswer = (UserAnswer)session.getAttribute("userAnswer");
        }

        if (page >= 1 && page <= 10) {
            Map map = new HashMap();
            map.put("question", questionList.getQuestionList().get(page));
            return map;
        } else {
            return userAnswer.getUserAnswer();
        }
    }

    @Override
    protected void validatePage(Object command, Errors errors, int page) {

    }

    @Override
    protected void postProcessPage(HttpServletRequest request, Object command, Errors errors, int page) {
        HttpSession session = request.getSession();
        QuestionList questionList;
        if (session.getAttribute("questionList") == null) {
            questionList = initializeQuestionList();
            session.setAttribute("questionList", questionList);
        } else {
            questionList = (QuestionList)session.getAttribute("questionList");
        }

        UserAnswer userAnswer;
        if (session.getAttribute("userAnswer") == null) {
            userAnswer = new UserAnswer(questionList);
            session.setAttribute("userAnswer", userAnswer);
        } else {
            userAnswer = (UserAnswer)session.getAttribute("userAnswer");
        }

        if (page >= 0 && page <= 9) {
            String answer = request.getParameter("option");
            userAnswer.modifyUserAnswer(questionList.getQuestionList().get(page + 1), answer);
            session.setAttribute("userAnswer", userAnswer);
        }
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        HttpSession session = request.getSession();
        List<Question> questionList = (List<Question>)session.getAttribute("questionList");
        Map<Question, Character> userAnswer = (Map<Question, Character>)session.getAttribute("userAnswer");
        ModelAndView mv = new ModelAndView("ResultsPage");
        mv.addObject("questionList", questionList);
        mv.addObject("userAnswer", userAnswer);
        return mv;
    }

    @Override
    protected ModelAndView processCancel(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        return new ModelAndView("redirect:/cancel");
    }

    private QuestionList initializeQuestionList() {
        QuestionList questionList = new QuestionList();
        questionList.addQuestion(new Question(
                "Which method is called when client request come?",
                "get()",
                "post()",
                "init()",
                "service()",
                "D"
        ));
        questionList.addQuestion(new Question(
                "Which interface contain servlet life-cycle methods?",
                "HttpServlet",
                "GenericServlet",
                "Service",
                "Servlet",
                "D"
        ));
        questionList.addQuestion(new Question(
                "Which of these life-cycle method you can over-ride in your class?",
                "init()",
                "service()",
                "doGet()",
                "All of these",
                "D"
        ));
        questionList.addQuestion(new Question(
                "Which tag of DD maps internal name of servlet to public URL pattern?",
                "servlet",
                "servlet-mapping",
                "web-app",
                "servlet-mappings",
                "B"
        ));
        questionList.addQuestion(new Question(
                "Which life-cycle method make ready the servlet for garbage collection?",
                "init",
                "service",
                "system.gc",
                "destroy",
                "D"
        ));
        questionList.addQuestion(new Question(
                "Which http method is idempotent?",
                "get",
                "post",
                "trace",
                "option",
                "A"
        ));
        questionList.addQuestion(new Question(
                "Which life cycle method is called once in servlet life?",
                "class loading",
                "init()",
                "service()",
                "destroy()",
                "B"
        ));
        questionList.addQuestion(new Question(
                "Which method does not exists in HttpServlet Class?",
                "service",
                "init",
                "doGet",
                "doPost",
                "B"
        ));
        questionList.addQuestion(new Question(
                "Which statement is not true about ServletConfig?",
                "There is one servlet config per one application",
                "We can access ServletContext through it",
                "provide deploy-time information to server",
                "There is one servlet config per one servlet",
                "A"
        ));
        questionList.addQuestion(new Question(
                "Which statement is not true about ServletContext?",
                "There is one ServletContext per one application",
                "Generally it is used to get web app parameters",
                "We can get Server Information through it",
                "There is one Servlet Context per one servlet",
                "D"
        ));
        return questionList;
    }
}
