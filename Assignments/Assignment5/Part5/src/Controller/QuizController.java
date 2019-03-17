package Controller;

import org.springframework.validation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;

import javax.servlet.http.*;
import Model.*;

public class QuizController extends AbstractWizardFormController {
    private String successView;
    private String cancelView;

    @Override
    protected Object formBackingObject(HttpServletRequest request) {
        System.out.println("Form Backing Object: ");
        HttpSession session = request.getSession();

        QuestionList questionList;
        if (session.getAttribute("questionList") == null) {
            questionList = initializeQuestionList();
            session.setAttribute("questionList", questionList);
        } else {
            questionList = (QuestionList)session.getAttribute("questionList");
        }

        return questionList.getQuestion(0);
    }

    @Override
    protected void validatePage(Object command, Errors errors, int page) {

    }

    @Override
    protected void postProcessPage(HttpServletRequest request, Object command, Errors errors, int page) {
        System.out.println("Post Process Page: ");
        HttpSession session = request.getSession();
        QuestionList questionList = (QuestionList)session.getAttribute("questionList");

        if (page >= 0 && page <= 9) {
            Question question = (Question)command;
//            System.out.println(getPageSessionAttributeName(request));
            System.out.println("Current Page: " + page);
            System.out.println("Target Page: " + getTargetPage(request, page));
            System.out.println("Question " + (page+1));
            System.out.println("User Option: " + question.getUserOption());

            questionList.getQuestion(page).setUserOption(question.getUserOption());
            session.setAttribute("questionList", questionList);
            // update command class
            question.setQuestionObject(questionList.getQuestion(getTargetPage(request, page)));
        }
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        HttpSession session = request.getSession();
        QuestionList questionList = (QuestionList)session.getAttribute("questionList");
        int page = getCurrentPage(request);

        Question question = (Question)command;
        System.out.println("Question " + (page+1));
        System.out.println("User Option: " + question.getUserOption());

        questionList.getQuestion(page).setUserOption(question.getUserOption());
        session.setAttribute("questionList", questionList);
        ModelAndView mv = new ModelAndView(getSuccessView());
        mv.addObject("specialPage", "finish");
        mv.addObject("questionList", questionList);
        return mv;
    }

    @Override
    protected ModelAndView processCancel(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        ModelAndView mv = new ModelAndView(getCancelView());
        mv.addObject("specialPage", "cancel");
        return mv;
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

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    public String getCancelView() {
        return cancelView;
    }

    public void setCancelView(String cancelView) {
        this.cancelView = cancelView;
    }
}
