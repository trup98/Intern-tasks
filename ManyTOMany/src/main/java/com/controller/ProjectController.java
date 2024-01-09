package com.controller;

import com.dao.ProjectDao;
import com.model.ProjectVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProjectController", value = "/ProjectController")
public class ProjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        if ("insert".equals(flag)) {
            insert(request, response);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String projectName = request.getParameter("projectName");
        ProjectVo projectVo = new ProjectVo();
        projectVo.setProjectName(projectName);

        ProjectDao projectDao = new ProjectDao();
        projectDao.insert(projectVo);
        response.sendRedirect("addProject.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        if ("search".equals(flag)) {
            search(request, response);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProjectDao projectDao = new ProjectDao();
        List list = projectDao.search();
    }
}
