package com.controller;

import com.dao.ProjectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProjectController",value = "/ProjectController")
public class ProjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        if("add".equals(flag)){
            search(request,response);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProjectDao projectDao = new ProjectDao();
        List list = projectDao.search();
        HttpSession session =request.getSession();
        session.setAttribute("data",list);
        response.sendRedirect("addProject.jsp");
    }
}
