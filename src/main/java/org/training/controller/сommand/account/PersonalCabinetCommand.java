package org.training.controller.сommand.account;

import org.training.controller.сommand.Command;
import org.training.model.entity.Exam;
import org.training.model.entity.Speciality;
import org.training.model.entity.Student;
import org.training.model.service.ExamService;
import org.training.model.service.SpecialityService;
import org.training.model.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;



/**
 * Move user to menu.
 * If access 'admin' move to admin menu.
 * If access 'user' move to user menu.
 */
public class PersonalCabinetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final HttpSession session = request.getSession();
        final Student.ROLE role = (Student.ROLE) session.getAttribute("role");

        SpecialityService specialityService = new SpecialityService();
        List<Speciality> specialities = specialityService.getAllSpecialities();
        request.setAttribute("specialities", specialities);

        ExamService examService = new ExamService();
        List<Exam> exams = examService.getAllExams();
        request.setAttribute("exams", exams);

        StudentService studentService = new StudentService();
        List<Student> students = studentService.getAllUsers();
        request.setAttribute("students", students);


        if (role.equals(Student.ROLE.ADMIN)) {
            return "/WEB-INF/admin/adminbasis.jsp";
        } else if (role.equals(Student.ROLE.USER)) {
            return "/WEB-INF/user/userbasis.jsp";
        } else {
            return "/jsp/login.jsp?userExist=false";
        }
    }
}





//String path = request.getServletContext().getContextPath();
//System.out.println("redirect@" + path + "/jsp/login.jsp?userExist=false");
//return "redirect@" + path + "/jsp/login.jsp?userExist=false";