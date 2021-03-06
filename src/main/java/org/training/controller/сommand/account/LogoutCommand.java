package org.training.controller.сommand.account;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.training.controller.сommand.Command;
import org.training.controller.сommand.CommandUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Processes logging out.
 * @author Stanislav Holovachuk
 */
public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String email = (String)request.getSession().getAttribute("email");
        CommandUtility.unlogUser(request, email);
        logger.info("User [" + email + "] " + "logged out." );

        String path = request.getServletContext().getContextPath();
        return "redirect@" + path ;
    }
}






















//        final HttpSession session = request.getSession();
//        session.removeAttribute("password");
//        session.removeAttribute("login");
//        session.removeAttribute("role");

//Optional<Object> login = Optional.ofNullable(request.getSession().getAttribute("login"));

//login.ifPresent(lgn -> System.out.println(lgn.toString()));

//login.ifPresent(lgn -> CommandUtility.unlogUser(request, lgn.toString()));
//        return "redirect:login";





//    @Override
//    public String execute(HttpServletRequest request) {
//
//        Optional<Object> email = Optional.ofNullable(request.getSession().getAttribute("email"));
//
//        email.ifPresent(e -> CommandUtility.unlogUser(request, e.toString()));
//
//        return "redirect:login";
//    }


//    @Override
//    public String execute(HttpServletRequest request) throws IOException, ServletException {
////        CommandUtility.setUserRole(request, Student.ROLE.UNKNOWN, "Guest");
////        return "/index.jsp";
//
//        final HttpSession session = request.getSession();
//
//        session.removeAttribute("password");
//        session.removeAttribute("login");
//        session.removeAttribute("role");
//
//        response.sendRedirect(super.getServletContext().getContextPath());
//
//    }
