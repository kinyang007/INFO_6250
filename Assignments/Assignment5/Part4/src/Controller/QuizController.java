package Controller;

import Model.*;
import java.util.*;
import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @RequestMapping("/{questionIndex:[\\d]+}.htm")
    public ModelAndView questionHandler(@PathVariable("questionIndex") int questionIndex,
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("quiz");
        HttpSession session = request.getSession();
        List<Question> questionList;
        if (session.getAttribute("questionList") == null) {
            questionList = initializeQuestionList();
            session.setAttribute("questionList", questionList);
        } else {
            questionList = (List<Question>)session.getAttribute("questionList");
        }

        Map<Question, Character> userAnswer;
        if (session.getAttribute("userAnswer") == null) {
            userAnswer = initializeUserAnswer(questionList);
            session.setAttribute("userAnswer", userAnswer);
        } else {
            userAnswer = (Map<Question, Character>)session.getAttribute("userAnswer");
        }

        if (questionIndex != 1) {
            String lastAnswer = request.getParameter("option");
            userAnswer.put(questionList.get(questionIndex - 2), lastAnswer.charAt(0));
//            System.out.println("Question " + (questionIndex - 2) + ": ");
//            System.out.println(lastAnswer);
            session.setAttribute("userAnswer", userAnswer);
        }

        mv.addObject("page", "question");
        mv.addObject("questionIndex", questionIndex);
        mv.addObject("question", questionList.get(questionIndex - 1));
        return mv;
    }

    @RequestMapping("/result.htm")
    public ModelAndView resultHandler(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("quiz");
        HttpSession session = request.getSession();

        List<Question> questionList = (List<Question>)session.getAttribute("questionList");
        Map<Question, Character> userAnswer = (Map<Question, Character>)session.getAttribute("userAnswer");
        String lastAnswer = request.getParameter("option");
        userAnswer.put(questionList.get(9), lastAnswer.charAt(0));
        session.setAttribute("userAnswer", userAnswer);

        mv.addObject("page", "result");
        mv.addObject("questionList", questionList);
        mv.addObject("userAnswer", userAnswer);

        return mv;
    }

    private List<Question> initializeQuestionList() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question(
                "Which method is called when client request come?",
                "get()",
                "post()",
                "init()",
                "service()",
                'D'
        ));
        questionList.add(new Question(
                "Which interface contain servlet life-cycle methods?",
                "HttpServlet",
                "GenericServlet",
                "Service",
                "Servlet",
                'D'
        ));
        questionList.add(new Question(
                "Which of these life-cycle method you can over-ride in your class?",
                "init()",
                "service()",
                "doGet()",
                "All of these",
                'D'
        ));
        questionList.add(new Question(
                "Which tag of DD maps internal name of servlet to public URL pattern?",
                "servlet",
                "servlet-mapping",
                "web-app",
                "servlet-mappings",
                'B'
        ));
        questionList.add(new Question(
                "Which life-cycle method make ready the servlet for garbage collection?",
                "init",
                "service",
                "system.gc",
                "destroy",
                'D'
        ));
        questionList.add(new Question(
                "Which http method is idempotent?",
                "get",
                "post",
                "trace",
                "option",
                'A'
        ));
        questionList.add(new Question(
                "Which life cycle method is called once in servlet life?",
                "class loading",
                "init()",
                "service()",
                "destroy()",
                'B'
        ));
        questionList.add(new Question(
                "Which method does not exists in HttpServlet Class?",
                "service",
                "init",
                "doGet",
                "doPost",
                'B'
        ));
        questionList.add(new Question(
                "Which statement is not true about ServletConfig?",
                "There is one servlet config per one application",
                "We can access ServletContext through it",
                "provide deploy-time information to server",
                "There is one servlet config per one servlet",
                'A'
        ));
        questionList.add(new Question(
                "Which statement is not true about ServletContext?",
                "There is one ServletContext per one application",
                "Generally it is used to get web app parameters",
                "We can get Server Information through it",
                "There is one Servlet Context per one servlet",
                'D'
        ));
        return questionList;
    }

    private Map<Question, Character> initializeUserAnswer(List<Question> questionList) {
        Map<Question, Character> userAnswer = new HashMap<>();
        for (Question question : questionList) {
            userAnswer.put(question, null);
        }
        return userAnswer;
    }
}
