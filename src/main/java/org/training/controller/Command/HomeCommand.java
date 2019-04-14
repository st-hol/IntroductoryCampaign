package org.training.controller.Command;

import org.training.model.entity.Exam;
import org.training.model.entity.Speciality;
import org.training.model.service.ExamService;
import org.training.model.service.SpecialityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


//servlet jss - routes


public class HomeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        SpecialityService specialityService = new SpecialityService();
        List<Speciality> specialities = specialityService.getAllSpecialities();
        request.setAttribute("specialities", specialities );


        return "/jsp/index.jsp";

//        String path = request.getServletContext().getContextPath();
//        //System.out.println(path);
//        return "redirect@" + path + "/jsp/index.jsp";
    }
}




//            Optional<Object> role = Optional.ofNullable(request.getSession().getAttribute("role"));
//            return role.map(o -> "redirect@" + o.toString().toLowerCase())
//                    .orElse("redirect@login");